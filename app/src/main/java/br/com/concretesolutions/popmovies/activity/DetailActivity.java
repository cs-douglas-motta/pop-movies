package br.com.concretesolutions.popmovies.activity;

import android.os.Bundle;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.fragment.DetailFragment;
import br.com.concretesolutions.popmovies.model.Movie;

public class DetailActivity extends BaseActivity {
    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, DetailFragment.newInstance((Movie) getIntent().getParcelableExtra(EXTRA_MOVIE)))
                .commit();
    }
}
