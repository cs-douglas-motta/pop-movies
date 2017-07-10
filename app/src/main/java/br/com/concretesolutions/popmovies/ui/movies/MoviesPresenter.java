package br.com.concretesolutions.popmovies.ui.movies;

import java.util.List;

import br.com.concretesolutions.popmovies.data.model.Movie;
import br.com.concretesolutions.popmovies.data.repository.MovieRepository;

public class MoviesPresenter implements MoviesContract.MoviesPresenter {
    private MovieRepository repository;
    private MoviesContract.MoviesView view;

    public MoviesPresenter(MoviesContract.MoviesView view, MovieRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getMovies(String sort) {
        view.showProgressLoad();
        repository.getMovies(sort, new MovieRepository.MoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                view.hideProgressLoad();
                view.showMovies(movies);
            }

            @Override
            public void onDataNotAvailable(String error) {
                view.hideProgressLoad();
                view.showError(error);
            }
        });
    }
}
