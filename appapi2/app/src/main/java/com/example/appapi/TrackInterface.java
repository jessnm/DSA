package com.example.appapi;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TrackInterface {
    String ENDPOINT = "http://10.0.2.2:8080/dsaApp/";

    @GET("tracks")
    Call<List<Track>> getTracks();
    @POST("tracks")
    Call<Track> addTrack(@Body Track track);
    @PUT("tracks")
    Call<Track> updateTrack(@Body Track track);
    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") String trackId);
}
