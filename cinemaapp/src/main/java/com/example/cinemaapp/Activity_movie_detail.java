package com.example.cinemaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_movie_detail extends AppCompatActivity {

    private static final String TAG="com.example.cinemaapp";

    ImageView imgView;
    TextView title, genre, desc;
    MovieDetailModel movie;
    String movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //set the back (up) button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find all our view components
         imgView=(ImageView) findViewById(R.id.image);
         title=(TextView) findViewById(R.id.title);
         genre=(TextView) findViewById(R.id.genre);
         desc=(TextView) findViewById(R.id.description);

        //get data that was sent from previous activity
        Intent intent = getIntent();
        movie_id=intent.getStringExtra("movie_id");
        String movie_title=intent.getStringExtra("movie_title");

        getRemoteData();

        //set the title of this activity to be the street name
        getSupportActionBar().setTitle(movie_title);
    }

    //populate layout with remote data
    private void  processData(){
        //set elements
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        desc.setText(movie.getDescription());

        //set movie image
        Glide.with(Activity_movie_detail.this)
                .load(movie.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imgView);
    }

//get data from Http
    private void getRemoteData(){
        ApiService apiService=ApiClient.getClient().create(ApiService.class);
        Call<MovieDetailModel> call=apiService.getMovie(Integer.parseInt(movie_id));
        call.enqueue(new Callback<MovieDetailModel>() {
            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                movie = (MovieDetailModel) response.body();
                Log.i(TAG, movie.getTitle());
                processData();
            }
            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
