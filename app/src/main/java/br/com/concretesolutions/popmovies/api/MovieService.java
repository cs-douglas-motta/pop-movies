package br.com.concretesolutions.popmovies.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by douglasmotta on 08/05/17.
 */

public interface MovieService {
    @GET("movie/popular?")
    Call<ListMovies> listPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated?")
    Call<ListMovies> listTopRatedMovies(@Query("api_key") String apiKey);
}
