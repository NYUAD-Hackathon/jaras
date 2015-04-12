package com.example.achkar.jaras;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener {


    private MobileServiceClient mClient;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ImageButton b2;
    private Button b3;
    private ImageButton b4;
    private EditText eText, eTextTitle;
    public Activity activity;
    private Button b5;
    private TextView mTapTextView;
    private String country = "United Arab Emirates";
    String currentpost, currentTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        setUpMapIfNeeded();

        activity = this;

        final FrameLayout f1 = (FrameLayout) findViewById(R.id.postext);
        b2 = (ImageButton) findViewById(R.id.imageButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1.setVisibility(View.VISIBLE);

            }

        });

        b4 = (ImageButton) findViewById(R.id.closebutton);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1.setVisibility(View.GONE);
            }

        });



        b3 = (Button) findViewById(R.id.ringbutton);
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                eText = (EditText) findViewById(R.id.postwindow);
                eTextTitle = (EditText) findViewById(R.id.titleaction);
                currentpost = String.valueOf(eText.getText());
                currentTitle = String.valueOf(eTextTitle.getText());
                new DBRequest(activity, activity).execute("post message", 0, currentpost, currentTitle);
            }
        });

        //mTapTextView = (TextView) findViewById(R.id.);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                country = getCountry(latLng);
                new DBRequest(activity, activity).execute("retrieve posts", 1, country);
            }
        });


    }


    @Override
    public void onMapClick(LatLng point) {
        //mTapTextView.setText("tapped, point=" + point);

    }

    public String getCountry(LatLng point){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try{
            List<Address> addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1);
            Address obj = addresses.get(0);
            //String add = obj.getAddressLine(0);
            //GUIStatics.currentAddress = obj.getSubAdminArea() + "," + obj.getAdminArea();
            //GUIStatics.latitude = obj.getLatitude();
            //GUIStatics.longitude = obj.getLongitude();
            //GUIStatics.currentCity= obj.getSubAdminArea();
            //GUIStatics.currentState= obj.getAdminArea();
            //add = add + "\n" + obj.getCountryName();
            //add = add + "\n" + obj.getCountryCode();
            //add = add + "\n" + obj.getAdminArea();
            //add = add + "\n" + obj.getPostalCode();
            //add = add + "\n" + obj.getSubAdminArea();
            //add = add + "\n" + obj.getLocality();
            //add = add + "\n" + obj.getSubThoroughfare();

            //Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);

            return obj.getCountryName();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            return null;
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
