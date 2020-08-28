package com.example.ieprojectv2;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnection {
    private OkHttpClient client=null;
    private String results;
    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");
    public NetworkConnection(){
        client=new OkHttpClient();
    }
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-37.876663,145.045089&radius=5000&types=hospital&openhour=true&sensor=false&key=AIzaSyBQMU5WdKseHha7NwRojzDV7jXz2fxz8ak";
    private static final String BASE_URL_clinic = "https://maps.googleapis.com/maps/api/place/search/json?location=-37.876663,145.045089&rankby=distance&types=doctor&openhour=ture&name=clinic&sensor=false&key=AIzaSyBQMU5WdKseHha7NwRojzDV7jXz2fxz8ak";




    public String searchHospital(){
        Request.Builder builder = new Request.Builder();
        builder.url(BASE_URL);


        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            results=response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public String searchClinic(){
        Request.Builder builder = new Request.Builder();
        builder.url(BASE_URL_clinic);


        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            results=response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }
}
