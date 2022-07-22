package com.yudi.muslimapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NamaRasulActivity extends AppCompatActivity {

    ListView listView;
    String[] rknIman = new String[]{"1. Nabi Adam AS","2. Nabi Idris AS",
            "3. Nabi Nuh AS", "4. Nabi Hud AS", "5. Nabi Shaleh AS","6. Nabi Ibrahim AS","7. Nabi Luth AS","","8. Nabi Ismail AS","9. Nabi Ishaq AS","10. Nabi Yaqub AS","11. Nabi Yusuf AS","12. Nabi Ayub AS",
            "13. Nabi Zulkifli AS","14. Nabi Syu’aib AS","15. Nabi Yunus AS","16. Nabi Musa AS","17. Nabi Harun AS","18. Nabi Daud AS","19. Nabi Sulaiman AS","20. Nabi Ilyas AS","21. Nabi Ilyasa AS","22. Nabi Zakaria AS","23. Nabi Yahya AS","24. Nabi Isa AS","25. Nabi Muhammad SAW"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_rasul);

        getSupportActionBar().setTitle("ListView Rukun Iman");
        listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NamaRasulActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, rknIman);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(NamaRasulActivity.this, "Memilih : "+rknIman[i], Toast.LENGTH_LONG).show();
            }
        });


    }
}
