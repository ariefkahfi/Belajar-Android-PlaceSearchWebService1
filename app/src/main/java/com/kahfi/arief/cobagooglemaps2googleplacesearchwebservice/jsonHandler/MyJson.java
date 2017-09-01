package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

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


    public MyJson(){

    }

    public MyJson(ListView list,Context context){
        this.list=list;
        this.context=context;
    }



    public   void  readThisJSONByInputStreamReader(JsonElement el) throws Exception{

          ArrayList<ATM> atm = new ArrayList<>();

          JsonArray result = el.getAsJsonObject().get("results").getAsJsonArray();

            for(JsonElement element : result){
                JsonElement geometry  = element.getAsJsonObject().get("geometry");
                JsonElement location = geometry.getAsJsonObject().get("location");

                String namaTempat = element.getAsJsonObject().get("name").getAsString();
                String alamat = element.getAsJsonObject().get("vicinity").getAsString();


                double lat = location.getAsJsonObject().get("lat").getAsDouble();
                double lng = location.getAsJsonObject().get("lng").getAsDouble();


                atm.add(new ATM(namaTempat,alamat));
            }

            bacaJsonKeList(atm);
    }


    public  void bacaJsonKeList(ArrayList<ATM> atmList){
        AtmAdapter adapter = new AtmAdapter(context, R.layout.list_ui,atmList);
        list.setAdapter(adapter);
    }


}
