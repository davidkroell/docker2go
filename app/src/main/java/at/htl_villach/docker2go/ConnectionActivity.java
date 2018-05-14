package at.htl_villach.docker2go;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ConnectionActivity extends AppCompatActivity
        implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    // constants
    public static final String KEY_POSITION = "POSITION";

    private ArrayAdapter<Connection> connectionArrayAdapter;
    private ListView listViewConnections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        // floating action button onClick workaround
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addConnection);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConnection(v);
            }
        });

        // listview
        connectionArrayAdapter = new ArrayAdapter<Connection>(this, R.layout.list_item_connection,
                R.id.textViewHostString, Connection.listAll(Connection.class)){

            @Override
            public View getView(int position, View v, ViewGroup parent){
                View view = super.getView(position, v, parent);
                TextView textViewHostString = view.findViewById(R.id.textViewHostString);
                TextView textViewPort = view.findViewById(R.id.textViewSSHPort);

                Connection currConn = Connection.listAll(Connection.class).get(position);

                textViewHostString.setText(currConn.getUsername() + "@" + currConn.getHostname());
                textViewPort.setText(currConn.getSshPort().toString());

                return view;
            }
        };
        listViewConnections = findViewById(R.id.listViewConnections);

        listViewConnections.setAdapter(connectionArrayAdapter);
        listViewConnections.setOnItemLongClickListener(this);
        listViewConnections.setOnItemClickListener(this);
    }

    private void addConnection(View v) {
        Intent i = new Intent(this, ConnectionDetailsActivity.class);
        startActivity(i);
    }

    private void refreshGUI(){
        connectionArrayAdapter.clear();
        connectionArrayAdapter.addAll(Connection.listAll(Connection.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshGUI();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this, ConnectionDetailsActivity.class);
        intent.putExtra(KEY_POSITION, position);
        startActivity(intent);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent i = new Intent(this, OverviewActivity.class);
        i.putExtra(KEY_POSITION, position);
        startActivity(i);
    }
}
