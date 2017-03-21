package com.example.ssith123.unoapps;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssith123.unoapps.Activity.Home;
import com.example.ssith123.unoapps.Interface.RegisterAPI;
import com.example.ssith123.unoapps.Model.SimpleRegistration;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignUp extends AppCompatActivity {
    SoapPrimitive resultString;
    String TAG = "Response";
    Button signupbutn , clearbutn;
    TextView tvIsConnected;
    AutoCompleteTextView firstnametxt,lastnametxt,mobilenumtxt,emailtxt,passwordtxt,cpasswordtxt;
    private String Firstname, Lastname, Mobilenum, Emailid, Password, cpassword;
    TextView signuptitle;
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;
    //just a test
    //just a 2test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
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
        signupbutn = (Button) findViewById(R.id.btnSignUp);
        clearbutn = (Button) findViewById(R.id.btnClear);
        signuptitle = (TextView) findViewById(R.id.signuptitle);
        final Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Convergence-Regular.ttf");
        signuptitle.setTypeface(custom_font);
        signuptitle.setText(R.string.signup_text);
        firstnametxt = (AutoCompleteTextView) findViewById(R.id.txtfirstname);
        lastnametxt = (AutoCompleteTextView) findViewById(R.id.txtlastname);
        mobilenumtxt = (AutoCompleteTextView) findViewById(R.id.txtmobilenum);
        emailtxt = (AutoCompleteTextView) findViewById(R.id.txtemail);
        passwordtxt = (AutoCompleteTextView) findViewById(R.id.txtpassword);
        cpasswordtxt = (AutoCompleteTextView) findViewById(R.id.txtcpassword);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }
    }
    public void butnClick(View v){
        int id=v.getId();
        switch (id){
            case R.id.btnSignUp:
                register();
                if (!validate()){
                    Toast.makeText(SignUp.this,"form filling error",Toast.LENGTH_SHORT).show();
                }else
                    signupSuccess();
                break;
            case R.id.btnClear:

                firstnametxt.setText("");
                lastnametxt.setText("");
                mobilenumtxt.setText("");
                emailtxt.setText("");
                passwordtxt.setText("");
                cpasswordtxt.setText("");
                firstnametxt.setError(null);
                lastnametxt.setError(null);
                mobilenumtxt.setError(null);
                emailtxt.setError(null);
                passwordtxt.setError(null);
                cpasswordtxt.setError(null);
                firstnametxt.requestFocus();
//                til.setError(null);
//                til1.setError(null);
                break;
        }
    }
    public void signupSuccess(){
        //what u want to do after sign success

        Toast.makeText(SignUp.this,"success",Toast.LENGTH_SHORT).show();
        //new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
        Intent intent1 = new Intent(SignUp.this, Home.class);
        startActivity(intent1);
    }
    private class AsyncCallWS extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            calculate();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            Toast.makeText(SignUp.this, "Response" + resultString, Toast.LENGTH_SHORT).show();
        }
    }
    public void calculate() {
        String SOAP_ACTION = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
        String METHOD_NAME = "CelsiusToFahrenheit";
        String NAMESPACE = "http://www.w3schools.com/webservices/";
        String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
           // Request.addProperty("Celsius", getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.i(TAG, "Result Celsius: " + resultString);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    public void register(){
        initialize();
    }
    public void initialize(){

        Firstname =firstnametxt.getText().toString().trim();
        Lastname=lastnametxt.getText().toString().trim();
        Mobilenum=mobilenumtxt.getText().toString().trim();
        Emailid=emailtxt.getText().toString().trim();
        Password=passwordtxt.getText().toString().trim();
         cpassword=cpasswordtxt.getText().toString().trim();
        //for shared preference
//         SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", 0);
//         SharedPreferences.Editor editor = pref.edit();
//         editor.putString("Name",firstname);
//         editor.putString("Email",email);
//         editor.putString("Password",pasword);
//         editor.commit();

       // Toast.makeText(getApplicationContext(), "jj" ,Toast.LENGTH_SHORT).show();
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
//        RestAdapter adapter = new RestAdapter.Builder()
//                                    .setEndpoint(ROOT_URL)
//                                    .build();
//        //Creating object for our interface
//        RegisterAPI registerAPI = adapter.create(RegisterAPI.class);
//        //Defining the method insertuser of our interface
//        registerAPI.insertUser(Firstname, Lastname, Mobilenum, Emailid, Password, new Callback<Response>() {
//            @Override
//            public void success(Response response, Response response2) {
//                //On success we will read the server's output using bufferedreader
//                //Creating a bufferedreader object
//                BufferedReader bufferedReader = null;
//                //An string to store output from the server
//                String output = "";
//                try {
//                    bufferedReader = new BufferedReader(new InputStreamReader(response.getBody().in()));
//                    //Reading the output in the string
//                    output = bufferedReader.readLine();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //Displaying the output as a toast
//                Toast.makeText(SignUp.this, output, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(SignUp.this, error.toString(),Toast.LENGTH_LONG).show();
//            }
//        });
    }
    public boolean validate(){
        boolean valid =true;
        if (!isValidFirstname(Firstname)) {
            //Set error message for password field
            firstnametxt.setError("firstname cannot be empty.");
            valid = false;
        }
        if (!isValidLastname(Lastname)) {
            //Set error message for password field
            lastnametxt.setError("lastname cannot be empty.");
            valid = false;
        }
        if (!isValidMobilenum(Mobilenum)) {
            //Set error message for password field
            mobilenumtxt.setError("mobilenumber must be 10 number.");
            valid = false;
        }
        if (!isValidEmail(Emailid)) {
            //Set error message for email field
            emailtxt.setError("Please insert correct EmailId.");
            valid = false;
        }
         if (!isValidPassword(Password)) {
            //Set error message for password field
            passwordtxt.setError("Password cannot be empty.");
             valid = false;
        } if (!isValidCpassword(cpassword)) {
            //Set error message for password field
            cpasswordtxt.setError("password must match.");
            valid = false;
        }
        return valid;
    }
    private boolean isValidFirstname(String firstname) {
        if (firstname != null && firstname.length()>=3) {
            return true;
        }
        return false;
    }
    private boolean isValidLastname(String latname) {
        if (latname != null && latname.length()>=3) {
            return true;
        }
        return false;
    }
    private boolean isValidMobilenum(String mobilenum) {
        if (mobilenum!=null && mobilenum.length()==10) {
            return true;
        }
        return false;
    }

        // validating email id
        private boolean isValidEmail(String email) {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);

            return matcher.matches();
        }

        // validating password
        private boolean isValidPassword(String pasword) {
            if (pasword != null && pasword.length() >= 4) {
                return true;
            }
            return false;
        }
    private boolean isValidCpassword(String cpasword) {
        if (cpasword.contains(Password)) {
            return true;
        }
        return false;
    }
