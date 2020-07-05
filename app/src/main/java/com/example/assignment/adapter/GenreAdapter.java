package com.example.assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.database.AppDatabase;
import com.example.assignment.extras.Constants;
import com.example.assignment.models.Movie;
import com.example.assignment.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder>{

    ArrayList<String> datas;
    Context context;

    public GenreAdapter(Context context,ArrayList<String> datas) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.genre_cell, parent, false);
        return new GenreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String s=datas.get(position);
        holder.genre.setText(s);
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView genre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            genre=itemView.findViewById(R.id.genre_cell);
        }

    }

}

