package br.com.concretesolutions.popmovies.ui.movies;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.concretesolutions.popmovies.data.model.Movie;
import br.com.concretesolutions.popmovies.data.repository.MovieRepository;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

public class MoviesPresenterTest {
    private MoviesContract.MoviesPresenter presenter;

    @Mock
    MoviesContract.MoviesView view;

    @Mock
    MovieRepository repository;

    @Captor
    ArgumentCaptor<MovieRepository.MoviesCallback> callback;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new MoviesPresenter(view, repository);
    }

    @Test
    public void getMovies_shouldShowListMovies_whenActivityIsStarted() {
        final String sort = "top rated";
        final List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Original Title", "Portuguese", "Title", false, "Overview", new Date(), 8.0, 500, false, 10.0, "path.jpg"));
        movies.add(new Movie(2, "Original Title 2", "Portuguese 2", "Title 2", false, "Overview 2", new Date(), 8.0, 400, false, 10.0, "path1.jpg"));

        presenter.getMovies(sort);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showProgressLoad();

        verify(repository).getMovies(eq(sort), callback.capture());

        callback.getValue().onMoviesLoaded(movies);

        inOrder.verify(view).hideProgressLoad();

        verify(view).showMovies(movies);
    }

    @Test
    public void getMovies_shouldShowError_whenBadRequest() {
        final String sort = "top rated";
        final List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Original Title", "Portuguese", "Title", false, "Overview", new Date(), 8.0, 500, false, 10.0, "path.jpg"));
        movies.add(new Movie(2, "Original Title 2", "Portuguese 2", "Title 2", false, "Overview 2", new Date(), 8.0, 400, false, 10.0, "path1.jpg"));

        presenter.getMovies(sort);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showProgressLoad();

        verify(repository).getMovies(eq(sort), callback.capture());

        callback.getValue().onDataNotAvailable("Error");

        inOrder.verify(view).hideProgressLoad();

        verify(view).showError("Error");
    }
}