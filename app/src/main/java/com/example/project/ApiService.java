package com.example.project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/destinations/")
    Call<Model> createTask(@Body Model task);

}
