package id.co.imastudio.portalberita;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
ImageView img;
    TextView tvV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = (ImageView)findViewById(R.id.imgDet);
        tvV =(TextView) findViewById(R.id.tvDet);

        tvV.setText(getIntent().getStringExtra("isi_berita"));
        Glide.with(getApplicationContext())
                .load("http://192.168.43.153/berita/foto_berita/"+
                      getIntent().getStringExtra("gambar"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(img);
        setTitle(getIntent().getStringExtra("judul"));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
