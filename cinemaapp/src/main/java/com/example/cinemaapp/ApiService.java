package com.example.cinemaapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ASUS on 06.01.2018.
 */

public interface ApiService {
    @GET("cinemas")
    Call<List<CinemaModel>> getCinemas();

    @GET("films/{cinemaId}")
    Call<List<MovieModel>> getMovies(@Path("cinemaId") int cinemaId);

    @GET("film/{id}")
    Call<MovieDetailModel> getMovie(@Path("id") int id);
}
