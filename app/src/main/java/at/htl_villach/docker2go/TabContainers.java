package at.htl_villach.docker2go;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabContainers extends Fragment implements Connection.onCommandStatusChangeListener {

    ListView listViewContainers;
    Connection activeConnection;
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
        containers = new ArrayList<DockerContainer>();

        containerArrayAdapter = new ArrayAdapter<DockerContainer>(this.getContext(),
                R.layout.list_item_container, R.id.textViewContainerName, containers) {

            @Override
            public View getView(int position, View v, ViewGroup parent){
                View view = super.getView(position, v, parent);
                TextView textViewName = view.findViewById(R.id.textViewContainerName);
                View statusIncicator = view.findViewById(R.id.statusIndicator);
                final DockerContainer currentContainer = containers.get(position);

                textViewName.setText(currentContainer.getNames().get(0));
                if(currentContainer.getState().equals(getString(R.string.info_running))){
                    // container running
                    statusIncicator.setBackgroundColor(
                            ContextCompat.getColor(getContext(), R.color.containersRunning));
                }else{
                    // container not running
                    statusIncicator.setBackgroundColor(
                            ContextCompat.getColor(getContext(), R.color.containersStopped));
                }

                // more button
                ImageButton more = view.findViewById(R.id.buttonMore);

                more.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        PopupMenu popup = new PopupMenu(v.getContext(), v);
                        popup.inflate(R.menu.popup_menu_container);
                        // another anonymous class
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.container_popup_inspect:
                                        Toast.makeText(getContext(), "pressed inspect on " + currentContainer.getNames().get(0),
                                                Toast.LENGTH_SHORT).show();
                                        return true;
                                    case R.id.container_popup_stop:
                                        return true;
                                    default:
                                        return false;
                                }
                            }
                        });
                        popup.show();
                    }
                });

                return view;
            }
        };

        Bundle arguments = getArguments();
        if(arguments != null) {
            Integer position = getArguments().getInt(ConnectionActivity.KEY_POSITION, 0);
            activeConnection = Connection.listAll(Connection.class).get(position);
            LoadContainers();
        } else
            Toast.makeText(getContext(), "Fatal issue: No Arguments", Toast.LENGTH_SHORT).show();

        listViewContainers.setAdapter(containerArrayAdapter);

    }

    public void LoadContainers() {
        DockerCommandBuilder containersCommand = new DockerCommandBuilder()
                .apiEndpoint("/containers/json")
                .requestMethod("GET");

        activeConnection.executeCommand(this, containersCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        DockerContainer[] dContainers = DockerObjParser.Containers(command.getResult());

        for(DockerContainer singleContainer : dContainers)
            containers.add(singleContainer);

        containerArrayAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Amount of containers: " + dContainers.length, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        //Toast.makeText(getContext(), (!commandExecutionSummary.exececutedWithExceptions()) ? "Command executed successfully!" : "Command couldn't be executed", Toast.LENGTH_SHORT).show();
    }
}
