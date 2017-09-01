package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.R;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.constant.GooglePlaceSearchWebService;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.AsyncResponse;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.MyJson;

import java.util.ArrayList;

/**
 * Created by Arief on 9/1/2017.
 */

public class NearbyATMListDialogMaker extends BaseDialog{

    private Spinner spinRadius;
    private ListView list;
    private Button submitNearbyATM;


    public NearbyATMListDialogMaker(@NonNull Context context, String title, int resourceForLayoutXML) {
        super(context, title, resourceForLayoutXML);
    }


    @Override
    public void initWidgetForDialog() {
        spinRadius = (Spinner)v.findViewById(R.id.spinRadius);
        list = (ListView)v.findViewById(R.id.atmList);
        submitNearbyATM = (Button)v.findViewById(R.id.submit);
    }


    public void onButtonListener(){
        submitNearbyATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyJson json = new MyJson(list,v.getContext());
                new AsyncResponse(json).execute(GooglePlaceSearchWebService.DUMMY_DATA);
            }
        });
    }

    @Override
    public void buildMe() {
        try {
            initWidgetForDialog();
            onButtonListener();
            this.setNeutralButton("close", null);
            this.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
