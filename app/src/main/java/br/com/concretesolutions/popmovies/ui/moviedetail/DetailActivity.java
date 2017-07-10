package br.com.concretesolutions.popmovies.ui.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.data.model.Movie;
import br.com.concretesolutions.popmovies.ui.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {
    static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    static final String FORMAT_yyyy = "yyyy";

    @BindView(R.id.imageDetailMoviePoster)
    ImageView imageDetailMoviePoster;

    @BindView(R.id.tvMovieTitle)
    TextView textMovieTitle;

    @BindView(R.id.textMovieReliaseYear)
    TextView textMovieReliaseYear;

    @BindView(R.id.textMovieVoteAverage)
    TextView textMovieVoteAverage;

    @BindView(R.id.textMovieDescription)
    TextView textMovieDescription;

    private Movie movie;

    public static Intent getStartIntent(Context context, Movie movie) {
        return new Intent(context, DetailActivity.class)
                .putExtra(EXTRA_MOVIE, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        Picasso.with(this).load(Movie.URL_BASE_POSTER_MOVIE + movie.getPosterPath()).into(imageDetailMoviePoster);
        textMovieTitle.setText(movie.getTitle());
        textMovieReliaseYear.setText(DateFormat.format(FORMAT_yyyy, movie.getReleaseDate()));
        textMovieVoteAverage.setText(Double.toString(movie.getVoteAverage()));
        textMovieDescription.setText(movie.getOverview());
    }
}
