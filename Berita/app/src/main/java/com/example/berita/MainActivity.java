package com.example.berita;

import android.content.Intent;
import android.os.Bundle;

import com.example.berita.adapter.BeritaAdapter;
import com.example.berita.model.Berita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

import com.example.berita.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private static final int CODE_MAIN_ACTIVITY = 69 ;
    ListView beritaListView;
    FloatingActionButton fab;
    BeritaAdapter beritaAdapter;
    ArrayList<Berita> listBerita = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        onClickGroup();
    }

    void initView(){
        beritaListView = (ListView) findViewById(R.id.beritaList);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        beritaAdapter = new BeritaAdapter(getApplicationContext(),listBerita);
        beritaListView.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("request Code: ",String.valueOf(requestCode));
        Log.v("resutl: ","isinya: "+data.getStringExtra("data"));
        parse(data.getStringExtra("data"));

    }


    private void parse( String data){
        Gson gson = new Gson();
        Berita model = gson.fromJson(data,Berita.class);
        Log.v("IsiModel: ",model.getTitle());
        listBerita.add(model);
        beritaAdapter.notifyDataSetChanged();
    }

    void onClickGroup(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Data.class);
                intent.putExtra("id", "CEK");
                startActivityForResult(intent, CODE_MAIN_ACTIVITY);
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
