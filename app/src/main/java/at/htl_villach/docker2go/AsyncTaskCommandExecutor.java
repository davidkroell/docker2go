package at.htl_villach.docker2go;

import android.os.AsyncTask;

/**
 * Created by kroel on 07.05.2018.
 */


// this is the class which runs the long lasting operation
// first param type: backgroundParams
// second param type: updateValues
// third param type: resultValue
public class AsyncTaskCommandExecutor extends AsyncTask<String, String, String> {

    Connection connection;
    Connection.onCommandStatusChangeListener listener;

    public AsyncTaskCommandExecutor(Connection.onCommandStatusChangeListener listener, Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void onPreExecute() {
        // connect to server
    }

    @Override
    protected String doInBackground(String... backgroundParams) {
        return "finished";
    }

    @Override
    protected void onProgressUpdate(String... updateValues) {
        // Things to be done while execution of long running operation is in progress
        // This method is triggered by calling the method publishProgress()
        // The updateValues are the params of the method publishProgress()

        listener.onCommandProgressUpdate(updateValues);
    }

    @Override
    protected void onPostExecute(String resultValue) {
        // Things to be done after the long running operation
        // for example showing the result value
        listener.onCommandFinished(resultValue);
    }
}
