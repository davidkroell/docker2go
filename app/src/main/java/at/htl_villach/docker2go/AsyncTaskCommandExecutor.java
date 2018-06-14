package at.htl_villach.docker2go;

import android.os.AsyncTask;
import android.util.Base64;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.HostKey;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import at.htl_villach.docker2go.Connection.onCommandStatusChangeListener;

/**
 * Created by kroel on 07.05.2018.
 */


// this is the class which runs the long lasting operation
// first param type: backgroundParams, the command to be executed
// second param type: updateValues, with this Object, the listener is called
// third param type: resultValue, with this Object, the listener is called
public class AsyncTaskCommandExecutor extends AsyncTask<Command, Command, CommandExecutionSummary> {

    private Connection connection;
    private onCommandStatusChangeListener listener;
    private boolean hostKeyVerification = true;
    // how often to execute single commands
    // 0 = endless
    private int numExecute;
    // how long to wait between command execution
    // in milliseconds
    private int waitInterval;

    private CommandExecutionSummary ces;

    private Session jschSession;

    AsyncTaskCommandExecutor(onCommandStatusChangeListener listener, Connection connection) {
        this(listener, connection, true);
    }

    AsyncTaskCommandExecutor(onCommandStatusChangeListener listener, Connection connection, boolean hostKeyVerification) {
        this(listener, connection, hostKeyVerification, 1, 0);
    }

    AsyncTaskCommandExecutor(onCommandStatusChangeListener listener, Connection connection, boolean hostKeyVerification, int numExecute, int waitInterval) {
        this.listener = listener;
        this.connection = connection;
        this.hostKeyVerification = hostKeyVerification;
        this.numExecute = numExecute;
        this.waitInterval = waitInterval;
    }

    @Override
    protected void onPreExecute() {
        this.ces = new CommandExecutionSummary();
        // bootstrap connection to server
        try {
            JSch jsch = new JSch();

            this.jschSession = jsch.getSession(
                    this.connection.getUsername(),
                    this.connection.getHostname(),
                    this.connection.getSshPort());

            this.jschSession.setPassword(this.connection.getPassword());

            // optional no static hostkey checking
            if (!this.hostKeyVerification) {
                Properties prop = new Properties();
                prop.put("StrictHostKeyChecking", "no");
                this.jschSession.setConfig(prop);
            } else {
                // static host key checking
                byte[] key = Base64.decode(this.connection.getHostKey(), Base64.DEFAULT);
                HostKey hostKey = new HostKey(this.connection.getHostname(), key);
                jsch.getHostKeyRepository().add(hostKey, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // executed every command on remote machine in separated environment,
    // still using same channel
    @Override
    protected CommandExecutionSummary doInBackground(Command... commands) {
        try {
            // connect to remote machine
            this.jschSession.connect(this.connection.getConnectionTimeout());

            // execute commands "numExecute" times
            // TODO execute command endless
            for (int i = 0; i <= numExecute; i++) {
                // Execute single command and use onProgressUpdate for output
                for (Command command : commands) {

                    Command c = execCommand(command);
                    this.ces.addCommand(c);

                    // calls method onProgressUpdate
                    publishProgress(c);
                }
                Thread.sleep(waitInterval);
            }

            return this.ces;
        } catch (Exception e) {
            e.printStackTrace();
            this.ces.addException(e);
            return this.ces;
        } finally {
            this.jschSession.disconnect();
        }
    }

    private Command execCommand(Command c)
            throws JSchException, InterruptedException, UnsupportedEncodingException {
        ChannelExec channelssh = (ChannelExec) this.jschSession.openChannel("exec");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        channelssh.setOutputStream(baos);

        // use Commands toString method to get the actual command
        // in String representation e.g.: "curl localhost"
        channelssh.setCommand(c.toString());
        channelssh.connect();

        // wait until the channel is closed
        int refreshTimeout = c.getRefreshTimeOut();
        while (!channelssh.isClosed()) {
            Thread.sleep(refreshTimeout);
        }

        // set result fields
        c.setResult(baos.toString("utf8"));
        c.setExitCode(channelssh.getExitStatus());
        channelssh.disconnect();
        return c;
    }

    // called by doInBackground's publishProgress() method,
    // this will call the listener's method onCommandFinished() with each command object separately
    @Override
    protected void onProgressUpdate(Command... updateValues) {
        for (Command updateValue : updateValues) {
            listener.onCommandFinished(updateValue);
        }
    }

    // called by return from doInBackground
    @Override
    protected void onPostExecute(CommandExecutionSummary ces) {
        listener.onAllCommandsFinished(ces);
    }
}
