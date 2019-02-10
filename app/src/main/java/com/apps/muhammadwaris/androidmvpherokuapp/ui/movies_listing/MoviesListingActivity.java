package com.apps.muhammadwaris.androidmvpherokuapp.ui.movies_listing;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apps.muhammadwaris.androidmvpherokuapp.R;
import com.apps.muhammadwaris.androidmvpherokuapp.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MoviesListingActivity extends BaseActivity implements MoviesActivityContract.View {

    MoviesPresenter presenter;
    MoviesListingAdapter adapter;
    RecyclerView moviesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_listing);
        setTitle("Movies Listing");
        bindPresenter();
        initRecyclerView();
        presenter.fetchMoviesListing(MoviesListingActivity.this);
        adapter.addFullScreenProgress();
    }

    private void initRecyclerView() {
        adapter = new MoviesListingAdapter(new ArrayList(),this);
        moviesRecyclerView = findViewById(R.id.rv_movies_listing);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setAdapter(adapter);
    }

    private void bindPresenter() {
        presenter = new MoviesPresenter();
        presenter.attachView(this);
    }

    @Override
    public void checkNetworkStatus(boolean isNetworkConnected) {
        super.checkNetworkStatus(isNetworkConnected);
        if (!isNetworkConnected) {
            adapter.addNoConnectionItem();
        }
    }

    @Override
    public void showMoviesListing(List moviesListing) {
        adapter.removeProgress();
        if (moviesListing != null) {
            moviesRecyclerView.setAdapter(new MoviesListingAdapter(moviesListing, this));
        } else {
            adapter.addNoResultItem(R.drawable.cloud, "No movies found");
        }
    }
}
