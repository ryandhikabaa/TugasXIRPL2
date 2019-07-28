package com.example.tugasxirpl2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.tugasxirpl2.ui.LoginActivity;
import com.example.tugasxirpl2.ui.TugasXIRPL2Activity;

import java.text.SimpleDateFormat;

public class SplashActivity extends AppCompatActivity {

    TextView tdate,ttime;
    Animation frombottom, fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tdate = (TextView) findViewById(R.id.date);
        ttime = (TextView) findViewById(R.id.time);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        tdate.setAnimation(animation);
        ttime.setAnimation(animation);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                long time = System.currentTimeMillis();
                                SimpleDateFormat sdf_time = new SimpleDateFormat("hh-mm-ss a");
                                String timeString = sdf_time.format(time);
                                ttime.setText(timeString);

                                SimpleDateFormat sdf_date = new SimpleDateFormat("MMM dd yyyy");
                                String dateString = sdf_date.format(date);
                                tdate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);

                String username = prefs.getString("email", "");
                String name = prefs.getString("user", "");
                if (username == ""){
                    //login
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    //mainmenu

                    Intent intent1 = new Intent(getApplicationContext(), TugasXIRPL2Activity.class);
                    startActivity(intent1);
                    finish();
                }

            }
        },2000);
    }

}
