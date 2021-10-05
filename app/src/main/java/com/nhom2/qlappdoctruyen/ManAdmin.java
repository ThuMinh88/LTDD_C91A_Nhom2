package com.nhom2.qlappdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nhom2.qlappdoctruyen.database.databasedoctruyen;
import com.nhom2.qlappdoctruyen.model.Truyen;
import java.lang.reflect.Array;
import java.util.ArrayList;

import adapter.adapterTruyen;

public class ManAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;

    com.nhom2.qlappdoctruyen.database.databasedoctruyen databasedoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonThemtruyen);

        initList();

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy id tài khoản để biết tài khoản admin nào đã vào chỉnh sửa
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id", 0);

                //tiếp tục gửi id qua màn hình thêm truyện
                Intent intent = new Intent(ManAdmin.this, ManDangBai.class);
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });
        //click item long sẽ xoá item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DialogDelete(position);

                return false;

            }
        });
    }

    //hiển thị cửa sổ xoá
    private void  DialogDelete(int position){
        Dialog dialog = new Dialog(this);
        //nạp layout
        dialog.setContentView(R.layout.dialogdelete);
        //chỉ no mới đc đóng
        //dialog.setCanceledOnTouchOutside(false);

        //ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idtruyen = TruyenArrayList.get(position).getID();

                //xoá dữ liệu
                databasedoctruyen.Delete(idtruyen);

                //cập nhật lại activity
                Intent intent = new Intent(ManAdmin.this,ManAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(ManAdmin.this,"Xoá truyện thành công", Toast.LENGTH_SHORT).show();

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //run...
        dialog.show();
    }
    //gán dữ liệu cho listview
    private void initList() {
        TruyenArrayList = new ArrayList<>();

        databasedoctruyen = new databasedoctruyen(this);

        Cursor cursorl = databasedoctruyen.getData2();

        while (cursorl.moveToNext()){
            int id = cursorl.getInt(0);
            String tentruyen = cursorl.getString(1);
            String noidung = cursorl.getString(2);
            String anh = cursorl.getString(3);
            int id_tk = cursorl.getInt(4);

            TruyenArrayList.add(new Truyen(id, tentruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursorl.moveToFirst();
        cursorl.close();
    }
}