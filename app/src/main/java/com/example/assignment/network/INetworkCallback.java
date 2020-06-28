package com.example.assignment.network;

import com.example.assignment.models.Movie;

import java.util.ArrayList;

public interface INetworkCallback<T> {
    public void OnSuccess(ArrayList<Movie> data, int pagecount);

    public void OnError(Exception e);
    //read about generics
}
