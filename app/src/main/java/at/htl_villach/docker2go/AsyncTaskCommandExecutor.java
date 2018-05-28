package at.htl_villach.docker2go;

import android.os.AsyncTask;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.HostKey;
import com.jcraft.jsch.HostKeyRepository;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
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
public class AsyncTaskCommandExecutor extends AsyncTask<Command, Command, CommandExecutionSummary> {

    private Connection connection;
    private Connection.onCommandStatusChangeListener listener;

    private CommandExecutionSummary ces;

    private Session jschSession;

    public AsyncTaskCommandExecutor(Connection.onCommandStatusChangeListener listener, Connection connection) {
        this.listener = listener;
        this.connection = connection;
    }

    @Override
    protected void onPreExecute() {
        this.ces = new CommandExecutionSummary();
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
    protected CommandExecutionSummary doInBackground(Command... backgroundParams) {
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
                channelssh.setCommand(command.toString());
                channelssh.connect();

                // wait for the command until it exits with the expected exit code
                int refreshTimeout = command.getRefreshTimeOut();
                while (!channelssh.isClosed()) {
                    Thread.sleep(refreshTimeout);
                }

                // set result fields
                command.setResult(baos.toString("utf8"));
                command.setExitCode(channelssh.getExitStatus());

                this.ces.addCommand(command);

                // calls method onProgressUpdate
                publishProgress(command);
                channelssh.disconnect();
            }

            return this.ces;
        }catch (Exception e){
            e.printStackTrace();
            this.ces.addException(e);
            return this.ces;
        }finally {
            this.jschSession.disconnect();
        }
    }

    // called by doInBackground's publishProgress() method,
    // this will call the listener's method onCommandFinished() with each Object separately
    @Override
    protected void onProgressUpdate(Command... updateValues) {
        for (Command updateValue : updateValues) {
            listener.onCommandFinished(updateValue);
        }
    }

    // called by return from doInBackground
    // return value should be "finished" if everything went correct.
    // if something went wrong, the String returned is the message of the Exception
    @Override
    protected void onPostExecute(CommandExecutionSummary ces) {
        listener.onAllCommandsFinished(ces);
    }
}
