package com.apps.muhammadwaris.androidmvpherokuapp.ui.movies_listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.muhammadwaris.androidmvpherokuapp.R;
import com.apps.muhammadwaris.androidmvpherokuapp.common.adapter.BaseAdapter;
import com.apps.muhammadwaris.androidmvpherokuapp.data.FullScreenProgress;
import com.apps.muhammadwaris.androidmvpherokuapp.data.MoviesModel;
import com.apps.muhammadwaris.androidmvpherokuapp.data.NoInternet;
import com.apps.muhammadwaris.androidmvpherokuapp.data.NoItemFound;
import com.apps.muhammadwaris.androidmvpherokuapp.data.Progress;
import com.apps.muhammadwaris.androidmvpherokuapp.utils.AppConstants;

import java.util.List;

public class MoviesListingAdapter extends BaseAdapter {

    Context context;
    List list;

    public MoviesListingAdapter(List list, Context context) {
        super(list, context);
        this.context = context;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == AppConstants.ITEM_FULL_SCREEN_PROGRESS) {
            return new ProgressViewHolder(LayoutInflater.from(context).inflate(R.layout.row_full_screen_progress, parent, false));
        } else if (viewType == AppConstants.ITEM_NO_INTERNET) {
            return new NoConnectionViewHolder(LayoutInflater.from(context).inflate(R.layout.row_no_internet_connection, parent, false));
        } else if (viewType == AppConstants.ITEM_NOT_FOUND) {
            return new NoDataFoundViewHolder(LayoutInflater.from(context).inflate(R.layout.row_no_data_found, parent, false));
        } else {
            return new MoviesListingViewHolder(LayoutInflater.from(context).inflate(R.layout.row_movies_listing, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof MoviesListingViewHolder){
            MoviesModel movie = (MoviesModel) list.get(position);
            MoviesListingViewHolder moviesListingViewHolder = (MoviesListingViewHolder) holder;
            moviesListingViewHolder.title.setText(movie.getTitle());
            moviesListingViewHolder.description.setText(movie.getDescription());
            moviesListingViewHolder.director.setText(movie.getDirector());
            moviesListingViewHolder.producer.setText(movie.getProducer());
            moviesListingViewHolder.releaseDate.setText(movie.getReleaseDate());

        }

    }


    public class MoviesListingViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, director, producer, releaseDate;

        public MoviesListingViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            director = itemView.findViewById(R.id.tv_director);
            producer = itemView.findViewById(R.id.tv_producer);
            releaseDate = itemView.findViewById(R.id.tv_release_date);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof FullScreenProgress) {
            return AppConstants.ITEM_FULL_SCREEN_PROGRESS;
        } else if (list.get(position) instanceof Progress) {
            return AppConstants.ITEM_PROGRESS;
        } else if (list.get(position) instanceof NoItemFound) {
            return AppConstants.ITEM_NOT_FOUND;
        } else if (list.get(position) instanceof NoInternet) {
            return AppConstants.ITEM_NO_INTERNET;
        } else if (list.get(position) instanceof MoviesListingViewHolder) {
            return AppConstants.MOVIES_LIST;
        }

        return -1;
    }


}
