package com.nhom2.qlappdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ManNoiDung extends AppCompatActivity {

    TextView txtTenTruyen, txtNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_noi_dung);

        txtNoiDung = findViewById(R.id.noidung);
        txtTenTruyen = findViewById(R.id.tenTruyen);

        //Lay du lieu
        Intent intent = getIntent();
        String tentruyen = intent.getStringExtra("tenTruyen");
        String noiDung = intent.getStringExtra("NoiDung");

        txtTenTruyen.setText(tentruyen);
        txtNoiDung.setText(noiDung);

        //Cho phep cuon noi dung truyen
        txtNoiDung.setMovementMethod(new ScrollingMovementMethod());
    }
}