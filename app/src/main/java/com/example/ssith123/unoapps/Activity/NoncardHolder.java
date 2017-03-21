package com.example.ssith123.unoapps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.ssith123.unoapps.R;

public class NoncardHolder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noncard_holder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
       final Button mButton=(Button)findViewById( R.id.iwantcard);
        mButton.setEnabled(false);
        CheckBox mCheckBox= ( CheckBox ) findViewById( R.id.termcondid);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){

                    mButton.setVisibility(View.VISIBLE);
                    mButton.setEnabled(true);
                }else {
                    mButton.setVisibility(View.GONE);
                }
            }
        });

    }
    public void cardgetClick(View v){
        Toast.makeText(NoncardHolder.this,"yes bravo",Toast.LENGTH_SHORT).show();
        Intent in8 = new Intent(NoncardHolder.this,CardHolder.class);
        startActivity(in8);
    }
    public void clickBack(View v){
        Button backbutn = (Button) findViewById(R.id.backbutn);
        Intent in6 = new Intent(NoncardHolder.this, FullRegistration.class);
        startActivity(in6);
    }

}
