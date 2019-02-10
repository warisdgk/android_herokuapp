package com.apps.muhammadwaris.androidmvpherokuapp.common.base;

import android.app.Activity;

import okhttp3.ResponseBody;
import retrofit2.Call;



public interface BaseActivityContract {

    interface Presenter {
        void attachView(View view);

        void deAttachView();

        void onNetworkResponse(boolean isSuccessful, String requestURL, String response);

        void checkNetworkStatus(boolean status);
    }

    interface View {

        void setTitle(String title);

        void onValidationError(String message);

        void showProgressDialog();

        void hideProgressDialog();

        void showToast(String message);

        void checkNetworkStatus(boolean status);

        void onNetworkResponseReceived();
    }

    interface Model {

        void createNetworkRequest(Call<ResponseBody> request, Activity activity);

    }


}
