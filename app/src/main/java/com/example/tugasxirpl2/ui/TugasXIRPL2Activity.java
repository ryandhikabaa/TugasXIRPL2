package com.example.tugasxirpl2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tugasxirpl2.R;

public class TugasXIRPL2Activity extends AppCompatActivity {
    Button tgs_login, tgs_tabViewPager;
    private static final int TIME_LIMIT = 1500;
    private static long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_xirpl2);
        tgs_login = (Button) findViewById(R.id.btn_tgs_login);
        tgs_tabViewPager = (Button) findViewById(R.id.btn_tgs_tabViewPager);

        tgs_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TugasXIRPL2Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        tgs_tabViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TugasXIRPL2Activity.this, TabViewPager.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()){
            super.onBackPressed();;
        }else {
            Toast.makeText(getApplicationContext(),"Tekan lagi untuk keluar",Toast.LENGTH_SHORT).show();
        }
        backPressed =System.currentTimeMillis();
    }
}
