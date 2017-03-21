package com.example.ssith123.unoapps.Activity;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.example.ssith123.unoapps.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

public class MapsActivity extends FragmentActivity implements LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 60 * 5; //1 minute
    private static final long FASTEST_INTERVAL = 1000 * 60 * 5; // 1 minute
    Button btnFusedLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    //for database
    SQLiteDatabase db;
    Cursor cursor;
    String name;
    String address;
    String lat;
    String lng;
    String id;
    //String mLastUpdateTime;
    GoogleMap googleMap;
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ...............................");
        //show error dialog if GoolglePlayServices not available

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        googleMap = mapFragment.getMap();
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        db = (new MySurrounding(this)).getWritableDatabase();

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        addMarker();
    }
    private void addMarker() {
        MarkerOptions options = new MarkerOptions();

        // following four lines requires 'Google Maps Android API Utility Library'
        // https://developers.google.com/maps/documentation/android/utility/
        // I have used this to display the time as title for location markers
        // you can safely comment the following four lines but for this info
        IconGenerator iconFactory = new IconGenerator(this);
        iconFactory.setStyle(IconGenerator.STYLE_GREEN);
       // options.icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(mLastUpdateTime)));
        options.anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
        LatLng currentLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
       //  double lat1 = Double.parseDouble(lat);
      //   double lng1 = Double.parseDouble(lng);
      //  LatLng currentLatLng1 = new LatLng(lat1, lng1);
        options.position(currentLatLng);
      //  options.position(currentLatLng1);
        Marker mapMarker = googleMap.addMarker(options);
       // Marker mapMarker1 = googleMap.addMarker(options);
        mapMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
      //  mapMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
       // mapMarker1.setTitle("my place");
//        long atTime = mCurrentLocation.getTime();
//        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date(atTime));
//        mapMarker.setTitle(mLastUpdateTime);
        Log.d(TAG, "Marker added.............................");
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
//                17));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        Log.d(TAG, "Zoom done.............................");
        displayCities();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
    }
    protected void startLocationUpdates() {
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }
    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    @Override
    public void onResume() {
        super.onResume();
       // LocationManager_check();
        // Make sure that GPS is enabled on the device
        LocationManager mlocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!enabled) {
            showDialogGPS();
        }
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }
    private void showDialogGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Enable GPS");
        builder.setMessage("Please enable GPS");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(
                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
   public void displayCities(){

       String selectquery = "SELECT _id,name,address,lat,lng FROM restoran ";
       cursor = db.rawQuery(selectquery, null);
        cursor.moveToFirst() ;
           do {
               id = cursor.getString(cursor.getColumnIndex("_id"));
               name = cursor.getString(cursor.getColumnIndex("name"));
               address = cursor.getString(cursor.getColumnIndex("address"));
               lat = cursor.getString(cursor.getColumnIndex("lat"));
               lng = cursor.getString(cursor.getColumnIndex("lng"));
               double lat1 = Double.parseDouble(lat);
               double lng1 = Double.parseDouble(lng);
               LatLng position = new LatLng(lat1,lng1);
               MarkerOptions options = new MarkerOptions();
               IconGenerator iconFactory = new IconGenerator(this);
               iconFactory.setStyle(IconGenerator.STYLE_GREEN);
               options.anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
               options.position(position);
               Marker mapMarker = googleMap.addMarker(options);
               mapMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
               googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
               googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
           }while (cursor.moveToNext());
       Toast.makeText(MapsActivity.this,id+name+address+lat+lng,Toast.LENGTH_SHORT).show();

       }
   }



