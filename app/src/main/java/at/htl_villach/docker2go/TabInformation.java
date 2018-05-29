package at.htl_villach.docker2go;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class TabInformation extends Fragment implements Connection.onCommandStatusChangeListener {

    static TextView textViewOperatingSystem, textViewServerVersion, textViewMemory;
    static Connection activeConnection;
    PieChart pieChart;

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

        pieChart = view.findViewById(R.id.piechartContainers);

        Bundle arguments = getArguments();
        if(arguments != null) {
            Integer position = getArguments().getInt(ConnectionActivity.KEY_POSITION, 0);
            activeConnection = Connection.listAll(Connection.class).get(position);
            LoadInfo();
        } else
            Toast.makeText(getContext(), "Fatal issue: No Arguments", Toast.LENGTH_SHORT).show();
    }

    // helper functions
    public void LoadInfo() {
        DockerCommandBuilder infoCommand = new DockerCommandBuilder()
                .apiEndpoint("/info")
                .requestMethod("GET");

        activeConnection.executeCommand(this, infoCommand);
    }

    public void loadChart(DockerInfo dInfo){
        // containers running, paused, stopped
        List<Entry> yvalues = new ArrayList<>();
        List<String> labels = new ArrayList<String>();

        yvalues.add(new Entry(dInfo.getContainersRunning(), 0));
        labels.add(getResources().getString(R.string.info_running));

        if(dInfo.getContainersPaused() != 0) {
            yvalues.add(new Entry(dInfo.getContainersPaused(), 1));
            labels.add(getResources().getString(R.string.info_paused));
        }
        if(dInfo.getContainersStopped() != 0) {
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

        PieData data = new PieData(labels, dataSet);

        pieChart.setData(data);

        // set custom colors
        int[] colors = new int[]{
                ContextCompat.getColor(getContext(), R.color.containersRunning),
                ContextCompat.getColor(getContext(), R.color.containersPaused),
                ContextCompat.getColor(getContext(), R.color.containersStopped)
        };
        dataSet.setColors(colors);

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
        pieChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        pieChart.getLegend().setTextSize(12f);

        // make it render instantly
        pieChart.invalidate();
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

            // render chart with docker info
            loadChart(dInfo);

        }
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary commandExecutionSummary) {
        //Toast.makeText(getContext(), (!commandExecutionSummary.exececutedWithExceptions()) ? "Command executed successfully!" : "Command couldn't be executed", Toast.LENGTH_SHORT).show();
    }
}
