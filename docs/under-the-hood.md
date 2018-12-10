# Under the hood
This chapter will give you a brief overview about the technical aspects of the Docker2go app.
The code snippets should help you to understand the concepts.

## Layout
The layout is implemented as a card layout and most of the time leverages a `ListView` to display entries.
The ListView in `ConnectionActivity` is served from a local SQLite Database accessed by SugarORM.

On connecting, a new Activity gets displayed which is based on a `TabbedActvity`.
The three Tabs (Information, Containers, Images) are implemented as `Fragments`.
In those fragments are different layouts implemented.
But they all share the `SwipeToRefresh` paradigm which should help to make Docker2go more usable.

Additionally, on the containers Tab (`TabContainers`) a BottomSheet gets displayed by clicking on a ListViewItem.
This BottomSheet gives you some basic actions based on the containers state and shows more details information.
For example the Docker Image is displayed in the BottomSheet whereas its not visible in the ListView.  

The activity parent (`OverviewActivity`) of the Fragments holds the Connection object in order to call the remote Docker API.

## Multithreading
Every netowork based operation is done concurrently inside a `AsyncTask` in order to not freeze the userinterface on long lasting operations.
The `Connection` class provides the method `executeCommand()` which lets you execute one or more command on a remote host.
For executing command on remote hosts the `AsyncTaskCommandExecutor` class is used.
Every call to `executeCommand()` is encapsulated in a single [JSch](index.md#third-party-libraries) connection.

```java
@Override
protected void onPreExecute() {
    // establish a secure connection to remote host
}
```

This class is able to execute one or more commands which must implement the `Command` interface.

```java
@Override
protected CommandExecutionSummary doInBackground(Command... commands) {
    try {
        // connect to remote host
        this.jschSession.connect();

        // Execute single command and use onProgressUpdate for output
        for (Command command : commands) {

            Command c = execCommand(command);
            this.ces.addCommand(c);

            // calls method onProgressUpdate
            // which then call onCommandFinished
            publishProgress(c);
        }

        return this.ces;
    } catch (Exception e) {
        e.printStackTrace();
        // exceptions in CommandExecutionSummary can be retrieved in GUI classes
        this.ces.addException(e);
        return this.ces;
    } finally {
        // make sure to close the connection
        this.jschSession.disconnect();
    }
}
```

Docker2go leverages a listener pattern as seen in the above example.
The listener class is a parameter of `executeCommand()` which will then call the methods `onCommandFinished()`
or `onAllCommandsFinished()` depending of the state of the AsyncTask.
The results of the Commands are part of the Command itself which is returned by the `onCommandFinished()` method.

## Command and Docker objects paradigm
Docker2go depends highly on the use of interfaces.
The `Command` is one of those abstract interfaces.
The Command interface is implemented by `DockerCommandBuilder` which lets you build your command
by utilizing a fluent API.

```java
DockerCommandBuilder command = new DockerCommandBuilder()
        .apiEndpoint("/containers/json")
        .queryParam("all", "true")
        .requestMethod("GET");

parentActivity.activeConnection.executeCommand(this, command); 
```

The `this` in the code snippet above is the listener class described in seciton [Multithreading](#multithreading).
Special in Docker2go's implementation is, that the GUI classes never talk directly to the AsyncTask
in the background. Every operation is cooridinated by the Connection class.

The results of the command are stored as string and can be accessed by calling the `getResult()` method of the `Command`.
The results are in JSON format which gets parsed by one of `DockerObjParser` static methods.
DockerObjParser utilizes the Gson library to make the parsing process more convinient.

```java
@Override
public void onCommandFinished(Command command) {
    if (command.exitedAsExpected()) {
            DockerContainer[] dContainers = DockerObjParser.Containers(command.getResult());

            containers.clear();
            containers.addAll(Arrays.asList(dContainers));

            containerArrayAdapter.notifyDataSetChanged();
    }
}
```

The example above shows how the list of containers is refreshed by overriding the `onCommandFinished()` method.
