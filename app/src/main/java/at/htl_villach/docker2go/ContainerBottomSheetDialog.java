package at.htl_villach.docker2go;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static at.htl_villach.docker2go.ConnectionActivity.KEY_CONN_ID;

public class ContainerBottomSheetDialog extends BottomSheetDialogFragment {

    private BottomSheetListener mListener;
    private DockerContainer mContainer;
    private int connectionPosition;
    private ArrayAdapter<String> adapterNavigation;
    private ArrayList<String> menuOptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_container, container, false);

        if(getArguments() != null) {
            connectionPosition = getArguments().getInt(KEY_CONN_ID);
        }

        ListView listViewSheetNav = v.findViewById(R.id.listViewSheetNav);

        adapterNavigation = new ArrayAdapter<String>(this.getContext(),
                R.layout.list_item_container_sheet, R.id.textViewItemName, menuOptions) {

            @Override
            public View getView(int position, View v, ViewGroup parent){
                View view = super.getView(position, v, parent);
                TextView textViewItemName = view.findViewById(R.id.textViewItemName);
                ImageView iconView = view.findViewById(R.id.imageIcon);

                textViewItemName.setText(menuOptions.get(position));

                switch(menuOptions.get(position)) {
                    case "Inspect":
                        iconView.setBackgroundResource(R.drawable.ic_remove_red_eye_black_24dp);
                        break;
                    case "Restart":
                        iconView.setBackgroundResource(R.drawable.ic_baseline_cached_24px);
                        break;
                    case "Stop":
                        iconView.setBackgroundResource(R.drawable.ic_stop_black_24dp);
                        break;
                    case "Start":
                        iconView.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                }

                return view;
            }
        };


        TextView textViewContainerTitle = v.findViewById(R.id.textViewContainerTitle);
        textViewContainerTitle.setText(mContainer.getNames().get(0).substring(1));

        TextView textViewImageTitle = v.findViewById(R.id.textViewImageTitle);
        textViewImageTitle.setText(mContainer.getImage());

        listViewSheetNav.setAdapter(adapterNavigation);
        listViewSheetNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.onActionSelected(menuOptions.get(i), mContainer);
                dismiss();
            }
        });

        return v;
    }

    public void setContainer(DockerContainer container) {
        this.mContainer = container;
        menuOptions = new ArrayList<String>();
        menuOptions.add("Inspect");
        if(mContainer.getState().contains("running")) {
            menuOptions.add("Restart");
            menuOptions.add("Stop");
        } else {
            menuOptions.add("Start");
        }
    }

    public void setListener(BottomSheetListener listener) {
        mListener = listener;
    }

    public void showDetails(DockerContainer container) {
        Intent i = new Intent(getActivity(), ContainerDetailActivity.class);
        i.putExtra(KEY_CONN_ID, connectionPosition);
        i.putExtra("ContainerID", container.getId());
        startActivity(i);
    }

    public interface BottomSheetListener {
        void onActionSelected(String action, DockerContainer affectedContainer);
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //try {
            mListener = (BottomSheetListener)context;
        /*} catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }*/
}
