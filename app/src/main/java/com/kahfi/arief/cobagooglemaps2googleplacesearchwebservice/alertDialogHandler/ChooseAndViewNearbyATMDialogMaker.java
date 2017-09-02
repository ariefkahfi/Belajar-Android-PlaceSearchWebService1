package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.R;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.constant.GooglePlaceSearchWebService;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.AsyncResponse;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.MyJson;

/**
 * Created by Arief on 9/1/2017.
 */

public class ChooseAndViewNearbyATMDialogMaker extends ProgressDialog implements MyDialogBuilder {


    private LatLng latLng;
    private GoogleMap gMap;

    private Context context ;

    public ChooseAndViewNearbyATMDialogMaker(Context context,LatLng latLng, GoogleMap gMap) {
        super(context);

        this.context = context;
        this.latLng = latLng;
        this.gMap = gMap;

        GooglePlaceSearchWebService.MyRadius = 500;

        this.show();
    }


    public void clearAllMarkersForGMap(){
        gMap.clear();
    }

    public void loadDataFromJSON(){
        clearAllMarkersForGMap();


        MyJson json = new MyJson(this.context,gMap,latLng);

        new AsyncResponse(json,false)
                .execute(GooglePlaceSearchWebService.getInstanceWebServiceWithParamLatLngAndType(this.context,latLng.latitude,latLng.longitude,"atm",500));

    }

    @Override
    public void initWidgetForDialog() {
        this.setTitle("Loading...");
        this.setMessage("Mencari ATM terdekat radius 500 meter");
    }

    @Override
    public void buildMe() {
        clearAllMarkersForGMap();
        loadDataFromJSON();

        this.dismiss();
    }
}
