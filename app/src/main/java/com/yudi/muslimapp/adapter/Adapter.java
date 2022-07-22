package com.yudi.muslimapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yudi.muslimapp.R;
import com.yudi.muslimapp.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.list_row, null);

        TextView id = (TextView) view.findViewById(R.id.id);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView address = (TextView) view.findViewById(R.id.address);
        TextView nomorhp = view.findViewById(R.id.nohp);
        TextView jeniskelamin = view.findViewById(R.id.RdGender);
        TextView lat = view.findViewById(R.id.txt_lat);
        TextView longi = view.findViewById(R.id.txt_long);

        Data data = items.get(i);

        id.setText(data.getId());
        name.setText(data.getName());
        address.setText(data.getAddress());
        nomorhp.setText(data.getNomorhp());
        jeniskelamin.setText(data.getJeniskelamin());
        lat.setText(data.getLatitude());
        longi.setText(data.getLongitude());

        return view;
    }
}
