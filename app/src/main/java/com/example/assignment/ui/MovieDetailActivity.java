package com.example.assignment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;
import com.example.assignment.adapter.GenreAdapter;
import com.example.assignment.extras.Constants;
import com.example.assignment.models.Movie;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView cover;
    TextView title,overview,rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        Movie movie= (Movie) getIntent().getSerializableExtra("data");

        cover=findViewById(R.id.movie_detail_cover);
        title=findViewById(R.id.movie_detail_title);
        overview=findViewById(R.id.movie_detail_overview);
        rating=findViewById(R.id.movie_detail_rating);


        Picasso.with(this)
                .load(Constants.image_base_url+ movie.getBackdrop_path())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(cover);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        rating.setText(" "+movie.getVote_average()+"("+movie.getVote_count()+")");

        JSONObject genres=Constants.genres;

        ArrayList<String> genreslist=new ArrayList<>();
        JSONArray genre_ids = null;
        try {
            genre_ids=new JSONArray(movie.getGenres());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(genre_ids!=null){
            for(int i=0;i<genre_ids.length();i++){
                try {
                    genreslist.add(genres.getString(String.valueOf(genre_ids.getInt(i))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


        RecyclerView genre_recycler=findViewById(R.id.recycler_genre);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        GenreAdapter adapter=new GenreAdapter(this,genreslist);
        genre_recycler.setLayoutManager(layoutManager);
        genre_recycler.setAdapter(adapter);


    }
}