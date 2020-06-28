package com.example.assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    ArrayList<Movie> datas;
    Context context;
    AppDatabase database;

    public MovieAdapter(Context context,ArrayList<Movie> datas) {
        this.datas = datas;
        this.context = context;
        database=AppDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_cell, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {

        final Movie movie=datas.get(position);
        holder.title.setText(movie.getTitle());
        holder.bookmark.setChecked(movie.getBookmarked());
        holder.rating.setText("  "+movie.getVote_average());
        //Log.d("testing image url",Constants.image_base_url+movie.getPoster_path());

        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(Constants.image_base_url+movie.getPoster_path())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.cover, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("success","working");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("success","error");

                    }
                });


        holder.bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                movie.setBookmarked(isChecked);

                if(isChecked){
                    InsertBookmark task=new InsertBookmark();
                    task.execute(movie);
                }else {
                    DeleteBookmark task=new DeleteBookmark();
                    task.execute(movie);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,MovieDetailActivity.class);
                i.putExtra("data",movie);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,rating;
        private ImageView cover;
        private CheckBox bookmark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookmark=itemView.findViewById(R.id.movie_cell_bookmark);
            title=itemView.findViewById(R.id.movie_cell_title);
            cover=itemView.findViewById(R.id.movie_cell_image);
            rating=itemView.findViewById(R.id.movie_cell_rating);
        }

    }
    class InsertBookmark extends AsyncTask<Movie,Void, Void> {

        @Override
        protected Void doInBackground(Movie... models) {
            database.movieDao().Insert(models[0]);
            return null;
        }
    }


    class DeleteBookmark extends AsyncTask<Movie,Void, Void>{
        @Override
        protected Void doInBackground(Movie... models) {
            database.movieDao().Delete(models[0]);
            return null;
        }
    }
}
