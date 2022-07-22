package com.yudi.muslimapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NamaMalaikatActivity extends AppCompatActivity {

    ListView listView;
    String[] nmMalaikat = new String[]{"1. Malaikat Jibril","2. Malaikat Mikail",
            "3. Malaikat Israfil", "4. Malaikat Israil", "5. Malaikat Mungkar",
            "6. Malaikat Nangkir", "7. Malaikat Raqib", "8. Malaikat Atid", "9. Malaikat Malik", "10. Malaikat Ridwan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_malaikat);

        getSupportActionBar().setTitle("ListView Nama Malaikat");
        listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NamaMalaikatActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, nmMalaikat);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(NamaMalaikatActivity.this, "Memilih : "+nmMalaikat[i], Toast.LENGTH_LONG).show();
            }
        });

    }
}
