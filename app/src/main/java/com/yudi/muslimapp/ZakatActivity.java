package com.yudi.muslimapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ZakatActivity extends AppCompatActivity {

    TextView txtHasil;
    EditText editText;
    Button btnPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat);

        txtHasil = (TextView)findViewById(R.id.txtHsl);
        btnPro = (Button)findViewById(R.id.btnPro);
        editText = (EditText)findViewById(R.id.editHrg);

        btnPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double hrg,tot;

                hrg = Double.parseDouble(editText.getText().toString());

                tot = hrg * 25/10;

                txtHasil.setText(" Jumlah Zakat Fitrah :  "+tot);

            }
        });

    }
}
