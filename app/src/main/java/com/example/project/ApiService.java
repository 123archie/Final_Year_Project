package com.example.project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/passengerreg/")
    Call<Model> createTask(@Body Model task);

}
