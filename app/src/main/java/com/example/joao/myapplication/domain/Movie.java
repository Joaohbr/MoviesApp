
package com.example.joao.myapplication.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.joao.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joao on 12/08/16.
 */
public class Movie implements Parcelable{

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String type;

    public Movie(){}
    public Movie(String title) { this.title = title; }
    protected Movie(Parcel in) {
        title = in.readString();
        year = in.readString();
        rated = in.readString();
        released = in.readString();
        runtime = in.readString();
        genre = in.readString();
        director = in.readString();
        writer = in.readString();
        actors = in.readString();
        plot = in.readString();
        language = in.readString();
        country = in.readString();
        awards = in.readString();
        poster = in.readString();
        metascore = in.readString();
        imdbRating = in.readString();
        imdbVotes = in.readString();
        imdbID = in.readString();
        type = in.readString();
    }


    public void loadJSON(JSONObject filme) throws JSONException{
            this.title = filme.getString("Title").trim().replace("-"," ");
            this.year = filme.getString("Year");
            this.rated = filme.getString("Rated");
            this.released = filme.getString("Released");
            this.runtime = filme.getString("Runtime");
            this.genre = filme.getString("Genre");
            this.director = filme.getString("Director");
            this.writer = filme.getString("Writer");
            this.actors = filme.getString("Actors");
            this.plot = filme.getString("Plot");
            this.language = filme.getString("Language");
            this.country = filme.getString("Country");
            this.awards = filme.getString("Awards");
            this.poster = filme.getString("Poster");
            this.metascore = filme.getString("Metascore");
            this.imdbRating = filme.getString("imdbRating");
            this.imdbVotes = filme.getString("imdbVotes");
            this.imdbID = filme.getString("imdbID");
            this.type = filme.getString("Type");

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeString(rated);
        parcel.writeString(released);
        parcel.writeString(runtime);
        parcel.writeString(genre);
        parcel.writeString(director);
        parcel.writeString(writer);
        parcel.writeString(actors);
        parcel.writeString(plot);
        parcel.writeString(language);
        parcel.writeString(country);
        parcel.writeString(awards);
        parcel.writeString(poster);
        parcel.writeString(metascore);
        parcel.writeString(imdbRating);
        parcel.writeString(imdbVotes);
        parcel.writeString(imdbID);
        parcel.writeString(type);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Movie)) {
            return false;
        }

        Movie m = (Movie) o;

        return m.title.equals(this.title);

    }

    @Override
    public int hashCode(){
        return this.title.hashCode();
    }

}

