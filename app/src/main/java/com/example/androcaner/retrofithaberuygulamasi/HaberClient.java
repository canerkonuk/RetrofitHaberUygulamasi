package com.example.androcaner.retrofithaberuygulamasi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HaberClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://newsapi.org/v2/";


    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            return retrofit;

        }
        return retrofit;

    }


}
