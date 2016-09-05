package com.example.joao.myapplication.persistence.sqlitedb;

import android.provider.BaseColumns;

/**
 * Created by joao on 26/08/16.
 */
public final class MovieAppContract {
    // To prevent someone from accidentally instatiate the contract class,
    // make the constructor private
    private MovieAppContract() {}

    /* Inner class that defines the movies table contents */
    public static class MovieTable implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_RATED = "rated";
        public static final String COLUMN_NAME_RELEASED = "released";
        public static final String COLUMN_NAME_RUNTIME = "runtime";
        public static final String COLUMN_NAME_GENRE = "genre";
        public static final String COLUMN_NAME_DIRECTOR = "director";
        public static final String COLUMN_NAME_WRITER = "writer";
        public static final String COLUMN_NAME_ACTORS = "actors";
        public static final String COLUMN_NAME_PLOT = "plot";
        public static final String COLUMN_NAME_LANGUAGE = "language";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_AWARDS = "awards";
        public static final String COLUMN_NAME_POSTER = "poster";
        public static final String COLUMN_NAME_METASCORE = "metascore";
        public static final String COLUMN_NAME_IMDBRATING = "imdbrating";
        public static final String COLUMN_NAME_IMDBVOTES = "imdbvotes";
        public static final String COLUMN_NAME_IMDBID = "imdbid";
        public static final String COLUMN_NAME_TYPE = "type";

    }
}


