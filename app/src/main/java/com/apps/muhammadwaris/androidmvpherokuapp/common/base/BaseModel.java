package com.apps.muhammadwaris.androidmvpherokuapp.common.base;

import android.app.Activity;

import com.apps.muhammadwaris.androidmvpherokuapp.utils.AppUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaseModel implements BaseActivityContract.Model {

    private BaseActivityContract.Presenter presenter;

    public BaseModel(BaseActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void createNetworkRequest(Call<ResponseBody> request, Activity activity) {

        if (!AppUtils.isInternetAvailable(activity)) {
            presenter.checkNetworkStatus(false);
            return;
        }

        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String requestURL = response.raw().request().url().toString();
                        presenter.onNetworkResponse(true, requestURL, response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    presenter.onNetworkResponse(false, "", "");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.onNetworkResponse(false, "", "");
            }
        });
    }
}
