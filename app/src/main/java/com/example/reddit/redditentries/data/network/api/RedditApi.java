package com.example.reddit.redditentries.data.network.api;

import com.example.reddit.redditentries.data.network.api.models.TopResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public interface RedditApi {

    @GET("/top/.json")
    Call<TopResponse> getTop(@Query("limit") int mount) ;
}
