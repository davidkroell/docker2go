package at.htl_villach.docker2go;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class TabInformation extends Fragment implements Connection.onCommandStatusChangeListener, SwipeRefreshLayout.OnRefreshListener {

    TextView textViewOperatingSystem, textViewServerVersion, textViewMemory, textViewNumCPUs,
            textViewSwarmStatus, textViewSwarmNodeType, textViewNodeAddr, textViewNodeId;
    SwipeRefreshLayout swipeRefreshLayout;
    PieChart pieChart;
    OverviewActivity parentActivity;

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
        textViewNumCPUs = view.findViewById(R.id.textViewNumCPUs);

        // swarm text fields
        textViewSwarmStatus = view.findViewById(R.id.textViewSwarmStatus);
        textViewSwarmNodeType = view.findViewById(R.id.textViewSwarmNodeType);
        textViewNodeAddr = view.findViewById(R.id.textViewSwarmNodeAddr);
        textViewNodeId = view.findViewById(R.id.textViewSwarmNodeId);


        // swipe to refresh
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        pieChart = view.findViewById(R.id.piechartContainers);

        // get parent activity
        parentActivity = (OverviewActivity) getActivity();
        loadInfo();
    }

    // helper functions
    public void loadInfo() {
        DockerCommandBuilder infoCommand = new DockerCommandBuilder()
                .apiEndpoint("/info")
                .requestMethod("GET");

        parentActivity.activeConnection.executeCommand(this, infoCommand);
    }

    public void loadChart(DockerInfo dInfo) {
        // containers running, paused, stopped
        List<Entry> yvalues = new ArrayList<>();
        List<String> labels = new ArrayList<String>();

        yvalues.add(new Entry(dInfo.getContainersRunning(), 0));
        labels.add(getResources().getString(R.string.info_running));

        if (dInfo.getContainersPaused() != 0) {
            yvalues.add(new Entry(dInfo.getContainersPaused(), 1));
            labels.add(getResources().getString(R.string.info_paused));
        }
        if (dInfo.getContainersStopped() != 0) {
            yvalues.add(new Entry(dInfo.getContainersStopped(), 2));
            labels.add(getResources().getString(R.string.info_stopped));
        }

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        dataSet.setValueTextSize(10f);

        // for integer values instead of floats on view
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                // little hack
                return ((int) value) + "";
            }
        });

        // set custom colors
        int[] colors = new int[]{
                ContextCompat.getColor(getContext(), R.color.containersRunning),
                ContextCompat.getColor(getContext(), R.color.containersPaused),
                ContextCompat.getColor(getContext(), R.color.containersStopped)
        };
        dataSet.setColors(colors);

        PieData data = new PieData(labels, dataSet);

        pieChart.setData(data);

        // text in the middle
        String middleStr = String.format("%d/%d \n %s",
                dInfo.getContainersRunning(),
                dInfo.getContainers(),
                getResources().getString(R.string.info_running));
        pieChart.setCenterText(middleStr);
        pieChart.setCenterTextSize(14f);

        // disable touch
        pieChart.setTouchEnabled(false);
        pieChart.setTransparentCircleRadius(0);

        // remove description
        pieChart.setDescription("");

        // render legend on the right
        pieChart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        //pieChart.getLegend().setTextSize(12f);

        // make it render instantly
        pieChart.invalidate();
    }

    @Override
    public void onCommandFinished(Command command) {
        DockerObj dObj = DockerObjParser.Any((DockerCommandBuilder) command);

        if (dObj instanceof DockerInfo) {
            DockerInfo dInfo = (DockerInfo) dObj;

            //System
            textViewOperatingSystem.setText(dInfo.getOperatingSystem());
            textViewServerVersion.setText(dInfo.getServerVersion());
            textViewMemory.setText(Utilities.formatBytes(dInfo.getMemTotal()));
            textViewNumCPUs.setText(Integer.toString(dInfo.getNCPU()));

            // swarm
            DockerInfo.SwarmBean swarm = dInfo.getSwarm();
            textViewSwarmStatus.setText(swarm.getLocalNodeState());

            if (swarm.getLocalNodeState().equals(getString(R.string.info_isactive))) {
                if (swarm.isControlAvailable())
                    textViewSwarmNodeType.setText(getString(R.string.info_swarm_master));
                else
                    textViewSwarmNodeType.setText(getString(R.string.info_swarm_worker));
                LinearLayout ll = (LinearLayout) textViewSwarmNodeType.getParent();
                ll.setVisibility(View.VISIBLE);

                textViewNodeAddr.setText(swarm.getNodeAddr());
                ll = (LinearLayout) textViewNodeAddr.getParent();
                ll.setVisibility(View.VISIBLE);

                textViewNodeId.setText(swarm.getNodeID());
                ll = (LinearLayout) textViewNodeId.getParent();
                ll.setVisibility(View.VISIBLE);
            } else {
                // hide views
                LinearLayout ll = (LinearLayout) textViewSwarmNodeType.getParent();
                ll.setVisibility(View.GONE);

                ll = (LinearLayout) textViewNodeAddr.getParent();
                ll.setVisibility(View.GONE);

                ll = (LinearLayout) textViewNodeId.getParent();
                ll.setVisibility(View.GONE);
            }

            // render chart with docker info
            loadChart(dInfo);
        }
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        swipeRefreshLayout.setRefreshing(false);
        // show error in snackbar
        if (commandExecutionSummary.exececutedWithExceptions())
            Snackbar.make(swipeRefreshLayout,
                    commandExecutionSummary.getLatestException().getLocalizedMessage(),
                    Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadInfo();
    }
}
