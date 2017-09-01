package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.R;
import com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.model.ATM;

import java.util.List;

/**
 * Created by Arief on 9/1/2017.
 */

public class AtmAdapter extends ArrayAdapter<ATM> {

    private List<ATM> atms;

    public AtmAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ATM> objects) {
        super(context, resource, objects);

        atms = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ui,null);

        ATM atm = atms.get(position);

        widgetHolder holder= new widgetHolder();

        holder.tampilNama = (TextView)convertView.findViewById(R.id.tampilNama);
        holder.tampilAlamat = (TextView)convertView.findViewById(R.id.tampilAlamat);

        holder.tampilNama.setText(atm.getNama());
        holder.tampilAlamat.setText(atm.getAlamat());


        return convertView;
    }

    class widgetHolder{
        private TextView tampilNama,tampilAlamat;
    }
}
