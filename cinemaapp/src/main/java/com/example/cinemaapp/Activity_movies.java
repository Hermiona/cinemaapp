package com.example.cinemaapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_movies extends AppCompatActivity {

    private static final String TAG="com.example.cinemaapp";
    ArrayList<MovieModel> movies=new ArrayList<>();
    ListView list;
    String cinema_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        //get data that was sent from previous activity
        Intent intent = getIntent();
        cinema_id=intent.getStringExtra("cinema_id");
        String ciname_name=intent.getStringExtra("cinema_name");
        Log.i(TAG,cinema_id);
        //set the back (up) button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //set the title of this activity to be the street name
        getSupportActionBar().setTitle(ciname_name);


        list=(ListView) findViewById(R.id.moviesList);

        getRemoteData();

    }
//custom adapter for movie item
    class MyAdapter extends ArrayAdapter<MovieModel>{
        private final Context context;
        private final List<MovieModel> movies;

        public MyAdapter(Context context, ArrayList<MovieModel> objects) {
            super(context, -1, objects);
            this.context = context;
            this.movies = objects;
        }

        //called when rendering the list
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //get the movie we are displaying
            MovieModel movie=movies.get(position);
            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item=inflater.inflate(R.layout.movie_item, parent, false);
            TextView title=(TextView) item.findViewById(R.id.txtMovieName);
            ImageView imageView=(ImageView) item.findViewById(R.id.imgMovie);
            //set movie title
            title.setText(movie.getTitle());
            //set movie image
            Glide.with(Activity_movies.this)
                    .load(movie.getImgUrl())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
            return item;
        }
    }



    private void  processData(){
        //create adapter
        MyAdapter adapter=new MyAdapter(this, movies);
        list.setAdapter(adapter);
        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener=new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieModel movie=movies.get(position);
                Intent intent=new Intent(Activity_movies.this, Activity_movie_detail.class);
                //send data to Activity_movie_detail
                intent.putExtra("movie_id", String.valueOf(movie.getId()));
                intent.putExtra("movie_title", String.valueOf(movie.getTitle()));

                startActivity(intent);
            }
        };
        //set the listener to the list view
        list.setOnItemClickListener(adapterViewListener);
    }

    //get data from Http
    private void getRemoteData(){
        ApiService apiService=ApiClient.getClient().create(ApiService.class);
        Call<List<MovieModel>> call=apiService.getMovies(Integer.parseInt(cinema_id));
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movies = (ArrayList<MovieModel>) response.body();
                for (MovieModel model : movies) {
                    Log.i(TAG, model.getTitle());
                }

                processData();
            }
            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
