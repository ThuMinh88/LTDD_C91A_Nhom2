package com.nhom2.qlappdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {

    TextView txtThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);

        txtThongTin = findViewById(R.id.txtThongTin);
        String thongtin = "NHÓM THỰC HIỆN ĐỒ ÁN: \n" +
                "\tNgô Tuấn Linh - 1851050080\n" +
                "\tNguyễn Thị Diễm My - 1851050091\n" +
                "\tNguyễn Hoàng Minh Thư - 1851050142\n" +
                "\tLê Thị Thu Uyên - 1851050181\n\n";
        txtThongTin.setText(thongtin);
    }
}