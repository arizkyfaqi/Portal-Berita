package id.co.imastudio.portalberita;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment {
//deklarasi variable
    ListView lsView;
    AQuery aq;
    //untuk get data array dalam bentuk array list yg diinisialisasikan di java classnya
    ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String ,String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_berita, container, false);

        //ini
        aq = new AQuery(getActivity());
        lsView = (ListView)v.findViewById(R.id.listBerita);

        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String,String> map = data.get(i);
                Intent a = new Intent(getActivity(),DetailActivity.class);
                a.putExtra("id_berita",map.get("id_berita"));
                a.putExtra("judul",map.get("judul"));
                a.putExtra("isi_berita",map.get("isi_berita"));
                a.putExtra("gambar",map.get("gambar"));
                startActivity(a);
            }
        });
        getBerita();
        return v;
    }

    private void getBerita() {

    String url ="http://192.168.43.153/berita/getdata.php";
  //membuat dialog progress sebelum di load
    ProgressDialog  progres = new ProgressDialog(getActivity());
    progres.setMessage("Loading...");
    progres.setCancelable(false);
    progres.setIndeterminate(false);
    //proses saat progress bar ngeload data
    aq.progress(progres).ajax(url,String.class,
            new AjaxCallback<String>() {
                @Override
                public void callback(String url, String object, AjaxStatus status) {
                   if (object !=null){
                       try {
                           JSONObject json = new JSONObject(object);
                           String pesan = json.getString("message");
                           String hasil = json.getString("success");
                           JSONArray jray = json.getJSONArray("array");
                            if (hasil.equalsIgnoreCase("true")){
                                for (int a =0; a<jray.length(); a++){
                                    JSONObject jo = jray.getJSONObject(a);
                                    String id = jo.getString("id_berita");
                                    String judul = jo.getString("judul");
                                    String gambar = jo.getString("gambar");
                                    String detail = jo.getString("isi_berita");
                                    HashMap<String,String> map = new HashMap<String, String>();

                                    map.put("id_berita",id);
                                    map.put("judul",judul);
                                    map.put("gambar",gambar);
                                    map.put("isi_berita",detail);

                                    data.add(map);
                                    tampilData(data);
                                }
   Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                            }else{
   Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                            }
                       } catch (JSONException e) {
                           e.printStackTrace();
   Toast.makeText(getActivity(), "Json Gagal", Toast.LENGTH_SHORT).show();
                       }
                   }else {
  Toast.makeText(getActivity(), "Objec Not FOund", Toast.LENGTH_SHORT).show();
                   }
                }
            } );


    }

    private void tampilData(ArrayList<HashMap<String, String>> data) {
        CustomAdapter  adapter = new CustomAdapter(getActivity(), data);
        lsView.setAdapter(adapter);
    }


}
