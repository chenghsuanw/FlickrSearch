package com.example.flickrsearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager instance = new RetrofitManager();
    private FlickrAPI flickrApi;

    private RetrofitManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        flickrApi = retrofit.create(FlickrAPI.class);
    }

    public static RetrofitManager getInstance() {
        return instance;
    }

    public FlickrAPI getAPI() {
        return flickrApi;
    }
}
