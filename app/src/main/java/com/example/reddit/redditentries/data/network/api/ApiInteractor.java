package com.example.reddit.redditentries.data.network.api;

import android.util.Log;

import com.example.reddit.redditentries.data.network.api.models.TopResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public class ApiInteractor {
   private RedditApi mRedditApi;
   private static final int MAX_TOP = 50;

    public ApiInteractor() {
        this.mRedditApi= ApiClient.getClient().create(RedditApi.class);
    }


    public void getTop(){
        this.mRedditApi.getTop(MAX_TOP).enqueue(new Callback<TopResponse>() {
            @Override
            public void onResponse(Call<TopResponse> call, Response<TopResponse> response) {
                Log.v("response",response.body().toString());
            }

            @Override
            public void onFailure(Call<TopResponse> call, Throwable t) {
                Log.e("onFailure","=>"+t.getMessage());
            }
        });
    }
}