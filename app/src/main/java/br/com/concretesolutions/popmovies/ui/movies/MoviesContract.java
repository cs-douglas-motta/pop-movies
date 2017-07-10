package br.com.concretesolutions.popmovies.ui.movies;

import java.util.List;

import br.com.concretesolutions.popmovies.data.model.Movie;

public interface MoviesContract {
    interface MoviesView {
        void updateMovies();

        void showProgressLoad();

        void hideProgressLoad();

        void showMovies(List<Movie> movies);

        void showError(String error);
    }

    interface MoviesPresenter {
        void getMovies(String moviesSort);
    }
}
