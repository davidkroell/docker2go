package at.htl_villach.docker2go;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static at.htl_villach.docker2go.ConnectionActivity.KEY_CONN_ID;

public class TabContainers extends Fragment implements Connection.onCommandStatusChangeListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_CONTAINER = "ContainerID";
    int connectionPosition;
    OverviewActivity parentActivity;
    ListView listViewContainers;
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
        parentActivity = (OverviewActivity) getActivity();
        containers = new ArrayList<>();

        // swipe to refresh
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        containerArrayAdapter = new ArrayAdapter<DockerContainer>(this.getContext(),
                R.layout.list_item_container, R.id.textViewContainerName, containers) {

            @NonNull
            @Override
            public View getView(int position, View v, @NonNull ViewGroup parent) {
                View view = super.getView(position, v, parent);
                TextView textViewName = view.findViewById(R.id.textViewContainerName);
                TextView textViewStatus = view.findViewById(R.id.textViewStatus);
                TextView textViewCreated = view.findViewById(R.id.textViewCreatedAt);
                TextView textViewPorts = view.findViewById(R.id.textViewPorts);
                View statusIndicator = view.findViewById(R.id.statusIndicator);
                DockerContainer currentContainer = containers.get(position);

                // set textfields
                textViewName.setText(currentContainer.getNames().get(0).substring(1));
                textViewStatus.setText(currentContainer.getStatus());
                String age = Utilities.timeElapsedString(
                        new Date((long) currentContainer.getCreated() * 1000),
                        getResources().getStringArray(R.array.date_types_singular),
                        getResources().getStringArray(R.array.date_types_plural),
                        getString(R.string.date_type_past_wrapper));

                textViewCreated.setText(age);

                StringBuilder portMapping = new StringBuilder();

                for (DockerContainer.PortsBean portsBean: currentContainer.getPorts()) {
                    portMapping.append(portsBean.getPublicPort())
                            .append("->")
                            .append(portsBean.getPrivatePort())
                            .append("/")
                            .append(portsBean.getType());

                    portMapping.append("\n");
                }

                if(portMapping.length() != 0){
                    // remove last character (last line break)
                    portMapping.setLength(portMapping.length() -1);
                    textViewPorts.setText(portMapping);
                    textViewPorts.setVisibility(View.VISIBLE);
                }

                int statusColor;
                if (currentContainer.getState().equals(getString(R.string.info_running))) {
                    // container running
                    statusColor = ContextCompat.getColor(getContext(), R.color.containersRunning);
                } else {
                    // container not running
                    statusColor = ContextCompat.getColor(getContext(), R.color.containersStopped);
                }

                // change color of round status indicator
                Drawable mDrawable = ContextCompat.getDrawable(getContext(), R.drawable.rounded_corners);
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(statusColor, PorterDuff.Mode.SRC));
                statusIndicator.setBackground(mDrawable);

                return view;
            }
        };

        loadContainers();

        listViewContainers.setAdapter(containerArrayAdapter);
        listViewContainers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final ContainerBottomSheetDialog containerDialog = new ContainerBottomSheetDialog();
                Bundle arguments = new Bundle();
                arguments.putInt(KEY_CONN_ID, connectionPosition);
                containerDialog.setArguments(arguments);
                containerDialog.setContainer(containers.get(position));
                containerDialog.setListener(new ContainerBottomSheetDialog.BottomSheetListener() {
                    @Override
                    public void onActionSelected(String action, DockerContainer affectedContainer) {
                        switch (action) {
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
                            case "Remove":
                                parentActivity.loadingIndicator.setVisibility(View.VISIBLE);
                                removeContainer(containerDialog.getContainer());
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

        parentActivity.activeConnection.executeCommand(this, containersCommand);
    }

    public void restartContainer(DockerContainer container) {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/" + container.getNames().get(0).substring(1) + "/restart")
                .requestMethod("POST");

        parentActivity.activeConnection.executeCommand(this, containersCommand);
    }


    public void stopContainer(DockerContainer container) {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/" + container.getNames().get(0).substring(1) + "/stop")
                .requestMethod("POST");

        parentActivity.activeConnection.executeCommand(this, containersCommand);
    }

    public void removeContainer(DockerContainer container) {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/" + container.getId())
                .requestMethod("DELETE");

        parentActivity.activeConnection.executeCommand(this, containersCommand);
    }

    public void showDetails(DockerContainer container) {
        Intent i = new Intent(getActivity(), ContainerDetailActivity.class);
        i.putExtra(KEY_CONN_ID, connectionPosition);
        i.putExtra("ContainerID", container.getId());
        startActivity(i);
    }

    public void loadContainers() {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/json")
                .queryParam("all", "true")
                .requestMethod("GET");

        parentActivity.activeConnection.executeCommand(this, containersCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        if (command.exitedAsExpected()) {
            String[] parts = ((DockerCommandBuilder) command).getQueryString().split("/");

            if (parts[1].equalsIgnoreCase("containers") && parts[2].equalsIgnoreCase("json?all=true")) {
                DockerContainer[] dContainers = DockerObjParser.Containers(command.getResult());

                containers.clear();
                containers.addAll(Arrays.asList(dContainers));

                containerArrayAdapter.notifyDataSetChanged();
            } else {
                loadContainers();
                parentActivity.infoTab.loadInfo();
                parentActivity.loadingIndicator.setVisibility(View.GONE);
            }
        } else {
            Snackbar.make(swipeRefreshLayout,
                    "something went wrong",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadContainers();
    }
}
