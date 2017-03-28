package com.example.ssith123.unoapps.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.volley.Request.Method;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ssith123.unoapps.Model.User;
import com.example.ssith123.unoapps.R;
import com.example.ssith123.unoapps.SignUp;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.android.gms.internal.a.v;

public class DiscountLoginSection extends AppCompatActivity {
    AutoCompleteTextView emailtxt,passwordtxt;
    Button loginbutn,registerbutn,fbloginbutn,googleloginbutn;
    TextView forgettext;
    String spname,spemail,sppassword,email,password;
    private ProgressDialog pDialog;
    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_login_section);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RequestQueue queue = Volley.newRequestQueue(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
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
            //SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", 0);
            //SharedPreferences.Editor editor = pref.edit();
//            spname = pref.getString("Name",null);
//            spemail =pref.getString("Email",null);
//            sppassword =pref.getString("Password",null);
            register();
            if (!validate()){
                Toast.makeText(DiscountLoginSection.this,"form filling error",Toast.LENGTH_SHORT).show();
            }else
                signupSuccess();
            //Toast.makeText(DiscountLoginSection.this,email,Toast.LENGTH_SHORT).show();
        }else if (id==R.id.btnregister){
            Toast.makeText(DiscountLoginSection.this,"going to registration page for new uno user",Toast.LENGTH_SHORT).show();
            Intent in3 = new Intent(DiscountLoginSection.this,SignUp.class);
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
//    private boolean isValidPassword(String pasword) {
//        if (pasword.contains(sppassword)) {
//            return true;
//        }
//        return false;
//    }
    // validating password
    private boolean isValidPassword(String pasword) {
        if (pasword != null && pasword.length() >= 4) {
            return true;
        }
        return false;
    }
//    private boolean isValidEmail(String emaill) {
//        if (emaill.contains(spemail)) {
//            return true;
//        }
//        return false;
//    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
//        if (email != null && email.length() == 10) {
//            return true;
//        }
//        return false;
    }
    public void signupSuccess(){
        Intent in14 = new Intent(DiscountLoginSection.this, Homes.class);
        startActivity(in14);
        //what u want to do after sign success
        Toast.makeText(DiscountLoginSection.this,"success",Toast.LENGTH_SHORT).show();
//        Intent intent10 = new Intent(DiscountLoginSection.this, CardHolder.class);
//        startActivity(intent10);
         checkLogin(email,password);
    }
    public void clickBack(View v){
        Button backbutn = (Button) findViewById(R.id.backbutn);
        Intent in12 = new Intent(DiscountLoginSection.this, Home.class);
        startActivity(in12);
    }
    private void checkLogin(final String email, final String password) {

        pDialog.setMessage("Logging in ...");
        showDialog();
        Log.d("pdialogpass","pass1");
        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Log.d("succeees","pass2");
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error){
                        JSONObject member_info = jObj.getJSONObject("member_info");
                        String uid = member_info.getString("uid");
                        String user_id = member_info.getString("user_id");
                        String email = member_info.getString("email");
                        String first_name = member_info.getString("first_name");
                        String last_name = member_info.getString("last_name");
                        String created_at = member_info
                                .getString("created_at");
                        String updated_at = member_info
                                .getString("updated_at");

                        JSONObject info = jObj.getJSONObject("info");
                        String image = info.getString("image");
                        String name = info.getString("name");
                        String uno_card_number = info.getString("uno_card_number");
                        String uno_card_expiry_date = info.getString("uno_card_expiry_date");
                        String uno_card_city = info.getString("uno_card_city");
                        String barcode = info.getString("barcode");
                        String qrcode = info.getString("qrcode");
                        String promotional_text = info.getString("promotional_text");
                        String present_before = info.getString("present_before");
                        String customer_service = info.getString("customer_service");
                        String phone_number = info.getString("phone_number");
                        String discount_section = info.getString("discount_section");
                        String site_link = info.getString("site_link");
                        String site_link_url = info.getString("site_link_url");
                        String current_time = info.getString("current_time");
                        String issue_image_link = info.getString("issue_image_link");
                        String uno_issue_no = info.getString("uno_issue_no");

                       // Intent in3 =new Intent(DiscountLoginSection.this,CardHolder.class);
                        Intent in3 =new Intent(DiscountLoginSection.this,Home.class);
                        in3.putExtra("Name",name);
                        in3.putExtra("Unocarnumber",uno_card_number);
                        in3.putExtra("Unocardexpirydate",uno_card_expiry_date);
                        in3.putExtra("Unocardcity",uno_card_city);
                        in3.putExtra("Currenttime",current_time);
                        in3.putExtra("Presentbefore",present_before);
                        startActivity(in3);
                    }else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                  //  Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                   // Snackbar.make(coordinatorLayout,error.getMessage(),Snackbar.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();

               // Toast.makeText(DiscountLoginSection.this,"error1",Toast.LENGTH_SHORT).show();
//                Log.e("Login Error: " ,error.getMessage());
                 Snackbar.make(coordinatorLayout,error.getMessage(),Snackbar.LENGTH_LONG).show();
//                Snackbar.make(this,"All field are compulsory and check ur internet connection.",Snackbar.LENGTH_LONG)
//                        .setActionTextColor(0xFFFF0000)
//                        .show();
            }

        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq,"hello");
    }
    // Adding request to request queue

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

