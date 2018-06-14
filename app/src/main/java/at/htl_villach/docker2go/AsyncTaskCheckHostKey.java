package at.htl_villach.docker2go;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.jcraft.jsch.HostKey;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

/**
 * Created by kroel on 07.05.2018.
 */


// this is the class which runs the long lasting operation
// first param type: backgroundParams, the command to be executed
// second param type: updateValues, with this Object, the listener is called
// third param type: resultValue, with this Object, the listener is called
public class AsyncTaskCheckHostKey extends AsyncTask<Void, Void, HostKey> {

    private Connection connection;
    private AlertDialog hkDialog;
    private Resources resources;

    private Session jschSession;

    public AsyncTaskCheckHostKey(Connection connection, AlertDialog hkDialog, Resources resources) {
        this.connection = connection;
        this.hkDialog = hkDialog;
        this.resources = resources;
    }

    @Override
    protected void onPreExecute() {
        // connect to server
        try {
            JSch jsch = new JSch();

            this.jschSession = jsch.getSession(
                    this.connection.getUsername(),
                    this.connection.getHostname(),
                    this.connection.getSshPort());

            this.jschSession.setPassword(this.connection.getPassword());

            // avoid static host key checking, this class is used for this
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            this.jschSession.setConfig(prop);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // get the remote HostKey
    @Override
    protected HostKey doInBackground(Void... useless) {
        try {
            // connect to remote machine
            this.jschSession.connect(this.connection.getConnectionTimeout());
            return jschSession.getHostKey();
        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        } finally {
            this.jschSession.disconnect();
        }
    }

    // called by doInBackground's publishProgress() method,
    // this will call the listener's method onCommandFinished() with each Object separately
    @Override
    protected void onProgressUpdate(Void... voids) {
        // unused
    }

    // called by return from doInBackground
    // the servers real hostkey
    // this method is run on main thread (UI)
    @Override
    protected void onPostExecute(HostKey hostKey) {
        // prototype connection with hostkey's bytes
        connection.setServerHostKey(hostKey.getKey());

        String fingerprint = hostKey.getFingerPrint(new JSch());
        String type = hostKey.getType();
        this.hkDialog.setTitle(resources.getString(R.string.dialog_hk_title));
        this.hkDialog.setMessage(
                String.format(resources.getString(R.string.dialog_hk_message), type, fingerprint)
        );
        hkDialog.show();
    }
}
