package br.com.concretesolutions.popmovies.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.concretesolutions.popmovies.model.Movie;

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
