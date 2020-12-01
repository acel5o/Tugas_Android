package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.news.model.Berita;
import com.example.news.viewmodels.BeritaViewModel;

public class ViewActivity extends AppCompatActivity {

    private String id,title,category,url;
    private Berita berita=new Berita();

    EditText titleEditText,categoryEditText,urlEditText;
    Button saveButton;
    String mode="add";
    BeritaViewModel beritaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        findViewById();
        initData();
        onClickGroup();
    }
    void findViewById(){
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        categoryEditText = (EditText) findViewById(R.id.categoryEditText);
        urlEditText = (EditText) findViewById(R.id.urlEditText);

        saveButton = (Button) findViewById(R.id.saveButton);
    }
    void initData(){
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);
        Bundle bundle = getIntent().getExtras();
        mode=bundle.getString("mode","add");
        id=bundle.getString("id","");
        title=bundle.getString("title","");
        category=bundle.getString("category","");
        url=bundle.getString("url","");
    }
    void onClickGroup(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berita beritaPayload = new Berita();
                beritaPayload.setTitle(titleEditText.getText().toString());
                beritaPayload.setCategory(categoryEditText.getText().toString());
                beritaPayload.setUrl(urlEditText.getText().toString());
                postNasabah(beritaPayload);
            }
        });

    }

    private void postNasabah(Berita beritaPayload ){
        beritaViewModel.postBeritaRepository(beritaPayload).observe(this, beritaResponse -> {
            berita = beritaResponse.getData();
            finish();
        });
    }

}
