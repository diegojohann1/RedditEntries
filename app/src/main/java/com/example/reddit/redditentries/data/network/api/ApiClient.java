package com.example.reddit.redditentries.data.network.api;


import com.example.reddit.redditentries.BuildConfig;
import com.example.reddit.redditentries.data.network.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diego Volantino on 18/3/18.
 */


public class ApiClient {


    private static  OkHttpClient getOKClient() {
        OkHttpClient client;
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.followRedirects(true);

        if (BuildConfig.DEBUG) {
            // Debug stethto only debug
            clientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }
        client = clientBuilder.build();
        return client;
    }

    public static Retrofit getClient() {
        Retrofit retrofitJSON = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .client(getOKClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        return retrofitJSON;
    }



}