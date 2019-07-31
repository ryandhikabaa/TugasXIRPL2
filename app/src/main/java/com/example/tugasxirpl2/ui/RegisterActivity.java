package com.example.tugasxirpl2.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tugasxirpl2.R;
import com.example.tugasxirpl2.model.UserModel;

import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity {
    TextView tdate,ttime;
    EditText username, email, pw;
    Button btn_signup;
    String text_username,text_email, text_pw;
    private static final String MY_PREFS_NAME = "register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final UserModel user = new UserModel();

        tdate = (TextView) findViewById(R.id.date);
        ttime = (TextView) findViewById(R.id.time);
        TextView tvLogin = findViewById(R.id.tv_login);
        username = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email);
        pw = findViewById(R.id.et_pw);
        btn_signup = findViewById(R.id.btn_signup);

        Animation a = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tdate.setAnimation(a);
        ttime.setAnimation(a);

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

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goLogin);
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                user.setEmail(email.getText().toString());
                user.setPassword(pw.getText().toString());
                user.setUsername(username.getText().toString());
//                text_email = email.getText().toString();
//                text_pw = pw.getText().toString();
//                text_username = username.getText().toString();
                if (user.getEmail().length()==0) {
                    email.setError("Enter email");
                } else if (user.getPassword().length()==0) {
                    pw.setError("Enter password");
                }else if (user.getUsername().length()==0){
                    username.setError("Enter username");
                }else {
                    SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", user.getEmail());
                    editor.putString("pw", user.getPassword());
                    editor.putString("username", user.getUsername());
                    editor.commit();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
