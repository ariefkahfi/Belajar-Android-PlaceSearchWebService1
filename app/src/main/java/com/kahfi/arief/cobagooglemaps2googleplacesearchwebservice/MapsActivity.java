package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler.BaseDialog;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler.NearbyATMListDialogMaker;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.AsyncResponse;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.MyJson;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Button atmAcak, atmTerdekat;


    private LocationManager locationManager;


    private String lat,lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);



        atmAcak = (Button) findViewById(R.id.acakATM);
        atmTerdekat = (Button) findViewById(R.id.nearbyATM);

        atmAcak.setOnClickListener(this);
        atmTerdekat.setOnClickListener(this);

        setPermissions();


        mapFragment.getMapAsync(this);
    }

    private void setPermissions(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission
                        (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET},1);

            return;
        }

        //locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }


    private void cariATMTerdekatDiLokasiAnda() {
        //new AsyncResponse().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-3.6608916,103.7694693&radius=1000&type=atm&key=AIzaSyAR6WPg06EYG94R2Q1Oq9AAovRsDJCEDjU");

        NearbyATMListDialogMaker dm = new NearbyATMListDialogMaker(MapsActivity.this,"a",R.layout.dialog_atm_lokasi_anda);

        dm.buildMe();

        //AlertDialog.Builder d= new AlertDialog.Builder(MapsActivity.this);

    }

    private void pilihLokasiDanCariATMTerdekat() {
        if(this.lat!=null && this.lng!=null){

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nearbyATM :
                cariATMTerdekatDiLokasiAnda();
                break;
            case R.id.acakATM :
                Log.e("Test","OK");
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
