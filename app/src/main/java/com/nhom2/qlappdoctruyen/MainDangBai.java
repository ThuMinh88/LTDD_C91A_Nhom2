package com.nhom2.qlappdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom2.qlappdoctruyen.database.databasedoctruyen;
import com.nhom2.qlappdoctruyen.model.Truyen;

public class MainDangBai extends AppCompatActivity {

    EditText edtTenTruyen, edtNoiDung, edtAnh;
    Button btnDangBai;
    databasedoctruyen databasedoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_bai);

        edtAnh = findViewById(R.id.dbimg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);

        databasedoctruyen = new databasedoctruyen(this);
        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreateTruyen();

                if (tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(MainDangBai.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERR :", "Nhập đầy đủ thông tin");
                }
                //nhập đủ thông tin thì thêm dữ liệu
                else {
                    databasedoctruyen.AddTruyen(truyen);

                    //chuyển qua màn ADmin và cập nhật lại dữ liệu
                    Intent intent = new Intent(MainDangBai.this, ManAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    private Truyen CreateTruyen(){
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent =  getIntent();

        int id = intent.getIntExtra("Id", 0);

        Truyen truyen = new Truyen(id,tentruyen,noidung,img,id);
        return  truyen;
    }
}