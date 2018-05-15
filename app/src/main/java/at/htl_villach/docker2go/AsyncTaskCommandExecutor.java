package at.htl_villach.docker2go;

import android.os.AsyncTask;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

/**
 * Created by kroel on 07.05.2018.
 */


// this is the class which runs the long lasting operation
// first param type: backgroundParams, the command to be executed
// second param type: updateValues, with this Object, the listener is called
// third param type: resultValue, with this Object, the listener is called
public class AsyncTaskCommandExecutor extends AsyncTask<Command, Object, Object> {

    private Connection connection;
    private Connection.onCommandStatusChangeListener listener;

    private Session jschSession;

    public AsyncTaskCommandExecutor(Connection.onCommandStatusChangeListener listener, Connection connection) {
        this.listener = listener;
        this.connection = connection;
    }

    @Override
    protected void onPreExecute() {
        // connect to server
        try{
            JSch jsch = new JSch();

            this.jschSession = jsch.getSession(
                    this.connection.getUsername(),
                    this.connection.getHostname(),
                    this.connection.getSshPort());

            this.jschSession.setPassword(this.connection.getPassword());

            // Avoid asking for key confirmation
            // TODO: add host key checking with users intervention
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            this.jschSession.setConfig(prop);

            //Properties config = new Properties();
            //config.put("StrictHostKeyChecking", "no");
            //config.put("PreferredAuthentications", "password");
            //this.session.setConfig(config);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // executed every command on remote machine in separated channels
    // still using same channel
    @Override
    protected String doInBackground(Command... backgroundParams) {
        try {
            // connect to remote machine
            this.jschSession.connect(this.connection.getConnectionTimeout());

            // Execute single command and use onProgressUpdate for output
            for (Command command : backgroundParams) {
                ChannelExec channelssh = (ChannelExec) this.jschSession.openChannel("exec");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                channelssh.setOutputStream(baos);

                // use Commands parseString method to get the actual command
                // in String representation e.g.: "curl localhost"
                channelssh.setCommand(command.parseString());
                channelssh.connect();

                // wait for the command until it exits with the expected exit code
                // TODO: what if it exits with another exit code?
                int refreshTimeout = command.getRefreshTimeOut();
                while (channelssh.getExitStatus() != command.getExpectedExitCode()) {
                    Thread.sleep(refreshTimeout);
                }

                // calls method onProgressUpdate
                publishProgress(baos.toString("utf8"));
                channelssh.disconnect();
            }

            return "finished";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }finally {
            this.jschSession.disconnect();
        }
    }

    // called by doInBackground's publishProgress() method,
    // this will call the listener's method onCommandFinished() with each Object separately
    @Override
    protected void onProgressUpdate(Object... updateValues) {
        for (Object updateValue : updateValues) {
            listener.onCommandFinished(updateValue);
        }
    }

    // called by return from doInBackground
    // return value should be "finished" if everything went correct.
    // if something went wrong, the String returned is the message of the Exception
    @Override
    protected void onPostExecute(Object resultValue) {
        listener.onAllCommandsFinished(resultValue);
    }
}
