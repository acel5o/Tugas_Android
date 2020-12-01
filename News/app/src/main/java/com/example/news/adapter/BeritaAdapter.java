package com.example.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.news.model.Berita;
import com.example.news.R;
import com.squareup.picasso.Picasso;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    Context context;
    ArrayList<Berita> beritas;

    public BeritaAdapter(Context context, ArrayList<Berita> beritas) {
        this.context = context;
        this.beritas = beritas;
    }

    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        return new  BeritaViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder holder, final int position) {
        holder.titleTv.setText(beritas.get(position).getTitle());
        holder.categoryTv.setText(beritas.get(position).getCategory());
        beritas.get(position).setUrl(beritas.get(position).getUrl());
        String imageUri = beritas.get(position).getUrl();
        Picasso.get().load(imageUri).into(holder.urlTv);


    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder{

        TextView titleTv;
        ImageView urlTv;
        TextView categoryTv;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.titleTextView);
            urlTv = itemView.findViewById(R.id.urlTextView);
            categoryTv = itemView.findViewById(R.id.categoryTextView);
        }
    }
}

