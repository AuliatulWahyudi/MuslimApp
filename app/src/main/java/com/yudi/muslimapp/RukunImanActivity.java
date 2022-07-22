package com.yudi.muslimapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RukunImanActivity extends AppCompatActivity {

    ListView listView;
    String[] rknIman = new String[]{"1. Iman Kepada Allah SWT","2. Iman Kepada Malaikat Allah",
            "3. Iman Kepada Khitab-khitab Allah", "4. Iman Kepada Rasul-rasul Allah", "5. Iman Kepada Hari Akhir",
            "6. Iman Kepada Qadar Baik dan Qadar Buruk"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rukun_iman);

        getSupportActionBar().setTitle("ListView Rukun Iman");
        listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RukunImanActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, rknIman);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RukunImanActivity.this, "Memilih : "+rknIman[i], Toast.LENGTH_LONG).show();
            }
        });

    }
}
