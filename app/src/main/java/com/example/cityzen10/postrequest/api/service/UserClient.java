package com.example.cityzen10.postrequest.api.service;

import com.example.cityzen10.postrequest.api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {
    @POST("users")
    Call<User> createAccount(@Body User user);
}
