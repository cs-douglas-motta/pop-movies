package br.com.concretesolutions.popmovies.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.activity.DetailActivity;
import br.com.concretesolutions.popmovies.adapter.MoviesAdapter;
import br.com.concretesolutions.popmovies.api.ListMovies;
import br.com.concretesolutions.popmovies.api.MovieService;
import br.com.concretesolutions.popmovies.api.RetrofitApi;
import br.com.concretesolutions.popmovies.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements MoviesAdapter.ItemClickListener {
    private static final String TAG_LOG = MainFragment.class.getSimpleName();

    private RecyclerView rvMovies;
    private List<Movie> movies;
    private MoviesAdapter moviesAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(int index) {
        MainFragment f = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        rvMovies = (RecyclerView) rootView.findViewById(R.id.rvMovies);

        moviesAdapter = new MoviesAdapter(getActivity(), new ArrayList<Movie>(), this);
        rvMovies.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(mLayoutManager);
        rvMovies.setAdapter(moviesAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();

    }

    public void updateMovies() {
        final MovieService movieService = RetrofitApi.getInstance().create(MovieService.class);

        Call<ListMovies> listMovies = null;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (sharedPreferences != null) {
            String sortMovies = sharedPreferences.getString(getString(R.string.pref_key), getString(R.string.pref_default));

            if (TextUtils.equals(sortMovies, getString(R.string.pref_popular))) {
                listMovies = movieService.listPopularMovies(getString(R.string.api_key));
            } else if (TextUtils.equals(sortMovies, getString(R.string.pref_top_rated))) {
                listMovies = movieService.listTopRatedMovies(getString(R.string.api_key));
            }

        } else {
            listMovies = movieService.listPopularMovies(getString(R.string.api_key));
        }

        listMovies.enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                if (response.isSuccessful()) {
                    movies = response.body().getMovies();
                    moviesAdapter.clear();
                    moviesAdapter.addList(movies);
                } else {
                    Log.d(TAG_LOG, "Call api not successful");
                }
            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {
                Log.d(TAG_LOG, "Call api falure", t);
            }
        });
    }



    @Override
    public void onItemClick(Movie movie) {
        startActivity(new Intent(getActivity(), DetailActivity.class).putExtra(DetailActivity.EXTRA_MOVIE, movie));
    }
}
