package at.htl_villach.docker2go;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addConnection);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConnection(v);
            }
        });
    }

    private void addConnection(View v) {
        Intent i = new Intent(this, ConnectionDetailsActivity.class);
        startActivity(i);
    }

}
