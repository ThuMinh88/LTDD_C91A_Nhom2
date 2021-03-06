package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom2.qlappdoctruyen.R;
import com.nhom2.qlappdoctruyen.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txtTenTryen;
        ImageView imgTruyen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen, null);

        viewHolder.txtTenTryen = convertView.findViewById(R.id.textviewTenTruyenNew);
        viewHolder.imgTruyen = convertView.findViewById(R.id.imgNewTruyen);
        convertView.setTag(viewHolder);

        Truyen truyen = (Truyen)getItem(position);
        viewHolder.txtTenTryen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_24).into(viewHolder.imgTruyen);

        return convertView;
    }

}
