package br.com.concretesolutions.popmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by douglasmotta on 08/05/17.
 */

public class ListMovies {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
