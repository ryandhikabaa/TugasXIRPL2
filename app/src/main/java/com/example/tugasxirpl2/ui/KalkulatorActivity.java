package com.example.tugasxirpl2.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasxirpl2.R;

public class KalkulatorActivity extends AppCompatActivity{
    private Toolbar toolbar;
    Button btnhitung;
    EditText txtangka1, txtangka2;
    TextView txthasil;
    Spinner spoperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        toolbar = findViewById(R.id.toolbarkal);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kalkulator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtangka1 = findViewById(R.id.et_angka1);
        txtangka2 = findViewById(R.id.et_angka2);
        btnhitung = findViewById(R.id.btn_hitung);
        txthasil =  findViewById(R.id.hasiloperate);
        spoperator =findViewById(R.id.sp_proses);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.proses, android.R.layout.simple_spinner_item);
//        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, operasi);
        spoperator.setAdapter(adapter);
        spoperator.getSelectedItemPosition();

        btnhitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String angka1 = txtangka1.getText().toString().trim();
                String angka2 = txtangka2.getText().toString().trim();
                if(TextUtils.isEmpty(angka1) && TextUtils.isEmpty(angka2)){
                    txtangka1.setError("Angka kosong");
                    txtangka2.setError("Angka kosong");
                }else if(TextUtils.isEmpty(angka1)){
                    txtangka1.setError("Angka kosong");
                }else if(TextUtils.isEmpty(angka2)){
                    txtangka2.setError("Angka kosong");
                }else{
                    int pilihan = spoperator.getSelectedItemPosition();
                    switch (pilihan) {
                        case 0:
                            int tambah = Integer.parseInt(angka1) + Integer.parseInt(angka2);
                            txthasil.setText(String.valueOf(tambah));
                            break;
                        case 1:
                            int kurang = Integer.parseInt(angka1) - Integer.parseInt(angka2);
                            txthasil.setText(String.valueOf(kurang));
                            break;
                        case 2:
                            int kali = Integer.parseInt(angka1) * Integer.parseInt(angka2);
                            txthasil.setText(String.valueOf(kali));
                            break;
                        case 3:
                            float bagi = Float.parseFloat(angka1) / Float.parseFloat(angka2);
                            txthasil.setText(String.valueOf(bagi));
                            break;
                    }
                }
            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
