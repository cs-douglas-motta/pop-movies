package br.com.concretesolutions.popmovies.data.repository;

import android.text.TextUtils;
import android.util.Log;

import br.com.concretesolutions.popmovies.data.api.MovieResponse;
import br.com.concretesolutions.popmovies.data.api.MovieService;
import br.com.concretesolutions.popmovies.data.api.RetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiMovieRepository implements MovieRepository {
    static final String API_KEY = "c8d917f61c2a2dfa450cefb12c62bbe9";

    @Override
    public void getMovies(final String sort, final MoviesCallback callback) {
        final MovieService movieService = RetrofitApi.getRetrofitMovieService();

        Call<MovieResponse> movies;

        if (TextUtils.equals(sort, "top rated")) {
            movies = movieService.getTopRatedMovies(API_KEY);
        } else {
            movies = movieService.getPopularMovies(API_KEY);
        }

        movies.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    callback.onMoviesLoaded(response.body().getMovies());
                } else {
                    callback.onDataNotAvailable("Erro ao acessar API");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onDataNotAvailable("Falha ao acessar API");
            Log.e("Error", "Falha ao acessar API", t);
            }
        });
    }
}
