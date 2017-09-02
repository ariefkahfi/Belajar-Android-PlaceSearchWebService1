package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.R;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.constant.GooglePlaceSearchWebService;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.AsyncResponse;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.MyJson;

/**
 * Created by Arief on 9/1/2017.
 */

public class NearbyATMListDialogMaker extends BaseDialog{

    private Spinner spinRadius;
    private GoogleMap gMap;
    private LatLng latLng;


    public NearbyATMListDialogMaker(@NonNull Context context, String title, int resourceForLayoutXML ,LatLng latLng,GoogleMap gMap) {
        super(context, title, resourceForLayoutXML);

        this.latLng=latLng;
        this.gMap = gMap;
    }

    public NearbyATMListDialogMaker(@NonNull Context context, String title, GoogleMap gMap, LatLng latLng) {
        super(context, title);
        this.gMap = gMap;
        this.latLng = latLng;
    }

    @Override
    public void initWidgetForDialog() {
        spinRadius = (Spinner)v.findViewById(R.id.spinRadius);
    }


    public void refreshAllMarkerFromGmap(){
        gMap.clear();
    }



    @Override
    public void buildMe() {
        try {
            initWidgetForDialog();
            this.setNeutralButton("Accept", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    refreshAllMarkerFromGmap();
                    String pilihan = spinRadius.getSelectedItem().toString();

                    Log.e("Pilihan",pilihan);

                /*
                <item>1 KM</item>
                <item>500 M</item>
                <item>2 KM</item>
                 */

                    if(pilihan!=null){
                        int radius = 0;
                        switch (spinRadius.getSelectedItemPosition()){
                            case 0 :
                                radius = 1000;
                                break;
                            case 1 :
                                radius = 500;
                                break;
                            case 2 :
                                radius = 2000;
                                break;
                            default:
                                Log.e("spinRadius","defaultSwitch");
                                break;
                        }

                        GooglePlaceSearchWebService.MyRadius = radius;

                        double lat = latLng.latitude;
                        double lng = latLng.longitude;

                        LatLng latLng = new LatLng(lat,lng);

                        MyJson json = new MyJson(v.getContext(),gMap,latLng);

                        new AsyncResponse(json,false)
                                .execute(GooglePlaceSearchWebService.getInstanceWebServiceWithParamLatLngAndType(v.getContext(),lat,lng,"atm",radius));


                    }
                }
            });
            this.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
