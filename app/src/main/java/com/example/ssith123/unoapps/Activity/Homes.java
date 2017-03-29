package com.example.ssith123.unoapps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ssith123.unoapps.R;

public class Homes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);
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
                Toast.makeText(Homes.this,"card1",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Homes.this,CardHolder.class);
                startActivity(intent2);
            }
        });
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.homes, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accountsetting) {
            // Handle the camera action
            Toast.makeText(Homes.this,"from accountsetting",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_registerbusiness) {
            Toast.makeText(Homes.this,"from register",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_discounttaken) {
            Toast.makeText(Homes.this,"from discounttaken",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_pointtaken) {
            Toast.makeText(Homes.this,"from pointtaken",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_changepassword) {
            Toast.makeText(Homes.this,"from changepassword",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
