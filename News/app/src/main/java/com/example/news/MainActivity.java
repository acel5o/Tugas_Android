package com.example.news;

import android.content.Intent;
import android.os.Bundle;

import com.example.news.adapter.BeritaAdapter;
import com.example.news.model.Berita;
import com.example.news.viewmodels.BeritaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Berita> beritaArrayList = new ArrayList<>();
    BeritaAdapter beritaAdapter;
    RecyclerView rvBerita;
    BeritaViewModel beritaViewModel;
    FloatingActionButton fab;
    List<Berita> beritas;

    @Override
    protected void onResume() {
        super.onResume();
        getListNasabah("1","20");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById();
        onClickGroup();
        initData();
        onResume();
    }

    void findViewById(){
        rvBerita = findViewById(R.id.beritaRecyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initData() {
        if (beritaAdapter == null) {
            beritaAdapter = new BeritaAdapter(MainActivity.this, beritaArrayList);
            rvBerita.setLayoutManager(new LinearLayoutManager(this));
            rvBerita.setAdapter(beritaAdapter);
            rvBerita.setItemAnimator(new DefaultItemAnimator());
            rvBerita.setNestedScrollingEnabled(true);
        } else {
            beritaAdapter.notifyDataSetChanged();
        }
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);

        beritaViewModel.init();
        beritaViewModel.getBeritasRepository().observe(this, nasabahsResponse -> {
            beritas = nasabahsResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });


    }
    private void getListNasabah(String page, String limit ){
        beritaViewModel.refresh(page,limit);
        beritaViewModel.getBeritasRepository().observe(this, nasabahsResponse -> {
            beritas = nasabahsResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });
    }
    void onClickGroup(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent( getApplicationContext(), ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
