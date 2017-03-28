package com.example.ssith123.unoapps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ssith123.unoapps.R;


public class FacebookRegistration extends AppCompatActivity {
    //facebook
//    private CallbackManager callbackManager;
//    private AccessTokenTracker accessTokenTracker;
//    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        LoginButton loginButton1 = (LoginButton) findViewById(R.id.login_button);
//        //Facebook login button
//        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Profile profile = Profile.getCurrentProfile();
//                nextActivity(profile);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        };
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
//        accessTokenTracker = new AccessTokenTracker() {
//
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//
//            }
//        };
//        profileTracker = new ProfileTracker() {
//
//            @Override
//            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//                nextActivity(currentProfile);
//            }
//        };
//        accessTokenTracker.startTracking();
//        profileTracker.startTracking();

    }

//    private void nextActivity(Profile profile) {
//        if (profile != null) {
//            Intent main = new Intent(FacebookRegistration.this, LogOut.class);
//            main.putExtra("name", profile.getFirstName());
//            main.putExtra("surname", profile.getLastName());
//            main.putExtra("imageUrl", profile.getProfilePictureUri(200, 200).toString());
//            startActivity(main);
//        }
//    }
}
