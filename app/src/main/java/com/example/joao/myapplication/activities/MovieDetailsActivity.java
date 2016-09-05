package com.example.joao.myapplication.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.joao.myapplication.R;
import com.example.joao.myapplication.domain.Movie;
import com.example.joao.myapplication.infra.CustomVolleyRequestQueue;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageLoader mImageLoader;
    private Movie movie;
    private NetworkImageView details_poster;
    private TextView  details_title;
    private TextView  details_year;
    private TextView  details_released;
    private TextView  details_runtime;
    private TextView  details_genre;
    private TextView  details_director;
    private TextView  details_writer;
    private TextView  details_language;
    private TextView  details_awards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageLoader = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
                .getImageLoader();


        details_poster = (NetworkImageView) findViewById(R.id.details_poster);
        details_awards = (TextView) findViewById(R.id.details_awards);
        details_director = (TextView) findViewById(R.id.details_director);
        details_genre = (TextView) findViewById(R.id.details_genre);
        details_language = (TextView) findViewById(R.id.details_language);
        details_released = (TextView) findViewById(R.id.details_released);
        details_runtime = (TextView) findViewById(R.id.details_runtime);
        details_title = (TextView) findViewById(R.id.details_title);
        details_writer = (TextView) findViewById(R.id.details_writer);
        details_year = (TextView) findViewById(R.id.details_year);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();

        movie = (Movie) intent.getExtras().getParcelable("movie");
    }

    @Override
    protected void onResume(){
        super.onResume();

        details_awards.setText("Awards: "+movie.getAwards());
        details_director.setText("Director: "+movie.getDirector());
        details_genre.setText("Genre: "+movie.getGenre());
        details_language.setText("Language: "+movie.getLanguage());
        details_released.setText("Released: "+movie.getReleased());
        details_runtime.setText("Runtime: "+movie.getRuntime());
        details_title.setText(movie.getTitle());
        details_writer.setText("Writer: "+movie.getWriter());
        details_year.setText("Year: "+movie.getYear());

        details_poster.setDefaultImageResId(R.drawable.clacket);
        details_poster.setErrorImageResId(R.drawable.clacket);

        try {
            details_poster.setImageUrl(movie.getPoster(), this.mImageLoader);
        } catch (Exception e) {
            //do nothing
        }

    }





}
