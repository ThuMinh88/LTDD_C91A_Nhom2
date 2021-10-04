          package com.nhom2.qlappdoctruyen;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.nhom2.qlappdoctruyen.adapter.adapterChuyenMuc;
import com.nhom2.qlappdoctruyen.adapter.adapterThongTin;
import com.nhom2.qlappdoctruyen.adapter.adapterTruyen;
import com.nhom2.qlappdoctruyen.database.databasedoctruyen;
import com.nhom2.qlappdoctruyen.model.TaiKhoan;
import com.nhom2.qlappdoctruyen.model.Truyen;
import com.nhom2.qlappdoctruyen.model.chuyenmuc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    NavigationView navigationView;
    ListView listViewNew, listviewThongTin, listviewMain;
    DrawerLayout drawerLayout;

    String email;
    String  tenTaiKhoan;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;

    databasedoctruyen databaseDocTruyen;

    adapterChuyenMuc adapterChuyenMuc;
    adapterThongTin apdapterThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDocTruyen = new databasedoctruyen(this);

        //nhan du lieu tu dang nhap
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq", 0);
        int idd = intentpq.getIntExtra("idd", 0);
        email = intentpq.getStringExtra("email");
        tenTaiKhoan = intentpq.getStringExtra("tenTaiKhoan");

        AnhXa();
        ActionBar();

        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ManNoiDung.class);

                String tenT = TruyenArrayList.get(position).getTenTruyen();
                String noiDung = TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tenTruyen", tenT);
                intent.putExtra("NoiDung", noiDung);
                startActivity(intent);
            }
        });

        //bắt click item cho listview
       listviewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Đăng bài
                if(position == 0){
                    if ((i == 2)) {
                        Intent intent = new Intent(MainActivity.this, MainDangBai.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Bạn không có quyền đăng bài",Toast.LENGTH_LONG).show();
                        Log.e("Đăng bài: ", "Bạn không có quyền đăng");
                    }
                }
                // Nếu vị trí ấn vào là thông tin thì sẽ chuyển qua main thông tin app
                else if (position == 1){
                    Intent intent = new Intent(MainActivity.this, MainThongTin.class);
                    startActivity(intent);
                }
                //Đăng xuất
                else if(position == 2){
                    finish();
                }
            }
        });

    }

    //Lam viec voi toolbar
    private void ActionBar() {
        //Ham ho tro toolbar
        setSupportActionBar(toolbar);

        //set nut cho action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    private void AnhXa(){
        toolbar = findViewById(R.id.toolBarMain);
        navigationView = findViewById((R.id.navigationDs));
        drawerLayout = findViewById(R.id.drawerLayout);
        listviewMain = findViewById(R.id.listviewMain);
        listviewThongTin = findViewById(R.id.listviewThongTin);
        listViewNew = findViewById(R.id.listviewNew);

        TruyenArrayList = new ArrayList<>();

        Cursor cursorl = databaseDocTruyen.getDatal();
        while (cursorl.moveToNext()){
            int id = cursorl.getInt(0);
            String tenTruyen = cursorl.getString(1);
            String noiDung = cursorl.getString(2);
            String anh = cursorl.getString(3);
            int id_tk = cursorl.getInt(4);

            TruyenArrayList.add(new Truyen(id,tenTruyen,noiDung,anh,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listViewNew.setAdapter(adapterTruyen);
        }

        cursorl.moveToFirst();
        cursorl.close();

        //THONG TIN
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tenTaiKhoan,email));

        apdapterThongTin = new adapterThongTin(this,R.layout.navigation_thongtin,taiKhoanArrayList);
        listviewThongTin.setAdapter(apdapterThongTin);

        //chuyenmuc
        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài",R.drawable.ic_baseline_post_add_24));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin", R.drawable.ic_baseline_face_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất", R.drawable.ic_baseline_login_24));

        adapterChuyenMuc = new adapterChuyenMuc(this,R.layout.chuyenmuc, chuyenmucArrayList);
        listviewMain.setAdapter(adapterChuyenMuc);
    }
    // Nap mot menu tim kiem
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //newu clic vap icon tim kiem sẽ chuyn qua man hinh tìm kiếm
        switch (item.getItemId()){
            case  R.id.menu1:
                Intent intent = new Intent(MainActivity.this,ManTimKiem.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}