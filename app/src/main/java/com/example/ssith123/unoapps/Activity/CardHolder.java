package com.example.ssith123.unoapps.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssith123.unoapps.Adapter.CardholderAdapter;
import com.example.ssith123.unoapps.R;

import java.util.jar.Attributes;

public class CardHolder extends AppCompatActivity {
  // public String spname,sphome,spdate;
    TextView presentbeforetv,counttv,datetv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_holder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Bundle bundle = getIntent().getExtras();
//        String Name = bundle.getString("Name");
//        String Unocarnumber = bundle.getString("Unocarnumber");
//        String Unocardexpirydate = bundle.getString("Unocardexpirydate");
//        String Unocardcity = bundle.getString("Unocardcity");
//        String Currenttime = bundle.getString("Currenttime");
//        String Presentbefore = bundle.getString("Presentbefore");
//        Toast.makeText(CardHolder.this,Name,Toast.LENGTH_SHORT).show();


//        SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", 0);
//        //SharedPreferences.Editor editor = pref.edit();
//        spname = pref.getString("Name",null);
//        sphome = pref.getString("Homeaddress",null);
//        spdate = pref.getString("Date",null);
//        String[] Nme={Name};
//        String[] Ucardnumber={Unocarnumber};
//        String[] Ucardexpirydate={Unocardexpirydate};
//        String[] Ucardcity={Unocardcity};
//        String[] Pbefore={Presentbefore};
//        String[] Ctime={Currenttime};
//        CardholderAdapter cardholderAdapter = new CardholderAdapter(this,Nme,Ucardnumber,Ucardexpirydate,
//                Ucardcity);
//        ListView listView = (ListView) findViewById(R.id.list1);
//        listView.setAdapter(cardholderAdapter);
//        Toast.makeText(CardHolder.this, Name+Presentbefore+Currenttime+Unocardcity,Toast.LENGTH_SHORT).show();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        presentbeforetv = (TextView) findViewById(R.id.presentbeforetv);
//        presentbeforetv.setText(Presentbefore);
//        datetv = (TextView) findViewById(R.id.datetv);
//        datetv.setText(Currenttime);
    }
    public void clickBack(View v){
        Button backbutn = (Button) findViewById(R.id.backbutn);
        Intent in9 = new Intent(CardHolder.this, Homes.class);
        startActivity(in9);
    }

}
