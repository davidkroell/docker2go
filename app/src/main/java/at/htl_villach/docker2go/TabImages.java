package at.htl_villach.docker2go;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabImages extends Fragment implements Connection.onCommandStatusChangeListener {

    ListView listViewImages;
    Connection activeConnection;
    ArrayAdapter<DockerImage> imageArrayAdapter;
    ArrayList<DockerImage> images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_images_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listViewImages = view.findViewById(R.id.listViewImages);
        images = new ArrayList<DockerImage>();

        imageArrayAdapter = new ArrayAdapter<DockerImage>(this.getContext(), R.layout.list_item_image, R.id.textViewImageName, images) {

            @Override
            public View getView(int position, View v, ViewGroup parent){
                View view = super.getView(position, v, parent);
                TextView textViewName = view.findViewById(R.id.textViewImageName);

                DockerImage currentImage = images.get(position);

                if(currentImage.getRepoTags() != null)
                    textViewName.setText(currentImage.getRepoTags().get(0));
                else
                    textViewName.setText(currentImage.getId());

                return view;
            }
        };

        Bundle arguments = getArguments();
        if(arguments != null) {
            Integer position = getArguments().getInt(ConnectionActivity.KEY_POSITION, 0);
            activeConnection = Connection.listAll(Connection.class).get(position);
            LoadImages();
        } else
            Toast.makeText(getContext(), "Fatal issue: No Arguments", Toast.LENGTH_SHORT).show();

        listViewImages.setAdapter(imageArrayAdapter);

    }

    public void LoadImages() {
        DockerCommandBuilder imagesCommand = new DockerCommandBuilder()
                .apiEndpoint("/images/json")
                .requestMethod("GET");

        activeConnection.executeCommand(this, imagesCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        DockerImage[] dImages = DockerObjParser.Images(command.getResult());

        for(DockerImage singleImage : dImages)
            images.add(singleImage);

        imageArrayAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Amount of containers: " + dImages.length, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        //Toast.makeText(getContext(), (!commandExecutionSummary.exececutedWithExceptions()) ? "Command executed successfully!" : "Command couldn't be executed", Toast.LENGTH_SHORT).show();
    }
}
