package id.co.imastudio.portalberita;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Taufiq on 1/6/2017.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<HashMap<String,String>> listdata;
    Context context;

    public CustomAdapter(FragmentActivity activity, ArrayList<HashMap<String, String>> data) {
    this.context = activity;
    this.listdata = data;

    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item, null);
        TextView judul = (TextView)v.findViewById(R.id.txtBuah);
        ImageView image = (ImageView)v.findViewById(R.id.imgBuah);
        judul.setText(listdata.get(i).get("judul"));
        Glide.with(context)
                .load("http://192.168.43.153/berita/foto_berita/"+
                        listdata.get(i).get("gambar"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(image);
        return v;
    }
}
