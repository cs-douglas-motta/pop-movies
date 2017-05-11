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

    private Context context;
    private List<Movie> movies;
    private ItemClickListener listener;

    public interface ItemClickListener {
        void onItemClick(Movie movie);
    }

    public MoviesAdapter(Context context, List<Movie> movies, ItemClickListener listener) {
        this.context = context;
        this.movies = movies;
        this.listener = listener;
    }

    public void clear() {
        if (this.movies != null) {
            this.movies.clear();
        }
    }

    public void addList(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.setMovie(movies.get(position));
        Picasso.with(context).load(Movie.URL_BASE_POSTER_MOVIE + movies.get(position).getPosterPath()).into(holder.ivPosterMovie);
    }

    @Override
    public int getItemCount() {
        if (movies != null && movies.size() > 0) {
            return movies.size();
        }

        return 0;
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        Movie movie;
        ImageView ivPosterMovie;


        public MoviesViewHolder(View itemView) {
            super(itemView);
            ivPosterMovie = (ImageView) itemView.findViewById(R.id.ivPosterMovie);
            itemView.setOnClickListener(this);
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(movie);
            }
        }
    }
}
