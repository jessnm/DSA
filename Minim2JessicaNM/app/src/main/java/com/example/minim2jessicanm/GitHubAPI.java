package com.example.minim2jessicanm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubAPI {

    String ENDPOINT = "https://api.github.com/";

    @GET("users/{user}")
    Call<User> getUserDetails(@Path("user") String username);

    @GET("users/{user}/repos")
    Call<List<Repos>> getRepoDetails(@Path("user") String username);
}
