package com.example.joao.myapplication.persistence.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.joao.myapplication.domain.Movie;
import com.example.joao.myapplication.persistence.sqlitedb.MovieAppContract;
import com.example.joao.myapplication.persistence.sqlitedb.MovieAppDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 26/08/16.
 */
public class MovieSQLiteDAO implements IDao<Movie> {

    MovieAppDbHelper mDbHelper;

    public MovieSQLiteDAO(Context context) {
        mDbHelper = new MovieAppDbHelper(context);
    }

    @Override
    public void insert(Movie m) {
        // Gets the data repository in write mode

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_TITLE, m.getTitle());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_ACTORS, m.getActors());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_AWARDS, m.getAwards());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_COUNTRY, m.getCountry());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_DIRECTOR, m.getDirector());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_GENRE, m.getGenre());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_IMDBID, m.getImdbID());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_IMDBRATING, m.getImdbRating());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_IMDBVOTES, m.getImdbVotes());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_LANGUAGE, m.getLanguage());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_METASCORE, m.getMetascore());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_PLOT, m.getPlot());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_POSTER, m.getPoster());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_RATED, m.getRated());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_RELEASED, m.getReleased());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_RUNTIME, m.getRuntime());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_WRITER, m.getWriter());
        values.put(MovieAppContract.MovieTable.COLUMN_NAME_YEAR, m.getYear());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(MovieAppContract.MovieTable.TABLE_NAME, null, values);

    }

    @Override
    public List<Movie> select(Object key) {

        if( (key != null) && !( key instanceof String) ) return null;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                MovieAppContract.MovieTable.COLUMN_NAME_TITLE,
                MovieAppContract.MovieTable.COLUMN_NAME_YEAR,
                MovieAppContract.MovieTable.COLUMN_NAME_WRITER,
                MovieAppContract.MovieTable.COLUMN_NAME_RUNTIME,
                MovieAppContract.MovieTable.COLUMN_NAME_RELEASED,
                MovieAppContract.MovieTable.COLUMN_NAME_LANGUAGE,
                MovieAppContract.MovieTable.COLUMN_NAME_GENRE,
                MovieAppContract.MovieTable.COLUMN_NAME_DIRECTOR,
                MovieAppContract.MovieTable.COLUMN_NAME_AWARDS,
                MovieAppContract.MovieTable.COLUMN_NAME_POSTER
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = null;
        String[] selectionArgs = null;

        if(key != null) {
           selection = MovieAppContract.MovieTable.COLUMN_NAME_TITLE + " = ?";
           selectionArgs = new String[] { (String) key };
        }

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                MovieAppContract.MovieTable.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                MovieAppContract.MovieTable.TABLE_NAME,   // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        List<Movie> resultList = new ArrayList<Movie>();

        cursor.moveToFirst();


        while( !cursor.isAfterLast() ) {
            resultList.add(readMovieFromCursor(cursor));
            cursor.moveToNext();
        }

        return resultList;
    }

    @Override
    public void remove(Object key) {

        if( !(key instanceof String) ) return;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = MovieAppContract.MovieTable.COLUMN_NAME_TITLE + " = ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = { (String) key };

        // Issue SQL statement.
        db.delete(MovieAppContract.MovieTable.TABLE_NAME, selection, selectionArgs);

    }


    private Movie readMovieFromCursor(Cursor cursor) {

        Movie m = new Movie();

        m.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_TITLE)));
        m.setAwards(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_AWARDS)));
        m.setDirector(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_DIRECTOR)));
        m.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_GENRE)));
        m.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_LANGUAGE)));
        m.setReleased(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_RELEASED)));
        m.setRuntime(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_RUNTIME)));
        m.setWriter(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_WRITER)));
        m.setYear(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_YEAR)));
        m.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(MovieAppContract.MovieTable.COLUMN_NAME_POSTER)));


        return m;
    }

}
