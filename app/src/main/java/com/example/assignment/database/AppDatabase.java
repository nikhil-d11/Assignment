package com.example.assignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment.models.Movie;

@Database(entities = {Movie.class},version = 4)
public abstract class AppDatabase extends RoomDatabase {
    private static final String db_name="ChecklistDB";
    private static AppDatabase instance;

    //what if 2 instances of database are present?
    public static synchronized AppDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,db_name)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

    public abstract MovieDao movieDao();


}