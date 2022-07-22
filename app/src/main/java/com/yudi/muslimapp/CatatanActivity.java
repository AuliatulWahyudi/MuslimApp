package com.yudi.muslimapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yudi.muslimapp.adapter.Adapter;
import com.yudi.muslimapp.helper.DbHelper;
import com.yudi.muslimapp.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CatatanActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "address";
    public static final String TAG_PHONE = "nomorhp";
    public static final String TAG_GENDER= "gender";
    public static final String TAG_LATITUDE= "latitude";
    public static final String TAG_LONGITUDE = "longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Tambah SQLite
        SQLite = new DbHelper(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);

        //Tambah ListView
        listView = (ListView)findViewById(R.id.list_view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tambah Intent untuk pindah  ke halaman add dan edit
                Intent intent = new Intent(CatatanActivity.this, AddEdit.class);
                startActivity(intent);
            }
        });

        //Tambah adapter dan listview
        adapter = new Adapter(CatatanActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO auto generated method stub
                final String idx = itemList.get(i).getId();
                final String name = itemList.get(i).getName();
                final String address = itemList.get(i).getAddress();
                final String noHp = itemList.get(i).getNomorhp();
                final String jekel = itemList.get(i).getJeniskelamin();
                final String lat = itemList.get(i).getLatitude();
                final String longi = itemList.get(i).getLongitude();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(CatatanActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO auto-generated method stub
                        switch (which){
                            case 0 :
                                Intent intent = new Intent(CatatanActivity.this, AddEdit.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_ADDRESS, address);
                                intent.putExtra(TAG_PHONE, noHp);
                                intent.putExtra(TAG_GENDER, jekel);
                                intent.putExtra(TAG_LATITUDE, lat);
                                intent.putExtra(TAG_LONGITUDE, longi);
                                startActivity(intent);
                                break;
                            case 1 :
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;

                        }
                    }
                }).show();

                return false;
            }
        });
        getAllData();
    }

    private void getAllData(){
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++){
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAME);
            String title = row.get(i).get(TAG_ADDRESS);
            String title1 = row.get(i).get(TAG_PHONE);
            String title2 = row.get(i).get(TAG_GENDER);
            String title3 = row.get(i).get(TAG_LATITUDE);
            String title4 = row.get(i).get(TAG_LONGITUDE);

            Data data = new Data();

            data.setId(id);
            data.setName(poster);
            data.setAddress(title);
            data.setNomorhp(title1);
            data.setJeniskelamin(title2);
            data.setLatitude(title3);
            data.setLongitude(title4);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
