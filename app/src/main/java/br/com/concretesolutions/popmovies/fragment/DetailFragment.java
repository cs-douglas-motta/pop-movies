package br.com.concretesolutions.popmovies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.activity.DetailActivity;
import br.com.concretesolutions.popmovies.model.Movie;

public class DetailFragment extends Fragment {
    private Movie movie;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailActivity.EXTRA_MOVIE, movie);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = getArguments().getParcelable(DetailActivity.EXTRA_MOVIE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView ivDetailMoviePoster = (ImageView) rootView.findViewById(R.id.ivDetailMoviePoster);
        TextView tvMovieTitle = (TextView) rootView.findViewById(R.id.tvMovieTitle);
        TextView tvMovieReliaseYear = (TextView) rootView.findViewById(R.id.tvMovieReliaseYear);
        TextView tvMovieVoteAverage = (TextView) rootView.findViewById(R.id.tvMovieVoteAverage);
        TextView tvMovieDescription = (TextView) rootView.findViewById(R.id.tvMovieDescription);

        Picasso.with(getActivity()).load(Movie.URL_BASE_POSTER_MOVIE + movie.getPosterPath()).into(ivDetailMoviePoster);
        tvMovieTitle.setText(movie.getTitle());
        tvMovieReliaseYear.setText(DateFormat.format("yyyy", movie.getReleaseDate()));
        tvMovieVoteAverage.setText(Double.toString(movie.getVoteAverage()));
        tvMovieDescription.setText(movie.getOverview());

        return rootView;
    }

}
