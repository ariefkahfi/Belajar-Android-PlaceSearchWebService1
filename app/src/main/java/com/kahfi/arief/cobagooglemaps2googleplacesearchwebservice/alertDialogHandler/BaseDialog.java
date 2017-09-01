package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.alertDialogHandler;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Arief on 9/1/2017.
 */

public abstract class BaseDialog extends AlertDialog.Builder implements MyDialogBuilder {


    protected View v;
    protected String title;

    public BaseDialog(@NonNull Context context, String title, int resourceForLayoutXML) {
        super(context);
        this.title = title;
        initializeForLayoutXML(resourceForLayoutXML);
    }

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, String title) {
        super(context);
        this.title = title;

    }


    protected void initializeForLayoutXML( int resource){
        this.v = LayoutInflater.from(getContext()).inflate(resource,null);
        setView(v);
    }

}
