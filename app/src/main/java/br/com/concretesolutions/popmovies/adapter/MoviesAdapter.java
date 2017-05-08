package br.com.concretesolutions.popmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.model.Movie;

/**
 * Created by douglasmotta on 08/05/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private static final String URL_BASE_POSTER_MOVIE = "https://image.tmdb.org/t/p/w500";

    private Context context;
    private List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Picasso.with(context).load(URL_BASE_POSTER_MOVIE + movies.get(position).getPosterPath()).into(holder.ivPosterMovie);
    }

    @Override
    public int getItemCount() {
        if (movies != null && movies.size() > 0) {
            return movies.size();
        }

        return 0;
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPosterMovie;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            ivPosterMovie = (ImageView) itemView.findViewById(R.id.ivPosterMovie);
        }
    }
}
