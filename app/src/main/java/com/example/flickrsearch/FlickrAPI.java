package com.example.flickrsearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlickrAPI {

    @GET("services/rest/")
    Call<Data> getRecent(@Query("method") String method, @Query("api_key") String api_key, @Query("format") String format, @Query("nojsoncallback") String nojsoncallback);

    @GET("services/rest/")
    Call<Data> search(@Query("method") String method, @Query("api_key") String api_key, @Query("text") String tags, @Query("format") String format, @Query("nojsoncallback") String nojsoncallback);
}
