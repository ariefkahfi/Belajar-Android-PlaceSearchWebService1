package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice;

import android.Manifest;
import android.content.Intent;
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

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler.BaseDialog;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler.ChooseAndViewNearbyATMDialogMaker;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler.ListDialogForAtmNamesAndAddress;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler.NearbyATMListDialogMaker;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.AsyncResponse;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.MyJson;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener,LocationListener {

    private GoogleMap mMap;
    private Button atmAcak, atmTerdekat,listAtm;


    private LocationManager locationManager;


    private boolean doneActivityResult ;

    //private double lat, lng;


    private Location location;

    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        atmAcak = (Button) findViewById(R.id.acakATM);
        atmTerdekat = (Button) findViewById(R.id.nearbyATM);
        listAtm = (Button)findViewById(R.id.listAtm);

        atmAcak.setOnClickListener(this);
        atmTerdekat.setOnClickListener(this);
        listAtm.setOnClickListener(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
            return;
        }

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }


    /*
        Penggunaan LocationListener
           -> locationManager.requestLocationUpdates(....);
           -> locationManager.getLastKnownLocation();
    */


    private void cariATMTerdekatDiLokasiAnda() {
        latLng = new LatLng(location.getLatitude(),location.getLongitude());
        NearbyATMListDialogMaker atm = new NearbyATMListDialogMaker(MapsActivity.this,"Nearby ATM",R.layout.dialog_atm_lokasi_anda,new LatLng(location.getLatitude(),location.getLongitude()),mMap);
        atm.buildMe();

        doneActivityResult = true;

    }

    private void pilihLokasiDanCariATMTerdekat() {
        try {
            Intent inten = new PlacePicker.IntentBuilder().build(MapsActivity.this);
            startActivityForResult(inten,1);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if(requestCode == 1 && resultCode== RESULT_OK){
                    Place place = PlacePicker.getPlace(MapsActivity.this,data);


                    mMap.clear();

                    latLng = place.getLatLng();

                    ChooseAndViewNearbyATMDialogMaker cAndView =
                            new ChooseAndViewNearbyATMDialogMaker(MapsActivity.this,place.getLatLng(),mMap);
                    cAndView.buildMe();
                    doneActivityResult = true;
                    Toast.makeText(this, "Pemilihan lokasi sukses", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Pemilihan lokasi dibatalkan", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nearbyATM :
                cariATMTerdekatDiLokasiAnda();
                break;
            case R.id.acakATM :
                pilihLokasiDanCariATMTerdekat();
                break;
            case  R.id.listAtm :
                showAtmList();
                break;

        }
    }


    public void  showAtmList(){
        if(doneActivityResult){
            ListDialogForAtmNamesAndAddress listDialog = new ListDialogForAtmNamesAndAddress(MapsActivity.this,"Atm List",R.layout.dialog_atm_list,latLng);
            listDialog.buildMe();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
