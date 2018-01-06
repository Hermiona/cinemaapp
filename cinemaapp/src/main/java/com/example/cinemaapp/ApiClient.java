package com.example.cinemaapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 06.01.2018.
 */

public class ApiClient {
    static final String BASE_URL="http://188.166.60.39:8000/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }


}
