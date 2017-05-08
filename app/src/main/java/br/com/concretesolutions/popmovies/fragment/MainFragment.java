package br.com.concretesolutions.popmovies.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.adapter.MoviesAdapter;
import br.com.concretesolutions.popmovies.api.MovieService;
import br.com.concretesolutions.popmovies.model.ListMovies;
import br.com.concretesolutions.popmovies.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private static final String API_KEY ="c8d917f61c2a2dfa450cefb12c62bbe9";
    private static final String API_URL_BASE = "https://api.themoviedb.org/3/";

    private RecyclerView rvMovies;
    private List<Movie> movies;
    private MoviesAdapter moviesAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", (ArrayList<? extends Parcelable>) movies);
        super.onSaveInstanceState(outState);
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rvMovies = (RecyclerView) rootView.findViewById(R.id.rvMovies);

        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList("movies");
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MovieService movieService = retrofit.create(MovieService.class);

            Call<ListMovies> listMovies = movieService.listPopularMovies(API_KEY);

            listMovies.enqueue(new Callback<ListMovies>() {
                @Override
                public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                    movies = response.body().getMovies();
                    setUpRecyclerView(movies);
                }

                @Override
                public void onFailure(Call<ListMovies> call, Throwable t) {

                }
            });
        }

        return rootView;
    }

    private void setUpRecyclerView(List<Movie> movies) {
        moviesAdapter = new MoviesAdapter(getActivity(), movies);
        rvMovies.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvMovies.setLayoutManager(mLayoutManager);
        rvMovies.setAdapter(moviesAdapter);
    }

}
