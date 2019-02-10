package com.apps.muhammadwaris.androidmvpherokuapp.common.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.apps.muhammadwaris.androidmvpherokuapp.R;


public class BaseActivity extends AppCompatActivity implements BaseActivityContract.View {

    private Dialog progressDialog = null;
    private BasePresenter presenter;

    public interface OnRetryConnectionResponse {
        void onRetryResponse();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void setTitle(String title) {
        TextView textView = findViewById(R.id.tv_title);
        textView.setText(title);
    }

    @Override
    public void onValidationError(String message) {
        showToast(message);
    }

    @Override
    public void showProgressDialog() {
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkNetworkStatus(boolean isNetworkConnected) {

    }

    @Override
    public void onNetworkResponseReceived() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void enableBackNavigation() {
        ImageView imageView = findViewById(R.id.iv_back);
        imageView.setOnClickListener(v -> finish());
    }

}
