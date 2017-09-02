package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.R;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.constant.GooglePlaceSearchWebService;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.AsyncResponse;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler.MyJson;

import java.util.List;

/**
 * Created by Arief on 9/2/2017.
 */

public class ListDialogForAtmNamesAndAddress extends BaseDialog {

    private ListView list;

    private LatLng latLng;

    public ListDialogForAtmNamesAndAddress(@NonNull Context context, String title, int resourceForLayoutXML,LatLng latLng) {
        super(context, title, resourceForLayoutXML);
        this.latLng=latLng;
    }

    @Override
    public void initWidgetForDialog() {
        list = (ListView)v.findViewById(R.id.atmList);
    }

    public void loadFromJsonToListView(){
        MyJson json = new MyJson(v.getContext(),list);
        new AsyncResponse(json,true).execute(GooglePlaceSearchWebService.getInstanceWebServiceWithParamLatLngAndType(v.getContext(),latLng.latitude,latLng.longitude,"atm",GooglePlaceSearchWebService.MyRadius));
    }

    @Override
    public void buildMe() {
        initWidgetForDialog();
        loadFromJsonToListView();

        this.show();
    }

}
