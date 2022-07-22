package com.yudi.muslimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn1:
                rukunIslam();
                break;
            case R.id.btn2:
                rukunIman();
                break;
            case R.id.btn3:
                namaMalaikat();
                break;
            case R.id.btn4:
                nabiDanRasul();
                break;
            case R.id.btn5:
                Kalkulator();
                break;
            case R.id.btn6:
                Catatan();
                break;
            case R.id.btn7:
                About();
                break;
        }
    }

    private void About() {
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }

    private void Catatan() {
        Intent i = new Intent(MainActivity.this, CatatanActivity.class);
        startActivity(i);
    }

    private void Kalkulator() {
        Intent i = new Intent(MainActivity.this, ZakatActivity.class);
        startActivity(i);
    }

    private void nabiDanRasul() {
        Intent i = new Intent(MainActivity.this, NamaRasulActivity.class);
        startActivity(i);
    }

    private void namaMalaikat() {
        Intent i = new Intent(MainActivity.this, NamaMalaikatActivity.class);
        startActivity(i);
    }

    private void rukunIman() {
        Intent i = new Intent(MainActivity.this, RukunImanActivity.class);
        startActivity(i);
    }

    private void rukunIslam() {
        Intent i = new Intent(MainActivity.this, RukunIslamActivity.class);
        startActivity(i);
    }
}
