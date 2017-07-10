package br.com.concretesolutions.popmovies.data.repository;

import java.util.List;

import br.com.concretesolutions.popmovies.data.model.Movie;

public interface MovieRepository<T> {
    interface MoviesCallback {
        void onMoviesLoaded(List<Movie> movies);

        void onDataNotAvailable(String error);
    }

    void getMovies(String sorter, MoviesCallback callback);
}
