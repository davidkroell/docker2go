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
// first param type: backgroundParams
// second param type: updateValues
// third param type: resultValue
public class AsyncTaskCommandExecutor extends AsyncTask<Command, String, String> {

    private Connection connection;
    private Connection.onCommandStatusChangeListener listener;

    private JSch jsch;
    private Session jschSession;

    public AsyncTaskCommandExecutor(Connection.onCommandStatusChangeListener listener, Connection connection) {
        this.listener = listener;
        this.connection = connection;
    }

    @Override
    protected void onPreExecute() {
        // connect to server
        try{
            this.jsch = new JSch();

            this.jschSession = this.jsch.getSession(
                    this.connection.getUsername(),
                    this.connection.getHostname(),
                    this.connection.getSshPort());

            this.jschSession.setPassword(this.connection.getPassword());

            // Avoid asking for key confirmation
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

    @Override
    protected String doInBackground(Command... backgroundParams) {
        try {
            this.jschSession.connect(this.connection.getConnectionTimeout());

            // Execute single command and use onProgressUpdate for output
            for (int i = 0; i < backgroundParams.length; i++){
                ChannelExec channelssh = (ChannelExec) this.jschSession.openChannel("exec");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                channelssh.setOutputStream(baos);

                channelssh.setCommand(backgroundParams[i].parseString());
                channelssh.connect();

                while (channelssh.getExitStatus() != backgroundParams[i].getExpectedExitCode()) {
                    Thread.sleep(backgroundParams[i].getRefreshTimeOut());
                }
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

    @Override
    protected void onProgressUpdate(String... updateValues) {
        // Things to be done while execution of long running operation is in progress
        // This method is triggered by calling the method publishProgress()
        // The updateValues are the params of the method publishProgress()

        for (int i = 0; i < updateValues.length; i++) {
            listener.onCommandFinished(updateValues[i]);
        }
    }

    @Override
    protected void onPostExecute(String resultValue) {
        // Things to be done after the long running operation
        // for example showing the result value
        listener.onAllCommandsFinished(resultValue);
    }
}
