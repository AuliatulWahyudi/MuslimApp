package com.yudi.muslimapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RukunIslamActivity extends AppCompatActivity {

    ListView listView;
    String[] rknIslam = new String[]{"1. Membaca 2 Kalimat Syahadat","2. Sholat 5 Waktu Sehari Semalam",
                                     "3. Membayar Zakat", "4. Puasa di Bulan Ramadhan", "5. Naik Haji bagi yang Mampu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rukun_islam);

        getSupportActionBar().setTitle("ListView Rukun Islam");
        listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RukunIslamActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, rknIslam);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RukunIslamActivity.this, "Memilih : "+rknIslam[i], Toast.LENGTH_LONG).show();
            }
        });

    }
}
