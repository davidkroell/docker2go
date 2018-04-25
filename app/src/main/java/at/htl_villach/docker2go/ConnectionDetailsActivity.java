package at.htl_villach.docker2go;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import at.htl_villach.docker2go.databinding.ActivityConnectionDetailsBinding;

public class ConnectionDetailsActivity extends AppCompatActivity {

    private ActivityConnectionDetailsBinding uiBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBind = DataBindingUtil.setContentView(this,R.layout.activity_connection_details);

    }


    private void onClick_buttonSave(View v){

        Connection c = new Connection(
                uiBind.editTextHostname.getText().toString(),
                uiBind.editTextUsername.getText().toString(),
                uiBind.editTextPassword.getText().toString(),
                Integer.parseInt(uiBind.editTextPort.getText().toString())
        );
        c.save();
        finish();
    }
}
