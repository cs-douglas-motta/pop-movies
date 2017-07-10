package br.com.concretesolutions.popmovies.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private static final String API_URL_BASE = "https://api.themoviedb.org/3/";

    private static MovieService movieService = null;

    public static MovieService getRetrofitMovieService() {
        if (movieService == null) {
            movieService = new Retrofit.Builder()
                    .baseUrl(API_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieService.class);
        }

        return movieService;
    }
}
