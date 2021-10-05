package com.nhom2.qlappdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom2.qlappdoctruyen.database.databasedoctruyen;

public class MHDangNhap extends AppCompatActivity {

    //tạo biến cho MH đăng nhập
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;

    //tạo đối tượng cho dbdoctruyen
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_h_dang_nhap);

        AnhXa();

        //đối tượng databasedoctruyen
        databasedoctruyen = new databasedoctruyen(this);
        //tạo sự kiện click button chuyển sang mh đăng ký tới intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MHDangNhap.this,MHDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                //sử dụng con trỏ lấy dữ liệu, gọi tới getdata để lấy tất cả tài khoản trong db
                Cursor cursor = databasedoctruyen.getData();

                //thực hiện vòng lặp để lấy dữ liệu từ cursor với movetonext di chuyển tiếp
                while (cursor.moveToNext()){

                    //1 là ô taikhoan, 2 là ô matkhau, 0 là idtaikhoan
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    if (datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)) {
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk= cursor.getString(1);

                        //chuyển qua màn hình mainactitvity
                        Intent intent = new Intent(MHDangNhap.this,MainActivity.class);
                        //gửi dữ liệu qua activity là MainActivity
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email", email);
                        intent.putExtra("tentaikhoan", tentk);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MHDangNhap.this,"Mời bạn nhập lại thông tin!!",Toast.LENGTH_LONG).show();
                        edtMatKhau.setText("");
                        edtTaiKhoan.setText("");
                    }
                }
                cursor.moveToFirst();
                //đóng khi ko dùng
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
}