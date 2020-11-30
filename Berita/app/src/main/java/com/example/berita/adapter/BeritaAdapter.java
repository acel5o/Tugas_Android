package com.example.berita.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.berita.model.Berita;
import com.example.berita.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.List;

public class BeritaAdapter extends BaseAdapter{



    Context context;
    private List<Berita> list;


    public BeritaAdapter(Context context, List<Berita> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item_berita, null);
        }
        Berita berita = list.get(position);
        TextView titleTextView =(TextView) convertView.findViewById(R.id.titleTextView);
        TextView categoryTextView =(TextView) convertView.findViewById(R.id.categoryTextView);



        titleTextView.setText(berita.getTitle());
        categoryTextView.setText(berita.getCategory());
        String imageUri = berita.getUrl();
        ImageView ivBasicImage = (ImageView) convertView.findViewById(R.id.userImageView);
        Picasso.with(context).load(imageUri).into(ivBasicImage);


        return convertView;
    }
}
