package com.nhom2.qlappdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.nhom2.qlappdoctruyen.adapter.adapterTruyen;
import com.nhom2.qlappdoctruyen.database.databasedoctruyen;
import com.nhom2.qlappdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManTimKiem extends AppCompatActivity {

    ListView listView;
    EditText edt;
    ArrayList<Truyen> TruyenArrayList;
    ArrayList<Truyen> arrayList;

    adapterTruyen adapterTruyen;
    databasedoctruyen databasedocTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        Anhxa();
        InitList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManTimKiem.this,ManNoiDung.class);
                String tenT = arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen", tenT);
                intent.putExtra("noidung", noidungt);
                startActivity(intent);
            }
        });

        //edtText search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    //search
    private void filter(String text){
        //xoa su lieu mang
        arrayList.clear();
        ArrayList<Truyen> filterList = new ArrayList<>();
        for(Truyen item : TruyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                //them item vao
                filterList.add(item);
                arrayList.add(item);
            }
        }
        adapterTruyen.filterList(filterList);
    }
//PHUONG THUC LAY DU LIEU
    private void InitList() {
        TruyenArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        databasedocTruyen = new databasedoctruyen(this);
        Cursor cursor = databasedocTruyen.getData2();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            arrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));
            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }

        cursor.moveToFirst();
        cursor.close();
    }

    private void Anhxa() {
        listView = findViewById(R.id.listViewTimKiem);
        edt = findViewById(R.id.timkiem);
    }
}