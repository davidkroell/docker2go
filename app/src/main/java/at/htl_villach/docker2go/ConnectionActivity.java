package at.htl_villach.docker2go;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ConnectionActivity extends AppCompatActivity {

    // constants
    public static final String KEY_CONN_ID = "CONNECTION_ID";

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
                R.id.textViewHost, Connection.listAll(Connection.class)) {

            @Override
            public View getView(final int position, View v, ViewGroup parent) {
                // define fields
                View view = super.getView(position, v, parent);
                TextView textViewHost = view.findViewById(R.id.textViewHost);
                TextView textViewUser = view.findViewById(R.id.textViewUser);
                TextView textViewOs = view.findViewById(R.id.textViewOperatingSystem);
                TextView textViewDescription = view.findViewById(R.id.textViewDescription);
                ImageView imageView = view.findViewById(R.id.connectionThumbnail);

                final Connection currConn = Connection.listAll(Connection.class).get(position);

                textViewHost.setText(currConn.getHostname());
                textViewUser.setText(currConn.getUsername());

                String osStr = currConn.getOperatingSystem();
                if (osStr == null || osStr.equals(""))
                    osStr = getString(R.string.connection_unkown_os);
                textViewOs.setText(osStr);

                // set image view drawable depending on operating system
                osStr = osStr.toLowerCase();
                if (osStr.contains("debian"))
                    imageView.setImageResource(R.drawable.debian);
                else if (osStr.contains("ubuntu"))
                    imageView.setImageResource(R.drawable.ubuntu);
                else if (osStr.contains("fedora"))
                    imageView.setImageResource(R.drawable.fedora);
                else if (osStr.contains("centos"))
                    imageView.setImageResource(R.drawable.centos);

                textViewDescription.setText(
                        String.format(getString(R.string.connection_description),
                                currConn.getTimesConnected(), currConn.getSshPort())
                );

                // listeners for buttons
                view.findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ConnectionDetailsActivity.class);
                        intent.putExtra(KEY_CONN_ID, currConn.getId());
                        startActivity(intent);
                    }
                });

                view.findViewById(R.id.buttonConnect).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getContext(), OverviewActivity.class);
                        i.putExtra(KEY_CONN_ID, currConn.getId());
                        startActivity(i);
                    }
                });

                return view;
            }
        };
        listViewConnections = findViewById(R.id.listViewConnections);

        listViewConnections.setAdapter(connectionArrayAdapter);
    }

    private void addConnection(View v) {
        Intent i = new Intent(this, ConnectionDetailsActivity.class);
        startActivity(i);
    }

    private void refreshGUI() {
        connectionArrayAdapter.clear();
        connectionArrayAdapter.addAll(Connection.listAll(Connection.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshGUI();
    }
}
