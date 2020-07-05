package com.example.assignment.extras;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Constants {

    public static String API_KEY="9a7081625e914144d3e2ff03710063ef";

    public static String image_base_url="https://image.tmdb.org/t/p/w500";

    public static JSONArray json_genres;
    public static JSONObject genres=new JSONObject();
    static {
        try {
            json_genres = (new JSONObject("{\r\n    \"genres\": [\r\n        {\r\n            \"id\": 28,\r\n            \"name\": \"Action\"\r\n        },\r\n        {\r\n            \"id\": 12,\r\n            \"name\": \"Adventure\"\r\n        },\r\n        {\r\n            \"id\": 16,\r\n            \"name\": \"Animation\"\r\n        },\r\n        {\r\n            \"id\": 35,\r\n            \"name\": \"Comedy\"\r\n        },\r\n        {\r\n            \"id\": 80,\r\n            \"name\": \"Crime\"\r\n        },\r\n        {\r\n            \"id\": 99,\r\n            \"name\": \"Documentary\"\r\n        },\r\n        {\r\n            \"id\": 18,\r\n            \"name\": \"Drama\"\r\n        },\r\n        {\r\n            \"id\": 10751,\r\n            \"name\": \"Family\"\r\n        },\r\n        {\r\n            \"id\": 14,\r\n            \"name\": \"Fantasy\"\r\n        },\r\n        {\r\n            \"id\": 36,\r\n            \"name\": \"History\"\r\n        },\r\n        {\r\n            \"id\": 27,\r\n            \"name\": \"Horror\"\r\n        },\r\n        {\r\n            \"id\": 10402,\r\n            \"name\": \"Music\"\r\n        },\r\n        {\r\n            \"id\": 9648,\r\n            \"name\": \"Mystery\"\r\n        },\r\n        {\r\n            \"id\": 10749,\r\n            \"name\": \"Romance\"\r\n        },\r\n        {\r\n            \"id\": 878,\r\n            \"name\": \"Science Fiction\"\r\n        },\r\n        {\r\n            \"id\": 10770,\r\n            \"name\": \"TV Movie\"\r\n        },\r\n        {\r\n            \"id\": 53,\r\n            \"name\": \"Thriller\"\r\n        },\r\n        {\r\n            \"id\": 10752,\r\n            \"name\": \"War\"\r\n        },\r\n        {\r\n            \"id\": 37,\r\n            \"name\": \"Western\"\r\n        }\r\n    ]\r\n}")).getJSONArray("genres");

            for(int i=0;i<json_genres.length();i++){
                JSONObject object=json_genres.getJSONObject(i);
                genres.put(object.getString("id"),object.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
