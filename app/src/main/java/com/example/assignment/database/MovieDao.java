package com.example.assignment.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.assignment.models.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("select * from movies")
    List<Movie> get_bookmarks();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert(Movie movie);

    @Delete
    void Delete(Movie movie);

}
