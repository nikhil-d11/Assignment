package com.example.assignment.network;

import com.example.assignment.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ResponseParser {


    public static ArrayList<Movie> parse_search_result(JSONArray results) throws JSONException {
        //use gson
        ArrayList<Movie> list=new ArrayList<>();
        for (int i=0;i<results.length();i++){
            JSONObject obj=results.getJSONObject(i);
            Movie movie=new Movie();
            movie.setId(String.valueOf(obj.getInt("id")));
            movie.setOverview(obj.getString("overview"));
            movie.setPopularity(String.valueOf(obj.getDouble("popularity")));
            movie.setPoster_path(obj.getString("poster_path"));
            movie.setBackdrop_path(obj.getString("backdrop_path"));
            movie.setTitle(obj.getString("title"));
            movie.setVote_average(String.valueOf(obj.getDouble("vote_average")));
            movie.setVote_count(String.valueOf(obj.getInt("vote_count")));
            movie.setRelease_date(obj.getString("release_date"));
            movie.setGenres(obj.getJSONArray("genre_ids").toString());
            list.add(movie);
        }
        return  list;
    }
}
