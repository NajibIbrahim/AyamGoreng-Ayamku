package com.example.abdie.ayamgorengayamku;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private List<Ayam> listAyam;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView totalPrice;
    public String number;
    private double price = 0;
    //Volley Request Queue
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        totalPrice = (TextView) findViewById(R.id.totalPrice);
        totalPrice.setText(String.valueOf(price));




        //Initializing our superheroes list
        listAyam = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        //Calling method to get data to fetch data
        getData();


        adapter = new AyamAdapter(listAyam, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Ayam ayam = listAyam.get(position);
                price = price + Double.parseDouble(ayam.getPrice());
                totalPrice.setText(String.valueOf(price));
            }

            @Override
            public void onLongClick(View view, int position) {

                Intent intent = new Intent(getApplicationContext(),TampilBarang.class);
                final Ayam ayam = listAyam.get(position);
                String nama = ayam.getName().toString();
                Toast.makeText(getApplicationContext(),nama + " Is Clicked",Toast.LENGTH_LONG).show();
                 intent.putExtra(Config.EMP_ID,nama);
                startActivity(intent);
            }
        }));
        totalPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total = totalPrice.getText().toString();
                Intent harga = new Intent(getApplicationContext(),TotalPembayaran.class);
                harga.putExtra("mytext",total);
                startActivity(harga);

            }
        });

    }






    //Request to get json from server we are passing an integer here
    //This integer will used to specify the page number for the request ?page = requestcount
    //This method would return a JsonArrayRequest that will be added to the request queue
    private JsonArrayRequest getDataFromServer() {
        //Initializing ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        //Displaying Progressbar
        progressBar.setVisibility(View.VISIBLE);
        setProgressBarIndeterminateVisibility(true);

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.DATA_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Calling method parseData to parse the json response
                parseData(response);
                //Hiding the progressbar
                progressBar.setVisibility(View.GONE);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(MainActivity.this, "No More Items Available", Toast.LENGTH_SHORT).show();
                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    //This method will get data from the web api
    private void getData() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getDataFromServer());
        //Incrementing the request counter
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            Ayam ayam = new Ayam();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object
                ayam.setImageUrl(json.getString(Config.TAG_IMAGE_URL));
                ayam.setName(json.getString(Config.TAG_NAME));
                ayam.setPrice(json.getString(Config.TAG_PRICE));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            listAyam.add(ayam);
        }

        //Notifying the adapter that data has been added or changed
        adapter.notifyDataSetChanged();
    }

    //This method would check that the recyclerview scroll has reached the bottom or not
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        User user = SharedPrefManager.getInstance(this).getUser();
        String number = user.getNumber().toString();

        switch (item.getItemId()) {
            case R.id.action_maps:
                Intent maps = new Intent();
                maps.setAction(Intent.ACTION_VIEW);
                // Using the coordinates for Google headquarters.
                String data = "geo:"+user.getLocation();
                maps.setData(Uri.parse(data));
                if (maps.resolveActivity(getPackageManager()) != null) {
                    startActivity(maps);
                }
                return true;
            case R.id.action_call:
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:"+number));
                startActivity(call);
                return true;
            case R.id.action_update:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_sms:
                Intent sms = new Intent(Intent.ACTION_VIEW);
                sms.setData(Uri.parse("sms:"+number));
                startActivity(sms);
                return true;
            case R.id.action_logout:
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
