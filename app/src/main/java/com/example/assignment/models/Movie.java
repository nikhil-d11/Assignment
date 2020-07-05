package com.example.assignment.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;

@Entity(tableName = "Movies")
public class Movie implements Serializable{

    @NotNull
    @PrimaryKey
    String id;
    //what if i don't mark all column info
    @ColumnInfo(name = "popularity")
    String popularity;

    @ColumnInfo(name = "vote_count")
    String vote_count;

    @ColumnInfo(name = "poster_path")
    String poster_path;

    @ColumnInfo(name = "backdrop_path")
    String backdrop_path;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "vote_average")
    String vote_average;

    @ColumnInfo(name = "overview")
    String overview;

    @ColumnInfo(name = "release_date")
    String release_date;

    @ColumnInfo(name = "bookmarked")
    Boolean bookmarked=false;

    @ColumnInfo(name = "genres")
    String genres;

    public String getGenres() {

        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Movie() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Boolean getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
