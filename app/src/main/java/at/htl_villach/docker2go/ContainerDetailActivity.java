package at.htl_villach.docker2go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ContainerDetailActivity extends AppCompatActivity implements Connection.onCommandStatusChangeListener {

    Connection activeConnection;
    DockerContainerDetail currentContainer;
    String containerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_detail);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Integer position = extras.getInt(ConnectionActivity.KEY_POSITION, 0);
            activeConnection = Connection.listAll(Connection.class).get(position);
            containerID = extras.getString(TabContainers.KEY_CONTAINER, null);
            loadContainerData(containerID);
        } else
            Toast.makeText(getApplicationContext(), "Fatal issue: No Arguments", Toast.LENGTH_SHORT).show();
    }

    public void loadContainerData(String containerID) {
        Toast.makeText(getApplicationContext(), "ContainerID is " + containerID, Toast.LENGTH_SHORT).show();
        DockerCommandBuilder imagesCommand = new DockerCommandBuilder()
                .apiEndpoint(String.format("/containers/%s/json", containerID))
                .requestMethod("GET");

        activeConnection.executeCommand(this, imagesCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        DockerContainerDetail dContainer = DockerObjParser.Container(command.getResult());
            currentContainer = dContainer;

            TextView textViewContainerName = findViewById(R.id.textViewContainerName);
            TextView textViewContainerStatus = findViewById(R.id.textViewContainerStatus);
            TextView textViewContainerPlatform = findViewById(R.id.textViewContainerPlatform);
            Toast.makeText(getApplicationContext(), "Loaded data for container: " + dContainer.getName(), Toast.LENGTH_SHORT).show();
            textViewContainerName.setText(dContainer.getName().substring(1));
            textViewContainerStatus.setText(dContainer.getState().getStatus());
            textViewContainerPlatform.setText(dContainer.getPlatform());
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        //Toast.makeText(getContext(), (!commandExecutionSummary.exececutedWithExceptions()) ? "Command executed successfully!" : "Command couldn't be executed", Toast.LENGTH_SHORT).show();
    }
}
