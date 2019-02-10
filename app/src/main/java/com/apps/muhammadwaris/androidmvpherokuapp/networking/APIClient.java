package com.apps.muhammadwaris.androidmvpherokuapp.networking;
import com.apps.muhammadwaris.androidmvpherokuapp.BuildConfig;
import com.apps.muhammadwaris.androidmvpherokuapp.utils.AppConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {


    private static APIClient instance;
    private APIService APIService;

    private APIClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit;

        if (BuildConfig.SHOW_LOGS) {
            retrofit = new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        } else {
            retrofit = new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        APIService = retrofit.create(APIService.class);
    }

    public static APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    public APIService getAPIService() {
        return APIService;
    }

}