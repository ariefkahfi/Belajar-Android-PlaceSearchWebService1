package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.jsonHandler;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Arief on 9/1/2017.
 */

public class AsyncResponse extends AsyncTask<String,Integer,JsonElement> {


    private MyJson json;

    public AsyncResponse(MyJson json){
        this.json=json;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("pre","pre_execute");
    }

    @Override
    protected JsonElement doInBackground(String... params) {
        JsonElement reader = null;
        try {
            URL url = new URL(params[0]) ;
            HttpURLConnection con  = (HttpURLConnection) url.openConnection();

            if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
               InputStream in = con.getInputStream();


                JsonParser parser = new JsonParser();

                reader = parser.parse(new JsonReader(new InputStreamReader(in)));

                Log.e("Async_kelas","loading_background...");
            }
        } catch (IOException e) {
            Log.e("ErrorAsyncResponse",e.getMessage());
            e.printStackTrace();
        }
        return reader;
    }

    @Override
    protected void onPostExecute(JsonElement el) {
        super.onPostExecute(el);
        try {
            json.readThisJSONByInputStreamReader(el);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Log.e("onPostExecute","selesai_load");
        }
    }
}
