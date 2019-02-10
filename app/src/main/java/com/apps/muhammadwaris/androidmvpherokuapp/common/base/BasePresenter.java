package com.apps.muhammadwaris.androidmvpherokuapp.common.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.apps.muhammadwaris.androidmvpherokuapp.R;
import com.apps.muhammadwaris.androidmvpherokuapp.R;



public class BasePresenter implements BaseActivityContract.Presenter {

    private BaseActivityContract.View view;
    private BaseModel model;

    public BasePresenter() {
        model = new BaseModel(this);
    }

    public void setActivityTitle(String title) {
        view.setTitle(title);
    }

    public Activity getActivity() {
        return ((Activity) view);
    }

    public Context getContext() {
        return (Context) view;
    }

    public void setGradientStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            Drawable background = getActivity().getResources().getDrawable(R.drawable.toolbar_gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getActivity().getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(getActivity().getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }


    @Override
    public void attachView(BaseActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void deAttachView() {
        this.view = null;
    }

    @Override
    public void onNetworkResponse(boolean isSuccessful, String requestURL, String response) {
    }

    @Override
    public void checkNetworkStatus(boolean status) {
        view.checkNetworkStatus(status);
    }

    public BaseActivityContract.View getView() {
        return view;
    }
}
