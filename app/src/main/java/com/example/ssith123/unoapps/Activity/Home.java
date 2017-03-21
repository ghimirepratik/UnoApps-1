package com.example.ssith123.unoapps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.ssith123.unoapps.R;

public class Home extends AppCompatActivity {
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cardView1=(CardView)findViewById(R.id.cardview1);
        cardView2=(CardView)findViewById(R.id.cardview2);
        cardView3=(CardView)findViewById(R.id.cardview3);
        cardView4=(CardView)findViewById(R.id.cardview4);
        cardView5=(CardView)findViewById(R.id.cardview5);
        cardView6=(CardView)findViewById(R.id.cardview6);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this,"card1",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Home.this,DiscountLoginSection.class);
                startActivity(intent2);
            }
        });

    }

}
