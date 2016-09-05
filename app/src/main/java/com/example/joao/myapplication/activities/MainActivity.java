package com.example.joao.myapplication.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.joao.myapplication.R;
import com.example.joao.myapplication.activities.views.MoviesAdapter;
import com.example.joao.myapplication.domain.Movie;
import com.example.joao.myapplication.infra.CustomVolleyRequestQueue;
import com.example.joao.myapplication.persistence.repositories.MovieRepositorySearchResult;
import com.example.joao.myapplication.persistence.repositories.MovieRepositorySingleton;
import com.example.joao.myapplication.infra.RecyclerItemClickListener;
import com.example.joao.myapplication.persistence.repositories.Repository;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public final static String NOME_USER = "com.example.joao.myapplication.NOME_USER";

    private ImageLoader mImageLoader;

    private Repository<Movie> movieRepository;
    private Repository<Movie> searchRepository;

    private MoviesAdapter myAdapter;

    private TextView listName;

    private final String url ="http://www.omdbapi.com/?t=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listName = (TextView) findViewById(R.id.listName);

        movieRepository = MovieRepositorySingleton.getInstance(MainActivity.this);


        mImageLoader = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
                .getImageLoader();


        myAdapter = new MoviesAdapter(this,movieRepository,mImageLoader);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setAdapter(myAdapter);
        recList.setItemAnimator(new DefaultItemAnimator());
        recList.setLayoutManager(new LinearLayoutManager(this));
        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, recList, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onLongClick(View view, int position) {
                        makeIntent(position);
                    }
                })
        );


        NetworkImageView niv = (NetworkImageView) findViewById(R.id.thumbnail);
        if(niv != null) {
            niv.setDefaultImageResId(R.drawable.clacket);
            niv.setErrorImageResId(R.drawable.clacket);
        }
        //handleIntent(getIntent());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setSubmitButtonEnabled(false);

        searchView.setOnQueryTextListener(new MyQueryListener());
        MenuItem searchItem =  menu.findItem(R.id.search);

        MenuItemCompat.setOnActionExpandListener(searchItem, new MyActionExpandListener());

        return true;
    }

    /*@Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // remove leading and trailing spaces from the query
            query = query.trim();
            doMySearch(query);
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private void requestFilme(String url, final Repository<Movie> repository) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,
                url.replace(' ','-'), null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("Response").equals("True")  ) {
                        Movie f = new Movie();
                        f.loadJSON(response);
                        repository.add(f);
                        Toast.makeText(MainActivity.this, "Added to your movies", Toast.LENGTH_LONG).show();
                        listName.setText(getResources().getString(R.string.listNameSearch));
                        myAdapter.notifyDataSetChanged();
                    } else {
                        listName.setText(getResources().getString(R.string.listNotFound));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listName.setText(getResources().getString(R.string.listNoLocal));
                Toast.makeText(MainActivity.this, "An Error Occurred", Toast.LENGTH_LONG).show();

            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
        listName.setText(getResources().getString(R.string.listSearching));
    }

    private class MyActionExpandListener implements MenuItemCompat.OnActionExpandListener {
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {

            searchRepository = new MovieRepositorySearchResult(movieRepository);
            listName.setText(getResources().getString(R.string.listNameSearch));
            myAdapter.setMovieRepository(searchRepository);
            myAdapter.notifyDataSetChanged();
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            myAdapter.setMovieRepository(movieRepository);
            listName.setText(getResources().getString(R.string.listNameMovies));
            myAdapter.notifyDataSetChanged();
            searchRepository = null;
            return true;
        }
    }


    private void makeIntent(int position){
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie",searchRepository == null ? movieRepository.get(position):searchRepository.get(position));
        startActivity(intent);
    }

    private class MyQueryListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            query = query.trim();
            if(searchRepository.getItemCount() == 0) {
                requestFilme(url+query,searchRepository);
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            // remove leading and trailing spaces from the query
            query = query.trim();
            doMySearch(query);
            return true;
        }

        private void doMySearch(String query) {

            if(searchRepository == null) return;

            searchRepository.clear();
            if(query.length()>0) {
                //iterate over the main repository
                for (Movie m : movieRepository) {
                    // add the movie to the results if it matches the query
                    if (m.getTitle().toLowerCase().matches("(\\w|\\W)*" + query.toLowerCase() + "(\\w|\\W)*"))
                        searchRepository.add(m);
                }
            }
            myAdapter.notifyDataSetChanged();
            if(searchRepository.getItemCount() == 0)
                listName.setText(getResources().getString(R.string.listNoLocal));
            else
                listName.setText(getResources().getString(R.string.listNameSearch));
        }
    }
}
