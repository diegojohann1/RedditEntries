package com.example.reddit.redditentries.data.network.api;

import android.util.Log;
import com.example.reddit.redditentries.data.network.api.models.TopResponse;
import com.example.reddit.redditentries.data.network.api.models.TopResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public class ApiInteractor {
   private RedditApi mRedditApi;
   private static final int MAX_TOP = 50;
    private TopResponseData interactorTopResponseData;


    public ApiInteractor() {
        this.mRedditApi= ApiClient.getClient().create(RedditApi.class);
    }

    public void getTop(final ResponseCallback cllbk){
        this.mRedditApi.getTop(MAX_TOP).enqueue(new Callback<TopResponse>() {
            @Override
            public void onResponse(Call<TopResponse> call, Response<TopResponse> response) {
                Log.v("response",response.body().toString());
                if(cllbk!=null)
                    cllbk.onDataReady(response.body());
            }

            @Override
            public void onFailure(Call<TopResponse> call, Throwable t) {
                Log.e("onFailure","=>"+t.getMessage());
                if(cllbk!=null)
                    cllbk.onError(t);
            }
        });
    }

    public TopResponseData getInteractorTopResponseData() {
        if(interactorTopResponseData != null) {
            return interactorTopResponseData;
        }
        return null;
    }

    public interface ResponseCallback{
        void onDataReady(TopResponse response);
        void onError(Throwable t);
    }
}