//        boolean valid=true;
//        if (firstname.isEmpty()){
////            til.setError("please enter valid name");
//            firstnametxt.setError("please enter valid name");
//            valid=false;
//            //usernameedt.setFocusable(true);
//        }else if (latname.isEmpty()){
//            lastnametxt.setError("please enter valid age");
////            til1.setError("please enter valid age");
//            valid=false;
//            // passwordedt.setFocusable(true);
//        }else if (mobilenum.isEmpty()||mobilenum.length()<=9){
//            mobilenumtxt.setError("please enter mobile num");
////            til1.setError("please enter valid age");
//            valid=false;
//            // passwordedt.setFocusable(true);
//        }else if (email.isEmpty()){
//            emailtxt.setError("please enter email id");
////            til1.setError("please enter valid age");
//            valid=false;
//            // passwordedt.setFocusable(true);
//        }else if (pasword.isEmpty()||pasword.length()<=7){
//            passwordtxt.setError("please enter password");
////            til1.setError("please enter valid age");
//            valid=false;
//            // passwordedt.setFocusable(true);
//        }else if (pasword.isEmpty()||pasword.length()<=7){
//            cpasswordtxt.setError("please enter password");
////            til1.setError("please enter valid age");
//            valid=false;
//            // passwordedt.setFocusable(true);
//        }
//
//        return valid;
 public boolean isConnected(){

    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isConnected())
        return true;
    else
        return false;
}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
//        return true;
//    }

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
}
