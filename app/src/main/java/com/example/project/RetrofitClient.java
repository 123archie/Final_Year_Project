package com.example.project;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String URL="https://fingerprint-quxo.vercel.app/";
    private static Retrofit retrofit=null;
    public static Retrofit getConnection(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
