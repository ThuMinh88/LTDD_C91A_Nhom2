package com.nhom2.qlappdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.nhom2.qlappdoctruyen.database.databasedoctruyen;
import com.nhom2.qlappdoctruyen.model.TaiKhoan;
import com.nhom2.qlappdoctruyen.model.Truyen;
import com.nhom2.qlappdoctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import adapter.adapterTruyen;
import adapter.adapterchuyenmuc;
import adapter.adapterthongtin;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listviewthongtin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArrayList;

    adapterTruyen adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;

    databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        //Nhận dữ liệu ở màn hình đăng nhập gửi
        Intent itentpq = getIntent();
        int i = itentpq.getIntExtra("phanq", 0);
        int idd = itentpq.getIntExtra("idd", 0);
        email = itentpq.getStringExtra("email");
        tentaikhoan = itentpq.getStringExtra("tentaikhoan");

        AnhXa();
        ActionBar();
        ActionViewFlipper();

        //Bắt sk click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ManNoiDung.class);

                String tent = TruyenArrayList.get(position).getTenTruyen();
                String noidungt = TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen", tent);
                intent.putExtra("noidung", noidungt);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    if (i==2) {

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài:", "Bạn không có quyền");
                    }
                }
                else if(position ==1){

                }
                else if(position==2){
                    finish();
                }
            }
        });

    }

    private void ActionBar() {
        //Hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);

        //set nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tạo icon cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //phương thức chạy quảng cáo ViewFlipper
    private void ActionViewFlipper() {
        //mảng  chứa tấm ảnh quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
        //Add ảnh vào mảng
        mangquangcao.add("https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cay-tao-than-230189.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-va-cao-230193.jpg");

        //Thực hiện vòng lặp for gắn ảnh vào ImageView lên app
        for(int i=0; i < mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //Sử dụng hàm thư viện Picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //phương thức chỉnh tấm hình vừa khung quảng cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewNew = findViewById(R.id.listViewNew);
        listviewthongtin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArrayList = new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getDatal();
        while (cursor1.moveToNext()) {
            int id = cursor1.getInt(0);
            String tenTruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id,tenTruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        //Thong tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

        adapterthongtin = new adapterthongtin(this, R.layout.navigation_thongtin,taiKhoanArrayList);
        listviewthongtin.setAdapter(adapterthongtin);

        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài", R.drawable.ic_post_add_24));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin", R.drawable.ic_baseline_face_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất", R.drawable.ic_baseline_login_24));

        adapterchuyenmuc = new adapterchuyenmuc(this, R.layout.chuyenmuc, chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Nhập một menu tìm kiếm vào ActionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Nếu click vào icon tìm kiếm sẽ chuyển qua màn hình tìm kiếm
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this, ManTimKiem.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
