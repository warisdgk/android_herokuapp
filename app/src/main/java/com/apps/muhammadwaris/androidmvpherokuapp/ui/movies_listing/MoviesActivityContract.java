package com.apps.muhammadwaris.androidmvpherokuapp.ui.movies_listing;

import android.app.Activity;

import com.apps.muhammadwaris.androidmvpherokuapp.common.base.BaseActivityContract;

import java.util.List;

public interface MoviesActivityContract {

    interface Presenter extends BaseActivityContract.Presenter {

    }

    interface View extends BaseActivityContract.View {
        void showMoviesListing(List moviesListing);
    }

    interface Model extends BaseActivityContract.Model {
        void fetchMoviesListing(Activity activity);
    }

}
