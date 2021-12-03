package com.example.cakeorderingapp;
//This is the final Project and this
// is an APP which you use for ordering a Cake with frosting.
// Also this have login feature and account creation as well.
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements LocationListener {
    Button locBtn;
    TextView locationvalue;
    Button mapsApp; /** Button added to open it in Google Maps App */
    Uri uriForIntent; /** URI for the google maps query  */
    LocationManager locationManager;


    private final static int CODE_ASK_APPROVAL = 1;


    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        checkPermissions();
        locBtn =  findViewById(R.id.getLocationBtn);
        locationvalue = findViewById(R.id.locationText);
        mapsApp = findViewById(R.id.showInMapsBtn);  /* Declaring and assigning the new added button (RJ)*/
        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        mapsApp.setOnClickListener(new View.OnClickListener() {
            /**
             *  Opens the location in the google maps app using the coordinates
             *  Reference - https://developers.google.com/maps/documentation/urls/android-intent
             * */
            @Override
            public void onClick(View view) {
                if(uriForIntent !=null){
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, uriForIntent);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    } else{
                        Toast.makeText(MapsActivity.this, "No Apps to handle maps request", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MapsActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        locationvalue.setText("Current Location: " + location.getLatitude() + ", " + location.getLongitude());
        /**
         * Utilizing the above coordinates to set the query parameters.
         * Reference - https://developers.google.com/maps/documentation/urls/android-intents
         * */
        uriForIntent = Uri.parse("geo:"+location.getLatitude()+","+location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MapsActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    /**
     * Checks the dynamically-controlled permissions and requests missing permissions from end user.
     */
    protected void checkPermissions() {
        final List <String> missingPermissions = new ArrayList <String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, CODE_ASK_APPROVAL);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(CODE_ASK_APPROVAL, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODE_ASK_APPROVAL:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted

                break;
        }
    }

    public void onMapReady(GoogleMap googleMap) {

        LatLng sbux = new LatLng(41.8308138,-87.6270885);
        googleMap.addMarker(new MarkerOptions()
                .position(sbux)
                .title("Starbucks"));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sbux));

        LatLng mickeyd = new LatLng(41.8308352,-87.6211466);
        googleMap.addMarker(new MarkerOptions()
                .position(mickeyd)
                .title("McDonald's"));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mickeyd));

        LatLng redline = new LatLng(41.834571838378906,-87.632568359375);
        googleMap.addMarker(new MarkerOptions()
                .position(redline)
                .title("Red Line @ Stix n Brix"));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(redline));

        googleMap.setMinZoomPreference(14.0f);
        googleMap.setMaxZoomPreference(22.0f);
        // [END_EXCLUDE]
    }

}