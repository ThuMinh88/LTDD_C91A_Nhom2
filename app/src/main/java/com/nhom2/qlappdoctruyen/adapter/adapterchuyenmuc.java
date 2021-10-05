package com.nhom2.qlappdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom2.qlappdoctruyen.R;
import com.nhom2.qlappdoctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterchuyenmuc extends BaseAdapter {
    private Context context;
    private int layout;
    private List<chuyenmuc> chuyenmucList;

    public adapterchuyenmuc(Context context, int layout, List<chuyenmuc> chuyenmucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucList = chuyenmucList;
    }

    @Override
    public int getCount() {
        return chuyenmucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        ImageView img = (ImageView) convertView.findViewById(R.id.imgchuyenmuc);

        TextView txt = (TextView) convertView.findViewById(R.id.textviewTenchuyenmuc);
        chuyenmuc cmuc = chuyenmucList.get(position);
        txt.setText(cmuc.getTenchuyenmuc());
        Picasso.get().load(cmuc.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);
        return convertView;
    }
}
