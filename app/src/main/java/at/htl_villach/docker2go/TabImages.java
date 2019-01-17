package at.htl_villach.docker2go;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TabImages extends Fragment implements Connection.onCommandStatusChangeListener, SwipeRefreshLayout.OnRefreshListener {

    ListView listViewImages;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayAdapter<DockerImage> imageArrayAdapter;
    ArrayList<DockerImage> images;
    OverviewActivity parentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_images_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //setup controls
        listViewImages = view.findViewById(R.id.listViewImages);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        //setup list
        images = new ArrayList<>();

        // swipe to refresh
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        //setup arrayadapter
        imageArrayAdapter = new ArrayAdapter<DockerImage>(this.getContext(), R.layout.list_item_image, R.id.textViewRepository, images) {

            @NonNull
            @Override
            public View getView(int position, View v, @NonNull ViewGroup parent) {
                View view = super.getView(position, v, parent);
                TextView textViewName = view.findViewById(R.id.textViewRepository);
                TextView textViewTag = view.findViewById(R.id.textViewTag);
                TextView textViewCreated = view.findViewById(R.id.textViewCreated);
                TextView textViewSize = view.findViewById(R.id.textViewSize);

                DockerImage currentImage = images.get(position);

                // tags may be empty
                if (currentImage.getRepoTags() != null) {
                    String firstTag = currentImage.getRepoTags().get(0);

                    String[] parts = firstTag.split(":");
                    textViewName.setText(parts[0]);

                    // show short id in braces
                    textViewTag.setText(parts[1] + " (" + currentImage.getId(12) + ")");
                } else {
                    textViewName.setText(currentImage.getId());
                }

                textViewCreated.setText(
                        Utilities.timeElapsedString(
                                new Date((long) currentImage.getCreated() * 1000),
                                getResources().getStringArray(R.array.date_types),
                                getString(R.string.date_type_past_wrapper)));


                textViewSize.setText(Utilities.formatBytes(currentImage.getSize()));

                return view;
            }
        };
        parentActivity = (OverviewActivity) getActivity();

        loadImages();
        listViewImages.setAdapter(imageArrayAdapter);
    }

    public void loadImages() {
        DockerCommandBuilder imagesCommand = new DockerCommandBuilder()
                .apiEndpoint("/images/json")
                .requestMethod("GET");

        parentActivity.activeConnection.executeCommand(this, imagesCommand);
    }

    @Override
    public void onCommandFinished(Command command) {
        if (command.exitedAsExpected()) {

            DockerImage[] dImages = DockerObjParser.Images(command.getResult());

            images.clear();

            images.addAll(Arrays.asList(dImages));

            imageArrayAdapter.notifyDataSetChanged();
            OverviewActivity oa = (OverviewActivity) getActivity();
            oa.loadingIndicator.setVisibility(View.GONE);
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
        loadImages();
    }
}
