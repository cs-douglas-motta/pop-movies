package br.com.concretesolutions.popmovies.data.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.concretesolutions.popmovies.data.model.Movie;

public class MovieResponse {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
