package com.example.achkar.jaras;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

//Azure server
import com.microsoft.windowsazure.mobileservices.*;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class splash extends FragmentActivity {


    private MobileServiceClient mClient;
    private Button splash_button;
    public Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        activity = this;

        splash_button = (Button) findViewById(R.id.button_splash);
        splash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goIntent = new Intent(activity, MapsActivity.class);
                startActivity(goIntent);
            }
        });
    }
}
