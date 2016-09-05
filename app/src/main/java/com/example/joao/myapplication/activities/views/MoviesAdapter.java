package com.example.joao.myapplication.activities.views;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.joao.myapplication.R;
import com.example.joao.myapplication.domain.Movie;
import com.example.joao.myapplication.persistence.repositories.MovieRepositorySearchResult;
import com.example.joao.myapplication.persistence.repositories.MovieRepositorySingleton;
import com.example.joao.myapplication.persistence.repositories.Repository;

/**
 * Created by joao on 12/08/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>{

    private Repository<Movie> movieRepository;
    private ImageLoader mImageLoader;
    private Context mContext;

    public void setMovieRepository(Repository<Movie> movieRepository) {
        if( movieRepository != null) {
            this.movieRepository = movieRepository;
        }
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        public TextView title, year;
        public ImageView overflow;
        private NetworkImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.year);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }

    }

    public MoviesAdapter(Context mContext, Repository<Movie> movieRepository, ImageLoader mImageLoader) {
        super();
        this.mContext = mContext;
        this.movieRepository = movieRepository;
        this.mImageLoader = mImageLoader;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_line, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder holder, final int position) {
        Movie movie = (Movie) movieRepository.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());


        holder.thumbnail.setDefaultImageResId(R.drawable.clacket);
        holder.thumbnail.setErrorImageResId(R.drawable.clacket);

        try {
            holder.thumbnail.setImageUrl(movie.getPoster(), this.mImageLoader);
        } catch (Exception e) {
            //do nothing
        }

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieRepository.getItemCount();
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view,final int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_movie_remove, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_remove:
                        Toast.makeText(mContext, "Removed", Toast.LENGTH_SHORT).show();
                        Movie m = movieRepository.get(position);
                        movieRepository.remove(m);
                        MoviesAdapter.this.notifyDataSetChanged();
                        return true;
                    default:
                }
                return false;
            }

        });
        popup.show();
    }


}
