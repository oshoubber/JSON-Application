package me.oshoubber.jsonapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class jsonActivity extends AppCompatActivity {
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView recyclerView;
    private LocationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_activity);
        //Make call to AsyncTask
        new AsyncFetch().execute();
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(jsonActivity.this);
        HttpURLConnection conn;
        String temp = null;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                temp  = URLEncoder.encode("https://guidebook.com/service/v2/upcomingGuides/", "UTF-8");
                url = new URL(temp);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                conn.setDoOutput(true);

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();
                Log.e("RESPONSECODE: ", "" + response_code);



                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());


            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("JSON", result);
            //this method will be running on UI thread

            pdLoading.dismiss();
            List<LocationData> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    LocationData locationData = new LocationData();

                    locationData.startDate= json_data.getString("startDate");
                    locationData.objType= json_data.getString("objType");
                    locationData.endDate= json_data.getString("endDate");
                    locationData.name= json_data.getString("name");
                    locationData.loginRequired= json_data.getString("loginRequired");
                    locationData.url= json_data.getString("venue");
                    locationData.icon= json_data.getString("icon");
                    data.add(locationData);
                }

                // Setup and Handover data to recyclerview
                recyclerView = findViewById(R.id.recyclerView);
                mAdapter = new LocationAdapter(jsonActivity.this, data);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(jsonActivity.this));

            } catch (JSONException e) {
                Toast.makeText(jsonActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}





