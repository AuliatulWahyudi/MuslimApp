package com.yudi.muslimapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yudi.muslimapp.helper.DbHelper;

public class AddEdit extends AppCompatActivity {

    EditText txt_id, txt_name, txt_address, txt_nomorhp, txt_latitude, txt_longitude;
    Button btn_submit, btn_cancel, tombolNext;
    RadioGroup txt_gender;
    ImageView imageView;
    GpsTracker gpsTracker;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address, noHp, gender, lat, longi;
    RadioButton radioButton1,radioButton2;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_address = (EditText) findViewById(R.id.txt_address);
        txt_nomorhp = (EditText) findViewById(R.id.txt_nomrhp);
        txt_gender = (RadioGroup) findViewById(R.id.RdGender);
        txt_latitude = (EditText) findViewById(R.id.txt_lat);
        txt_longitude = (EditText) findViewById(R.id.txt_long);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        tombolNext = findViewById(R.id.btn_next);

        id = getIntent().getStringExtra(CatatanActivity.TAG_ID);
        name = getIntent().getStringExtra(CatatanActivity.TAG_NAME);
        address = getIntent().getStringExtra(CatatanActivity.TAG_ADDRESS);
        noHp = getIntent().getStringExtra(CatatanActivity.TAG_PHONE);
        gender = getIntent().getStringExtra(CatatanActivity.TAG_GENDER);
        lat = getIntent().getStringExtra(CatatanActivity.TAG_LATITUDE);
        longi = getIntent().getStringExtra(CatatanActivity.TAG_LONGITUDE);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_address.setText(address);
            txt_nomorhp.setText(noHp);
            txt_latitude.setText(lat);
            txt_longitude.setText(longi);
        }

        tombolNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation(v);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getLocation(View view){
        gpsTracker = new GpsTracker(AddEdit.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            txt_latitude.setText(String.valueOf(latitude));
            txt_longitude.setText(String.valueOf(longitude));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdLaki:
                if (checked)
                    // Pirates are the best
                    Toast.makeText(AddEdit.this, radioButton1.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.rdPerempuan:
                if (checked)
                    // Ninjas rule
                    Toast.makeText(AddEdit.this, radioButton2.getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //TakePicture
    private void pickImageFromGalery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults [0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permisionwasgranted
                    pickImageFromGalery();
                }else {
                    //permisionwasdanied
                    Toast.makeText(this,"Permission Danied ...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //handle result of runtime permission
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //
            imageView.setImageURI(data.getData());
        }
    }


    //Kosongkan semua edit text
    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_address.setText(null);
        txt_nomorhp.setText(null);
    }

    //Menyimpan data  ke databae SQLite
    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_address.getText()).equals(null) || String.valueOf(txt_address.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please Input Name or Address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_name.getText().toString().trim(), txt_address.getText().toString().trim(), txt_nomorhp.getText().toString().trim()
                    , String.valueOf(txt_gender.getCheckedRadioButtonId()), txt_latitude.getText().toString().trim(), txt_longitude.getText().toString().trim());
            blank();
            finish();
        }
    }

    //Update data ke dalam database SQLite
    private void edit() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_address.getText()).equals(null) || String.valueOf(txt_address.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please Input Name or Address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(),
                    txt_address.getText().toString().trim(), txt_nomorhp.getText().toString().trim()
                    , String.valueOf(txt_gender.getCheckedRadioButtonId()), txt_latitude.getText().toString().trim(), txt_longitude.getText().toString().trim());
            blank();
            finish();

        }

    }
}
