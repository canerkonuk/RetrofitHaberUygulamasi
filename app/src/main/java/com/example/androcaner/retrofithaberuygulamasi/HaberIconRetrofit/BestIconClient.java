package com.example.androcaner.retrofithaberuygulamasi.HaberIconRetrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BestIconClient {

    private static Retrofit retrofitBaseIcon;
    private static final String BEST_ICON_BASE_URL="https://besticon-demo.herokuapp.com/";

    public static Retrofit getBastIconClient(){
        if(retrofitBaseIcon==null){

            retrofitBaseIcon=new Retrofit.Builder()
                    .baseUrl(BEST_ICON_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            return retrofitBaseIcon;
        }

        return retrofitBaseIcon;
    }

}
