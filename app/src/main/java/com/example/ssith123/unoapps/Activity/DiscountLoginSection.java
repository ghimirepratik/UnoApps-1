package com.example.ssith123.unoapps.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssith123.unoapps.Model.User;
import com.example.ssith123.unoapps.R;
import com.example.ssith123.unoapps.SignUp;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class DiscountLoginSection extends AppCompatActivity {
    AutoCompleteTextView emailtxt,passwordtxt;
    Button loginbutn,registerbutn,fbloginbutn,googleloginbutn;
    TextView forgettext;
    String spname,spemail,sppassword,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_login_section);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        emailtxt = (AutoCompleteTextView) findViewById(R.id.txtemailid);
        passwordtxt = (AutoCompleteTextView) findViewById(R.id.txtpassword);
        forgettext = (TextView) findViewById(R.id.forgettxt);
        forgettext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(DiscountLoginSection.this,Forgetpassword.class);
                startActivity(in2);
            }
        });
        loginbutn = (Button) findViewById(R.id.btnlogin);
        registerbutn = (Button) findViewById(R.id.btnregister);
        fbloginbutn = (Button) findViewById(R.id.btnfacebk);
        googleloginbutn = (Button) findViewById(R.id.btngogle);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    public void loginbutnClick(View v){
        int id = v.getId();
        if (id==R.id.btnlogin){
            Toast.makeText(DiscountLoginSection.this,"going to page for uno card holder",Toast.LENGTH_SHORT).show();
            SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", 0);
            //SharedPreferences.Editor editor = pref.edit();
            spname = pref.getString("Name",null);
            spemail =pref.getString("Email",null);
            sppassword =pref.getString("Password",null);
            register();
            if (!validate()){
                Toast.makeText(DiscountLoginSection.this,"form filling error",Toast.LENGTH_SHORT).show();
            }else
                signupSuccess();
            Toast.makeText(DiscountLoginSection.this,spemail,Toast.LENGTH_SHORT).show();
        }else if (id==R.id.btnregister){
            Toast.makeText(DiscountLoginSection.this,"going to registration page for new uno user",Toast.LENGTH_SHORT).show();
            Intent in3 = new Intent(DiscountLoginSection.this,FullRegistration.class);
            startActivity(in3);
        }else if (id==R.id.btnfacebk){
            Toast.makeText(DiscountLoginSection.this,"going to facebook registration page for new uno user",Toast.LENGTH_SHORT).show();
            Intent in4 = new Intent(DiscountLoginSection.this,FacebookRegistration.class);
            startActivity(in4);



        }else if (id==R.id.btngogle){
            Toast.makeText(DiscountLoginSection.this,"going to google registration page for new uno user",Toast.LENGTH_SHORT).show();
            Intent in5 = new Intent(DiscountLoginSection.this,GoogleRegistraton.class);
            startActivity(in5);
        }
    }
    public void register(){
        initialize();
    }
    public void initialize(){
        email = emailtxt.getText().toString();
        password = passwordtxt.getText().toString();
    }
    public boolean validate(){
        boolean valid =true;

        if (!isValidEmail(email)) {
            //Set error message for email field
            emailtxt.setError("Please insert correct EmailId.");
            valid = false;
        }
        if (!isValidPassword(password)) {
            //Set error message for password field
            passwordtxt.setError("please insert correct password.");
            valid = false;
        }
        return valid;
    }
    private boolean isValidPassword(String pasword) {
        if (pasword.contains(sppassword)) {
            return true;
        }
        return false;
    }
    private boolean isValidEmail(String emaill) {
        if (emaill.contains(spemail)) {
            return true;
        }
        return false;
    }
    public void signupSuccess(){
        //what u want to do after sign success
        Toast.makeText(DiscountLoginSection.this,"success",Toast.LENGTH_SHORT).show();
//        Intent intent10 = new Intent(DiscountLoginSection.this, CardHolder.class);
//        startActivity(intent10);
    }
    public void clickBack(View v){
        Button backbutn = (Button) findViewById(R.id.backbutn);
        Intent in12 = new Intent(DiscountLoginSection.this, Home.class);
        startActivity(in12);
    }
    }

