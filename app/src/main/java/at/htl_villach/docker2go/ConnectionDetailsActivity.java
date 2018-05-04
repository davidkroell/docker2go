package at.htl_villach.docker2go;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import at.htl_villach.docker2go.databinding.ActivityConnectionDetailsBinding;

public class ConnectionDetailsActivity extends AppCompatActivity {

    private ActivityConnectionDetailsBinding uiBind;
    private Connection editingConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBind = DataBindingUtil.setContentView(this,R.layout.activity_connection_details);

        // get intent data if item was clicked
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer position = extras.getInt(ConnectionActivity.KEY_POSITION, 0);
            editingConnection = Connection.listAll(Connection.class).get(position);

            uiBind.editTextHostname.setText(editingConnection.getHostname());
            uiBind.editTextUsername.setText(editingConnection.getUsername());
            uiBind.editTextPort.setText(editingConnection.getSshPort().toString());
        }
    }

    // load menu to show on activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu and add it to the existing (empty) menu
        if(editingConnection == null)
            // connection is beeing created
            getMenuInflater().inflate(R.menu.menu_connection_details_create, menu);
        else
            // connection is beeing edited
            getMenuInflater().inflate(R.menu.menu_connection_details_edit, menu);
        return true;
    }

    // listener for menu item
    public void onMenuClick_Cancel(MenuItem m){
        finish();
    }

    // listener for menu item
    public void onMenuClick_Delete(MenuItem m){
        if(editingConnection != null) // workaround if
            editingConnection.delete();
        finish();
    }

    public void onClick_buttonSave(View v) {

        if (editingConnection == null) {
            Connection c = new Connection(
                    uiBind.editTextHostname.getText().toString(),
                    uiBind.editTextUsername.getText().toString(),
                    uiBind.editTextPassword.getText().toString(),
                    Integer.parseInt(uiBind.editTextPort.getText().toString())
            );
            c.save();
        }else{
            editingConnection.setHostname(uiBind.editTextHostname.getText().toString());
            editingConnection.setUsername(uiBind.editTextUsername.getText().toString());
            editingConnection.setPassword(uiBind.editTextPassword.getText().toString());
            editingConnection.setSshPort(Integer.parseInt(uiBind.editTextPort.getText().toString()));
            editingConnection.save(); // store to database
        }
        finish();
    }
}
