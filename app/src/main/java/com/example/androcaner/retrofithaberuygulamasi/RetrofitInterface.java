package com.example.androcaner.retrofithaberuygulamasi;

import com.example.androcaner.retrofithaberuygulamasi.HaberIconRetrofit.BestIcon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("top-headlines")
    Call<News> getNews(@Query("country")String country, @Query("apiKey") String apiKey );



    @GET("allicons.json")
    Call<BestIcon> getBestIcon(@Query("url")String url);


}
