package com.example.abdie.ayamgorengayamku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        editTextUsername = (EditText) findViewById(R.id.editUsername);
        editTextPassword = (EditText) findViewById(R.id.editPass);


        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        editTextUsername.setText(user.getUsername());
        editTextPassword.setText(user.getPassword());

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateayam();
            }
        });
    }

    private void updateayam(){
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        class UpdateAyam extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ProfileActivity.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ProfileActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_USERNAME,username);
                hashMap.put(Config.KEY_PASSWORD,password);

                ReqHandler rh = new ReqHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_AYAM,hashMap);

                return s;
            }
        }

        UpdateAyam ue = new UpdateAyam();
        ue.execute();
    }
}
