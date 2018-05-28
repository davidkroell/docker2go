package at.htl_villach.docker2go;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TabInformation extends Fragment implements Connection.onCommandStatusChangeListener {

    static TextView textViewOperatingSystem, textViewServerVersion, textViewMemory, textViewContainersTotal, textViewContainersRunning, textViewContainersPaused, textViewContainersStopped;
    static Connection activeConnection;

    /* TODO:
        * Keep content after page goes inactive
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_information_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // System text fields
        textViewOperatingSystem = view.findViewById(R.id.textViewOperatingSystem);
        textViewServerVersion = view.findViewById(R.id.textViewServerVersion);
        textViewMemory = view.findViewById(R.id.textViewMemory);
        // Containers text fields
        textViewContainersTotal = view.findViewById(R.id.textViewContainersTotal);
        textViewContainersRunning = view.findViewById(R.id.textViewContainersRunning);
        textViewContainersPaused = view.findViewById(R.id.textViewContainersPaused);
        textViewContainersStopped = view.findViewById(R.id.textViewContainersStopped);

        Bundle arguments = getArguments();
        if(arguments != null) {
            Integer position = getArguments().getInt(ConnectionActivity.KEY_POSITION, 0);
            activeConnection = Connection.listAll(Connection.class).get(position);
            LoadInfo();
        } else
            Toast.makeText(getContext(), "Fatal issue: No Arguments", Toast.LENGTH_SHORT).show();
    }

    public void LoadInfo() {
        DockerCommandBuilder infoCommand = new DockerCommandBuilder()
                .apiEndpoint("/info")
                .requestMethod("GET");

        activeConnection.executeCommand(this, infoCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        DockerObj dObj = DockerObjParser.Any((DockerCommandBuilder) command);

        if(dObj instanceof DockerInfo){
            DockerInfo dInfo = (DockerInfo) dObj;
            //System
            textViewOperatingSystem.setText(dInfo.getOperatingSystem());
            textViewServerVersion.setText(dInfo.getServerVersion());
            textViewMemory.setText(Utilities.formatBytes(dInfo.getMemTotal()));
            //Containers
            textViewContainersTotal.setText(Integer.toString(dInfo.getContainers()));
            textViewContainersRunning.setText(Integer.toString(dInfo.getContainersRunning()));
            textViewContainersPaused.setText(Integer.toString(dInfo.getContainersPaused()));
            textViewContainersStopped.setText(Integer.toString(dInfo.getContainersStopped()));

        }
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        //Toast.makeText(getContext(), (!commandExecutionSummary.exececutedWithExceptions()) ? "Command executed successfully!" : "Command couldn't be executed", Toast.LENGTH_SHORT).show();
    }
}
