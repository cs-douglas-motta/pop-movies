package br.com.concretesolutions.popmovies.ui.movies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MoviesViewTest {
    MoviesContract.MoviesView view;

    @Mock
    MoviesContract.MoviesPresenter presenter;

    @Test
    public void updateMovies_shouldGetMovies_whenActivityIsStarted() {
//        view = Robolectric.setupActivity(MainActivity.class);
//        final String sort = "teste";
//
//        view.updateMovies();
//
//        verify(presenter).getMovies(sort);
    }
}
