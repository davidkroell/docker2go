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

        //Set title of this activity
        getSupportActionBar().setTitle("Container Details");

        //Retrieve current connection and Container ID from extras
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Integer position = extras.getInt(ConnectionActivity.KEY_CONN_ID, 0);
            activeConnection = Connection.listAll(Connection.class).get(position);
            containerID = extras.getString(TabContainers.KEY_CONTAINER, null);
            loadContainerData(containerID);
        } else
            Toast.makeText(getApplicationContext(), "Fatal issue: No Arguments", Toast.LENGTH_SHORT).show();
    }

    public void loadContainerData(String containerID) {
        //Create command based on containerID
        DockerCommandBuilder imagesCommand = new DockerCommandBuilder()
                .apiEndpoint(String.format("/containers/%s/json", containerID))
                .requestMethod("GET");

        //Execute the previously created command to retrieve data from the server
        activeConnection.executeCommand(this, imagesCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        //Convert JSON to Object
        DockerContainerDetail dContainer = DockerObjParser.Container(command.getResult());
        //Set resulting object as currentContainer.
        currentContainer = dContainer;

        //Create objects to interact with the UI
        TextView textViewContainerName = findViewById(R.id.textViewContainerName);
        TextView textViewContainerStatus = findViewById(R.id.textViewContainerStatus);
        TextView textViewContainerPlatform = findViewById(R.id.textViewContainerPlatform);
        TextView textViewContainerImage = findViewById(R.id.textViewContainerImage);
        TextView textViewContainerWorkingDir = findViewById(R.id.textViewContainerWorkingDir);
        TextView textViewNetworkIPAddress = findViewById(R.id.textViewNetworkIPAddress);
        TextView textViewNetworkGateway = findViewById(R.id.textViewNetworkGateway);
        TextView textViewNetworkMACAddress = findViewById(R.id.textViewNetworkMACAddress);

        //Update Overview Card
        textViewContainerName.setText(dContainer.getName().substring(1));
        textViewContainerStatus.setText(dContainer.getState().getStatus());
        textViewContainerPlatform.setText(dContainer.getPlatform());
        textViewContainerImage.setText(dContainer.getConfig().getImage());
        textViewContainerWorkingDir.setText(dContainer.getConfig().getWorkingDir());

        //Update Network Card
        textViewNetworkIPAddress.setText(dContainer.getNetworkSettings().getIPAddress() + "/" + dContainer.getNetworkSettings().getIPPrefixLen());
        textViewNetworkGateway.setText(dContainer.getNetworkSettings().getGateway());
        textViewNetworkMACAddress.setText(dContainer.getNetworkSettings().getMacAddress());
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {

    }
}
