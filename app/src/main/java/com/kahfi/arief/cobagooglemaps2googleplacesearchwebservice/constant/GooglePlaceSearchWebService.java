package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.constant;

import android.content.Context;

import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.R;

/**
 * Created by Arief on 9/1/2017.
 */

public class GooglePlaceSearchWebService {


    public static final String DUMMY_DATA = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-3.6608916,103.7694693&radius=1000&type=atm&key=Hello_world";


    public static String getInstanceWebServiceWithParamLatLngAndType(Context context , double lat, double lng,String type,int radius){
        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius="+radius+"&"+
                "type="+type+"&key="+context.getResources().getString(R.string.google_maps_key);
    }

    public static String getInstanceForWebServiceWithKeyword(Context context,double lat ,double lng,String type , int radius,String keyword){
        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius"+radius+"&"+
                "type="+type+"&keyword="+keyword+"&key="+context.getResources().getString(R.string.google_maps_key);
    }

}
