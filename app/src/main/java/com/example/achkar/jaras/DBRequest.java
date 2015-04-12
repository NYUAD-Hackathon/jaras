package com.example.achkar.jaras;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;


public class DBRequest extends AsyncTask<Object, Void, Object> {

    // additional time allowed to be late
    private static final String SCRIPTS_SERVER_IP_ADDRESS = "54.173.48.45/jaras/post.php";


    //keep context and activity DBRequest has been accessed from
    private Context context;
    private Activity activity;

    int codeOfRequest;

    ArrayList<HashMap<String, String>> messagesList;

    public DBRequest(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //pDialog.show();
    }

    @Override
    protected Object doInBackground(Object... params) {

        codeOfRequest = (Integer) params[1];

        switch (codeOfRequest) {
            case 0:
                submitPost(params);
                return null;
            case 1:
                return retrievePosts(params);
            default:
                return null;
        }

    }

    protected void onPostExecute(Object set) {
        super.onPostExecute(set);

        //h.post(r);				
        switch (codeOfRequest) {
            case 0:
                break;
            case 1:
                displayPosts((ArrayList<HashMap<String, Object>>) set);
                break;
            default:
                break;
        }

    }

    public void submitPost(Object... params){

        JSONParser jsonParser = new JSONParser();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("name", "Mark"));
        parameters.add(new BasicNameValuePair("message",(String) params[2]));
        parameters.add(new BasicNameValuePair("latitude", "20.134567"));
        parameters.add(new BasicNameValuePair("longitude", "54.543246"));
        parameters.add(new BasicNameValuePair("country", "United Arab Emirates"));
        parameters.add(new BasicNameValuePair("city", "Mark"));
        parameters.add(new BasicNameValuePair("title", (String) params[3]));

        jsonParser.makeHttpRequest("http://54.173.48.45/jaras/post.php", "POST", parameters);
    }

    public ArrayList<HashMap<String, Object>> retrievePosts(Object... params){

        JSONParser jsonParser = new JSONParser();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("country", String.valueOf(params[2])));
        JSONObject json = jsonParser.makeHttpRequest("http://54.173.48.45/jaras/retrieve_posts.php", "GET", parameters);

        ArrayList<HashMap<String, Object>> postsList = new ArrayList<>();

        try{
            int success = json.getInt("success");

            if (success == 1){

                JSONArray posts = json.getJSONArray("data");
                for (int i =0; i < posts.length(); i++){
                    JSONObject c = posts.getJSONObject(i);
                    String message = c.getString("message");
                    String title = c.getString("title");

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("message", message);
                    map.put("title", title);
                    postsList.add(map);
                }
            }

        } catch (Exception e){}

        return postsList;
    }

    public void displayPosts(ArrayList<HashMap<String, Object>> set){

        Intent goIntent = new Intent(activity, SeePost.class);
        goIntent.putExtra("messages_list", set);
        context.startActivity(goIntent);

    }

}