package com.example.berita;

import android.content.Intent;
import android.os.Bundle;

import com.example.berita.model.Berita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

public class Data extends AppCompatActivity {
    private EditText titleEditText, categoryEditText, UrlEditText;
    private ImageView saveButton;
    ArrayList<Berita> listBerita = new ArrayList<>();;
    private ArrayList<String> arrTemp;
    private static String json="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Berita");

        initView();
        onClickGroup();
    }
    void initView(){
        arrTemp=getIntent().getStringArrayListExtra("data");
        saveButton = (ImageView) findViewById(R.id.saveButton);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        categoryEditText = (EditText) findViewById(R.id.categoryEditText);
        UrlEditText = (EditText) findViewById(R.id.urlEditText);

    }
    private void saveData(){
        String newtitle = titleEditText.getText().toString();
        String newcategory = categoryEditText.getText().toString();
        String newurl = UrlEditText.getText().toString();

        Berita model = new Berita();
        model.setTitle(newtitle);
        model.setCategory(newcategory);
        model.setUrl(newurl);
        Gson gson = new Gson();
        json=gson.toJson(model);

    }

    void onClickGroup(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(listBerita);
                Log.v("Isi Array: ",jsonArray.toString());
                Intent i = getIntent();
                i.putExtra("data", json);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
