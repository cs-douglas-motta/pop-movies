package br.com.concretesolutions.popmovies.ui.movies;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.data.model.Movie;
import br.com.concretesolutions.popmovies.data.repository.ApiMovieRepository;
import br.com.concretesolutions.popmovies.ui.BaseActivity;
import br.com.concretesolutions.popmovies.ui.moviedetail.DetailActivity;
import br.com.concretesolutions.popmovies.ui.settings.SettingsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MoviesAdapter.ItemClickListener, MoviesContract.MoviesView {
    @BindView(R.id.progressLoad)
    ProgressBar progressLoad;

    @BindView(R.id.recyclerMovies)
    RecyclerView recyclerMovies;

    private MoviesContract.MoviesPresenter presenter;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MoviesPresenter(this, new ApiMovieRepository());

        setupToolbar();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        moviesAdapter = new MoviesAdapter(this, new ArrayList<Movie>(), this);
        recyclerMovies.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerMovies.setLayoutManager(mLayoutManager);
        recyclerMovies.setAdapter(moviesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMovies();
    }

    @Override
    public void updateMovies() {
        String sort = "top rated";

        presenter.getMovies(sort);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressLoad() {
        progressLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressLoad() {
        progressLoad.setVisibility(View.GONE);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        moviesAdapter.clear();
        moviesAdapter.addList(movies);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onItemClick(Movie movie) {
        startActivity(DetailActivity.getStartIntent(this, movie));
    }
}
