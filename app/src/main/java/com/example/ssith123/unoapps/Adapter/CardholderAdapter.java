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
    private final String[] Nme;
    private final String[] Ucardnumber;
    private final String[] Ucardexpirydate;
    private final String[] Ucardcity;
//    private final String[] Pbefore;
//    private final String[] Ctime;
   // private final Integer[] imgid;
    public CardholderAdapter(Activity context, String[] Nme,String[] Ucardnumber,String[] Ucardexpirydate,String[] Ucardcity) {
        super(context, R.layout.mylist,Nme);
        this.context= context;
        this.Nme = Nme;
        this.Ucardnumber = Ucardnumber;
        this.Ucardexpirydate = Ucardexpirydate;
        this.Ucardcity = Ucardcity;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView nametxt = (TextView) rowView.findViewById(R.id.navetv);
        TextView ucardnumber = (TextView) rowView.findViewById(R.id.ucardnumbertv);
        TextView datetxt = (TextView) rowView.findViewById(R.id.datetv);
        TextView cardcitytxt = (TextView) rowView.findViewById(R.id.cardcitytv);
//        TextView presentbeforetxt = (TextView) rowView.findViewById(R.id.presentbeforetv);
//        TextView currenttimetxt = (TextView) rowView.findViewById(R.id.currenttimetv);
        nametxt.setText(Nme[position]);
        ucardnumber.setText(Ucardnumber[position]);
        datetxt.setText(Ucardexpirydate[position]);
        cardcitytxt.setText(Ucardcity[position]);
//        presentbeforetxt.setText(Pbefore[position]);
//        currenttimetxt.setText(Ctime[position]);
        return rowView;
    }
}
