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

import com.example.tugasxirpl2.Kalkulator.KalkulatorCasio;
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

        final KalkulatorCasio kc = new KalkulatorCasio();
        btnhitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String angkaku1 = txtangka1.getText().toString().trim();
                String angkaku2 = txtangka2.getText().toString().trim();
                int angka1 = Integer.parseInt(txtangka1.getText().toString());
                int angka2 = Integer.parseInt(txtangka1.getText().toString());

                if(TextUtils.isEmpty(angkaku1) && TextUtils.isEmpty(angkaku2)){
                    txtangka1.setError("Angka kosong");
                    txtangka2.setError("Angka kosong");
                }else if(TextUtils.isEmpty(angkaku1)){
                    txtangka1.setError("Angka kosong");
                }else if(TextUtils.isEmpty(angkaku2)){
                    txtangka2.setError("Angka kosong");
                }else{
                    int pilihan = spoperator.getSelectedItemPosition();
                    switch (pilihan) {
                        case 0:
                            int hasiltambah = kc.penjumlahan(angka1,angka2);
                            txthasil.setText(String.valueOf(hasiltambah));
                            break;
                        case 1:
                            int hasilkurang = kc.pengurangan(angka1,angka2);
                            txthasil.setText(String.valueOf(hasilkurang));
                            break;
                        case 2:
                            int hasilkali = kc.perkalian(angka1,angka2);
                            txthasil.setText(String.valueOf(hasilkali));
                            break;
                        case 3:
                            double angka1double = Double.parseDouble(txtangka1.getText().toString());
                            double angka2double = Double.parseDouble(txtangka2.getText().toString());
                            double hasilbagi = kc.pembagian(angka1double,angka2double);
                            txthasil.setText(String.valueOf(hasilbagi));
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
