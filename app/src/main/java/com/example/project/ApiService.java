package com.example.project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface ApiService {
    @PUT("passengerreg/")
    Call<Model> createTask(@Body Model task);

}
