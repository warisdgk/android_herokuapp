package com.apps.muhammadwaris.androidmvpherokuapp.ui.movies_listing;

import android.app.Activity;

import com.apps.muhammadwaris.androidmvpherokuapp.common.base.BaseActivityContract;
import com.apps.muhammadwaris.androidmvpherokuapp.common.base.BasePresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class MoviesPresenter extends BasePresenter implements BaseActivityContract.Presenter {

    private MoviesModel moviesModel;
    private MoviesActivityContract.View view;

    public MoviesPresenter() {
        moviesModel = new MoviesModel(this);
    }

    public void fetchMoviesListing(Activity activity) {
        moviesModel.fetchMoviesListing(activity);
    }

    @Override
    public void onNetworkResponse(boolean isSuccessful, String requestURL, String response) {
        super.onNetworkResponse(isSuccessful, requestURL, response);
        view = (MoviesActivityContract.View) getView();
        if (isSuccessful) {
            try {
                JSONArray moviesList = new JSONArray(response);
                List movies = new Gson().fromJson(moviesList.toString(),
                        new TypeToken<List<com.apps.muhammadwaris.androidmvpherokuapp.data.MoviesModel>>() {
                        }.getType());
                view.showMoviesListing(movies);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            view.showMoviesListing(null);
        }
    }
}
