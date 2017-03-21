package com.example.ssith123.unoapps.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ssith123.unoapps.R;
import com.example.ssith123.unoapps.SignUp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullRegistration extends AppCompatActivity {
    Button registerbutn, clearbutn, dateslctbutn,mapbutn;
    AutoCompleteTextView refertxt, homeaddtxt, offadtxt, nationalitytxt , dateedt ;
//    EditText dateedt;
    private String refer,homeadd,offad,nationalityy,datee,radio1;
    private RadioGroup radiogendergroup;
    private RadioButton radioSexButton;
    private ToggleButton newslettertoggle;
    private ToggleButton unocardtoggle;
    public Bitmap bitmap;
    DatePickerDialog datePickerDialog;
    private int PICK_IMAGE = 1;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        refertxt = (AutoCompleteTextView) findViewById(R.id.txtreferby);
        homeaddtxt = (AutoCompleteTextView) findViewById(R.id.txthomeaddress);
        offadtxt = (AutoCompleteTextView) findViewById(R.id.txtoficeadress);
        nationalitytxt = (AutoCompleteTextView) findViewById(R.id.txtnationality);
        registerbutn = (Button) findViewById(R.id.btnSignUp);
        dateslctbutn = (Button) findViewById(R.id.dateslect);
        clearbutn = (Button) findViewById(R.id.btnClear);
        mapbutn = (Button) findViewById(R.id.mapbutnid);
        dateedt = (AutoCompleteTextView) findViewById(R.id.dateedt);
        newslettertoggle = (ToggleButton) findViewById(R.id.toggleButton1);
        unocardtoggle = (ToggleButton) findViewById(R.id.toggleButton2);
        radiogendergroup = (RadioGroup) findViewById(R.id.radiogendergroup);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void butnClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSignUp) {
            Toast.makeText(FullRegistration.this, "register click", Toast.LENGTH_SHORT).show();
            register();
            if (!validate()){
                    Toast.makeText(FullRegistration.this,"form filling error",Toast.LENGTH_SHORT).show();
                }else {
                    signupSuccess();
                }
        } else if (id == R.id.btnClear) {
            Toast.makeText(FullRegistration.this, "clear click", Toast.LENGTH_SHORT).show();
            refertxt.setText("");
            homeaddtxt.setText("");
            offadtxt.setText("");
            nationalitytxt.setText("");
            dateedt.setText("");
            refertxt.setError(null);
            homeaddtxt.setError(null);
            offadtxt.setError(null);
            nationalitytxt.setError(null);
            dateedt.setError(null);
            refertxt.requestFocus();
        } else if (id == R.id.dateslect) {
            Toast.makeText(FullRegistration.this, "dateselction click", Toast.LENGTH_SHORT).show();
            // calender class's instance and get current date , month and year from calender
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            //date picker dialog
            datePickerDialog = new DatePickerDialog(FullRegistration.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    dateedt.setText(i2 + "/"
                            + (i1 + 1) + "/" + i);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (id == R.id.imageslect) {

            Toast.makeText(FullRegistration.this, "imageselction click", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }
        else if (id==R.id.mapbutnid){
            Toast.makeText(FullRegistration.this, "map butn click click", Toast.LENGTH_SHORT).show();
            Intent in13 = new Intent(FullRegistration.this,MapsActivity.class);
            startActivity(in13);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.imgview);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void register(){
        initialize();
    }
    public void initialize(){

        refer =refertxt.getText().toString().trim();
        homeadd =homeaddtxt.getText().toString().trim();
        offad =offadtxt.getText().toString().trim();
        datee =dateedt.getText().toString().trim();
        nationalityy=nationalitytxt.getText().toString().trim();
        int radioid = radiogendergroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(radioid);
        radio1 = radioSexButton.getText().toString();
        StringBuffer toggle1 = new StringBuffer();
        StringBuffer toggle2 = new StringBuffer();
        toggle1.append(newslettertoggle.getText());
        toggle2.append(unocardtoggle.getText());
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Homeaddress",homeadd);
        editor.putString("Date", datee);
        editor.commit();

        Toast.makeText(FullRegistration.this,radio1+toggle1+toggle2+datee+homeadd,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),username  +  password,Toast.LENGTH_SHORT).show();
    }
    public boolean validate(){
        boolean valid =true;
        if (!isValidRefername(refer)) {
            //Set error message for password field
            refertxt.setError("refername cannot be empty.");
            valid = false;
        }
        if (!isValidHomeAdd(homeadd)) {
            //Set error message for password field
            homeaddtxt.setError("homeaddress cannot be empty.");
            valid = false;
        }
        if (!isValidOficeAdd(offad)) {
            //Set error message for password field
            offadtxt.setError("office address must not be empty.");
            valid = false;
        }
        if (!isValidDate(datee)) {
            //Set error message for email field
            dateedt.setError("Please insert date.");
            valid = false;
        }
        if (!isValidNationality(nationalityy)) {
            //Set error message for password field
            nationalitytxt.setError("nationality cannot be empty.");
            valid = false;
        }
        return valid;
    }
    private boolean isValidRefername(String refer) {
        if (refer != null && refer.length()>=3) {
            return true;
        }
        return false;
    }
    private boolean isValidHomeAdd(String homeadd) {
        if (homeadd != null && homeadd.length()>=3) {
            return true;
        }
        return false;
    }
    private boolean isValidOficeAdd(String offad) {
        if (offad!=null && offad.length()>=3) {
            return true;
        }
        return false;
    }

    // validating password
    private boolean isValidDate(String datee) {
        if (datee != null && datee.length()>=1) {
            return true;
        }
        return false;
    }
    private boolean isValidNationality(String nationalityy) {
        if (nationalityy != null && nationalityy.length()>=3) {
            return true;
        }
        return false;
    }
    public void signupSuccess(){
        //what u want to do after sign success
        Toast.makeText(FullRegistration.this,"success",Toast.LENGTH_SHORT).show();
        Intent intent4 = new Intent(FullRegistration.this, NoncardHolder.class);
        startActivity(intent4);
    }
    public void clickBack(View v){
        Button backbutn = (Button) findViewById(R.id.backbutn);
        Intent in7 = new Intent(FullRegistration.this, DiscountLoginSection.class);
        startActivity(in7);
    }
}
