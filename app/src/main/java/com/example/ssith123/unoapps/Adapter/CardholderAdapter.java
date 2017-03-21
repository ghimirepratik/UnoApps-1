package com.example.ssith123.unoapps.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ssith123.unoapps.R;

/**
 * Created by SSITH123 on 2/22/2017.
 */

public class CardholderAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] itemhomeadd;
    private final String[] itemdate;
   // private final Integer[] imgid;
    public CardholderAdapter(Activity context, String[] itemname,String[] itemhomeadd,String[] itemdate) {
        super(context, R.layout.mylist,itemname);
        this.context= context;
        this.itemname = itemname;
        this.itemhomeadd = itemhomeadd;
        this.itemdate = itemdate;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView nametxt = (TextView) rowView.findViewById(R.id.navetv);
        TextView homeaddtxt = (TextView) rowView.findViewById(R.id.homeaddtv);
        TextView datetxt = (TextView) rowView.findViewById(R.id.datetv);
        nametxt.setText(itemname[position]);
        homeaddtxt.setText(itemhomeadd[position]);
        datetxt.setText(itemdate[position]);
        return rowView;
    }
}
