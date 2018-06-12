package at.htl_villach.docker2go;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

import static at.htl_villach.docker2go.ConnectionActivity.KEY_CONN_ID;

public class TabContainers extends Fragment implements Connection.onCommandStatusChangeListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_CONTAINER = "ContainerID";
    int connectionPosition;
    OverviewActivity parentActivity;
    ListView listViewContainers;
    Connection activeConnection;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayAdapter<DockerContainer> containerArrayAdapter;
    ArrayList<DockerContainer> containers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_containers_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listViewContainers = view.findViewById(R.id.listViewContainers);
        parentActivity = (OverviewActivity)getActivity();
        containers = new ArrayList<>();

        // swipe to refresh
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        containerArrayAdapter = new ArrayAdapter<DockerContainer>(this.getContext(),
                R.layout.list_item_container, R.id.textViewContainerName, containers) {

            @NonNull
            @Override
            public View getView(int position, View v, @NonNull ViewGroup parent){
                View view = super.getView(position, v, parent);
                TextView textViewName = view.findViewById(R.id.textViewContainerName);
                View statusIndicator = view.findViewById(R.id.statusIndicator);
                final DockerContainer currentContainer = containers.get(position);

                textViewName.setText(currentContainer.getNames().get(0).substring(1));
                if(currentContainer.getState().equals(getString(R.string.info_running))){
                    // container running
                    statusIndicator.setBackgroundColor(
                            ContextCompat.getColor(getContext(), R.color.containersRunning));
                }else{
                    // container not running
                    statusIndicator.setBackgroundColor(
                            ContextCompat.getColor(getContext(), R.color.containersStopped));
                }

                return view;
            }
        };

        Bundle arguments = getArguments();
        if(arguments != null) {
            connectionPosition = getArguments().getInt(KEY_CONN_ID, 0);
            activeConnection = Connection.listAll(Connection.class).get(connectionPosition);
            LoadContainers();
        } else
            Toast.makeText(getContext(), "Fatal issue: No Arguments", Toast.LENGTH_SHORT).show();

        listViewContainers.setAdapter(containerArrayAdapter);
        listViewContainers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final ContainerBottomSheetDialog containerDialog = new ContainerBottomSheetDialog();
                Bundle arguments = new Bundle();
                arguments.putInt(KEY_CONN_ID, connectionPosition);
                containerDialog.setArguments(arguments);
                containerDialog.setContainer(containers.get(position));
                containerDialog.setListener(new ContainerBottomSheetDialog.BottomSheetListener(){
                    @Override
                    public void onActionSelected(String action, DockerContainer affectedContainer) {
                        switch(action) {
                            case "Inspect":
                                showDetails(affectedContainer);
                                break;
                            case "Restart":
                                parentActivity.loadingIndicator.setVisibility(View.VISIBLE);
                                restartContainer(containerDialog.getContainer());
                                break;
                            case "Start":
                                parentActivity.loadingIndicator.setVisibility(View.VISIBLE);
                                startContainer(containerDialog.getContainer());
                                break;
                            case "Stop":
                                parentActivity.loadingIndicator.setVisibility(View.VISIBLE);
                                stopContainer(containerDialog.getContainer());
                                break;
                        }
                    }
                });
                containerDialog.show(getFragmentManager(), "Container Sheet");
            }
        });
    }

    public void startContainer(DockerContainer container) {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/" + container.getNames().get(0).substring(1) + "/start")
                .requestMethod("POST");

        activeConnection.executeCommand(this, containersCommand);
    }

    public void restartContainer(DockerContainer container) {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/" + container.getNames().get(0).substring(1) + "/restart")
                .requestMethod("POST");

        activeConnection.executeCommand(this, containersCommand);
    }


    public void stopContainer(DockerContainer container) {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/" + container.getNames().get(0).substring(1) + "/stop")
                .requestMethod("POST");

        activeConnection.executeCommand(this, containersCommand);
    }

    public void showDetails(DockerContainer container) {
        Intent i = new Intent(getActivity(), ContainerDetailActivity.class);
        i.putExtra(KEY_CONN_ID, connectionPosition);
        i.putExtra("ContainerID", container.getId());
        startActivity(i);
    }

    public void LoadContainers() {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/json?all=true")
                .requestMethod("GET");

        activeConnection.executeCommand(this, containersCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        String[] parts = command.getApiEndpoint().split("/");

        if(parts[1].equalsIgnoreCase("containers") && parts[2].equalsIgnoreCase("json?all=true")) {
            DockerContainer[] dContainers = DockerObjParser.Containers(command.getResult());

            containers.clear();
            containers.addAll(Arrays.asList(dContainers));

            containerArrayAdapter.notifyDataSetChanged();
        }
        else if(parts[3].equalsIgnoreCase("start")) {
            LoadContainers();
            parentActivity.infoTab.LoadInfo();
            parentActivity.loadingIndicator.setVisibility(View.GONE);
        }
        else if(parts[3].equalsIgnoreCase("stop")) {
            LoadContainers();
            parentActivity.infoTab.LoadInfo();
            parentActivity.loadingIndicator.setVisibility(View.GONE);
        }
        else if(parts[3].equalsIgnoreCase("restart")) {
            LoadContainers();
            parentActivity.infoTab.LoadInfo();
            parentActivity.loadingIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        LoadContainers();
    }
}
