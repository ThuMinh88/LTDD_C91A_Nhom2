package com.nhom2.qlappdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom2.qlappdoctruyen.database.databasedoctruyen;
import com.nhom2.qlappdoctruyen.model.TaiKhoan;

public class MHDangKy extends AppCompatActivity {

    EditText edtDKTaiKhoan, edtDKMatKhau, edtDKEmail;
    Button btnDKDangKy, btnDKDangNhap;

    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_h_dang_ky);

        databasedoctruyen = new databasedoctruyen(this);

        AnhXa();

        //clicl cho btn đăng ký
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();
                TaiKhoan taiKhoan1 = CreateTaiKhoan();
                if(taikhoan.equals("") || matkhau.equals("") || email.equals("")){
                    Log.e("Thông báo :", "Chưa nhập đủ thông tin");
                }else { //đủ thông tin thì add vào db
                    databasedoctruyen.addTaiKhoan(taiKhoan1);
                    Toast.makeText(MHDangKy.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        //run...
        //trở về đăng nhập
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);

        return tk;
    }

    private void AnhXa() {
        edtDKEmail = findViewById(R.id.dkemail);
        edtDKMatKhau = findViewById(R.id.dkmatkhau);
        edtDKTaiKhoan = findViewById(R.id.dktaikhoan);
        btnDKDangKy = findViewById(R.id.dkdangky);
        btnDKDangNhap = findViewById(R.id.dkdangnhap);
    }
}