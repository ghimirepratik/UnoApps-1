package com.example.ssith123.unoapps.Activity;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by SSITH123 on 2/28/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.ssith123.unoapps", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(),0));
                Log.d("result",something);
              //  Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                //Toast.makeText(MyApplication.this,Base64.DEFAULT,Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
           // e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
           // e.printStackTrace();
        }
    }
}
