package com.example.abdie.ayamgorengayamku;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;



public class TampilBarang extends AppCompatActivity{

    private ImageLoader imageLoader;
    private NetworkImageView imageayam;
    private TextView ayamname;
    private TextView ayamdeskripsi;
    private TextView ayamharga;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_barang);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.EMP_ID);

        imageayam = (NetworkImageView) findViewById(R.id.tampilgambar);
        ayamname = (TextView) findViewById(R.id.tampilnama);
        ayamharga = (TextView) findViewById(R.id.tampilharga);
        ayamdeskripsi = (TextView) findViewById(R.id.tampildeskripsi);


        ayamname.setText("Nama :"+id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilBarang.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String deskripsi = c.getString(Config.TAG_DESKRIPSI);
            String harga = c.getString(Config.TAG_PRICE);
            String gambar = c.getString(Config.TAG_IMAGE_URL);
            imageLoader = CustomVolleyRequest.getInstance(getApplicationContext()).getImageLoader();
            imageLoader.get(gambar, ImageLoader.getImageListener(imageayam, R.drawable.image, android.R.drawable.ic_dialog_alert));

            ayamharga.setText("Harga : RP. "+harga);
            ayamdeskripsi.setText(deskripsi);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
