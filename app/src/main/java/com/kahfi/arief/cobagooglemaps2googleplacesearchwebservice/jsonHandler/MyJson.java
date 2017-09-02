package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.R;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.adapters.AtmAdapter;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler.MyDialogBuilder;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.model.ATM;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Arief on 9/1/2017.
 */

public class MyJson {


    private ListView list;
    private Context context;

    private GoogleMap gMap;

    private LatLng latLng;

    private boolean useGmapMarker;


    public MyJson(){

    }

    public MyJson(Context context , GoogleMap gMap , LatLng latLng){
        this.context=context;
        this.gMap=gMap;
        this.latLng=latLng;
    }

    public MyJson(Context context ,ListView list ){
        this.context=context;
        this.list=list;
    }


    public void readJSONForGoogleMapMarkers(JsonElement el){
        ArrayList<LatLng> latLngs = new ArrayList<>();


        JsonArray result = el.getAsJsonObject().get("results").getAsJsonArray();

        for(JsonElement geoList : result){
            JsonElement geometry = geoList.getAsJsonObject().get("geometry");
            JsonElement location = geometry.getAsJsonObject().get("location");


            LatLng latLng = new LatLng(
                     location.getAsJsonObject().get("lat").getAsDouble()
                    ,location.getAsJsonObject().get("lng").getAsDouble());


            latLngs.add(latLng);
        }
        tambahMarkerKeGMap(latLngs);

    }

    public  void readJSONToListView(JsonElement el) throws Exception{

          ArrayList<ATM> atm = new ArrayList<>();

          JsonArray result = el.getAsJsonObject().get("results").getAsJsonArray();

            for(JsonElement element : result){
                String namaTempat = element.getAsJsonObject().get("name").getAsString();
                String alamat = element.getAsJsonObject().get("vicinity").getAsString();


                Log.e("nama",namaTempat);
                Log.e("alamat",alamat);


                atm.add(new ATM(namaTempat,alamat));
            }
            bacaJsonKeList(atm);
    }

    public void tambahMarkerKeGMap(ArrayList<LatLng> latLngs){
        for(LatLng latLng : latLngs){
            gMap.addMarker(new MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
        setMarkerToMyPosition();
    }

    public void setMarkerToMyPosition(){
        gMap.addMarker(new MarkerOptions().position(latLng));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
    }



    public  void bacaJsonKeList(ArrayList<ATM> atmList){
        AtmAdapter adapter = new AtmAdapter(context, R.layout.list_ui,atmList);
        list.setAdapter(adapter);
    }


}
