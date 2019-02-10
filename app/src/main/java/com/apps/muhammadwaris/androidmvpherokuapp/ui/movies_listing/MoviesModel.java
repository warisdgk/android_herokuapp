package com.apps.muhammadwaris.androidmvpherokuapp.ui.movies_listing;

import android.app.Activity;

import com.apps.muhammadwaris.androidmvpherokuapp.common.base.BaseActivityContract;
import com.apps.muhammadwaris.androidmvpherokuapp.common.base.BaseModel;
import com.apps.muhammadwaris.androidmvpherokuapp.networking.APIClient;

import retrofit2.Call;


public class MoviesModel extends BaseModel implements MoviesActivityContract.Model {

    public MoviesModel(BaseActivityContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void fetchMoviesListing(Activity activity) {
        Call call = APIClient.getInstance().getAPIService().getMovies();
        createNetworkRequest(call,activity);
    }
}
