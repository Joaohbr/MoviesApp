package com.example.joao.myapplication.persistence.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joao on 26/08/16.
 */
public class MovieAppDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MovieApp.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TABLE_MOVIE =
            "CREATE TABLE " + MovieAppContract.MovieTable.TABLE_NAME + " (" +
                    MovieAppContract.MovieTable._ID + " INTEGER PRIMARY KEY," +
                    MovieAppContract.MovieTable.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_YEAR + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_RATED + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_RELEASED + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_RUNTIME + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_GENRE + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_DIRECTOR + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_WRITER + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_ACTORS + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_PLOT + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_LANGUAGE + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_AWARDS + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_POSTER + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_METASCORE + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_IMDBRATING + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_IMDBVOTES + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_IMDBID + TEXT_TYPE + COMMA_SEP +
                    MovieAppContract.MovieTable.COLUMN_NAME_TYPE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_TABLE_MOVIE =
            "DROP TABLE IF EXISTS " + MovieAppContract.MovieTable.TABLE_NAME;

    public MovieAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
