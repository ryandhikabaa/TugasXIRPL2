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

public class LoginActivity extends AppCompatActivity {

    TextView tdate,ttime;
    EditText etusername, etpassword, etemail;
    Button button_signin;
    String text_email, text_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final UserModel user = new UserModel();

        tdate = (TextView) findViewById(R.id.date);
        ttime = (TextView) findViewById(R.id.time);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_pw);
        button_signin = (Button) findViewById(R.id.btn_lg);

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

        TextView tvSignup = findViewById(R.id.tv_signup);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goLogin = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(goLogin);
                finish();
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setEmail(etemail.getText().toString());
                user.setPassword(etpassword.getText().toString());

                if (user.getEmail().length()==0) {
                    etemail.setError("Enter email");
                } else if (user.getPassword().length()==0) {
                    etpassword.setError("Enter password");
                }else {
                    SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", user.getEmail());
                    editor.putString("pw", user.getPassword());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, TugasXIRPL2Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
