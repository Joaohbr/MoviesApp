package com.example.joao.myapplication.persistence.repositories;

import android.content.Context;
import android.util.Log;

import com.example.joao.myapplication.domain.Movie;
import com.example.joao.myapplication.persistence.dao.MovieSQLiteDAO;

import java.util.List;

/**
 * Created by joao on 12/08/16.
 * This class is a singleton
 */
public class MovieRepositorySingleton extends Repository<Movie> {

    private static MovieRepositorySingleton mInstance;

    private MovieSQLiteDAO banco;

    private MovieRepositorySingleton(Context context)
    {
        banco = new MovieSQLiteDAO(context);
        loadDb();
    }

    public static MovieRepositorySingleton getInstance(Context context)
    {
        if(mInstance == null) {
            mInstance = new MovieRepositorySingleton(context);
        }
        return mInstance;
    }

    @Override
    public boolean add(Movie m) {
        banco.insert(m);
        return super.add(m);
    }

    @Override
    public boolean remove(int position) {
        Movie m = get(position);
        if( m == null) return false;

        banco.remove(m.getTitle());
        super.remove(position);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        banco.remove( ((Movie)o).getTitle() );
        return super.remove(o);

    }

    private void loadDb()
    {
        List<Movie> l = banco.select(null);
        Log.d("LOAD DB",""+lista.size());
        lista.addAll(l);

    }
}
