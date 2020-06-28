package com.example.assignment.network;

import com.example.assignment.models.Movie;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import static com.example.assignment.extras.Constants.API_KEY;
import static com.example.assignment.network.ResponseParser.parse_search_result;

public class Api {


    public static void search_api(String query,String page_no,final INetworkCallback<ArrayList<Movie>> callback){

        //learn how to generalize this class
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.themoviedb.org")
                .addPathSegment("3")
                .addPathSegment("search")
                .addPathSegment("movie")
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("language", "en-US")
                .addQueryParameter("page", page_no)
                .addQueryParameter("query",query)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        client.newCall(request).enqueue(new Callback(){

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {


                    try {
                        JSONObject jsonObject=new JSONObject(response.body().string());
                        //Log.d("testing parsing",""+jsonObject.getInt("total_pages"));
                        callback.OnSuccess(parse_search_result(jsonObject.getJSONArray("results")),jsonObject.getInt("total_pages"));
                        //Log.d("testing parsing time",""+jsonObject.getInt("total_pages"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
