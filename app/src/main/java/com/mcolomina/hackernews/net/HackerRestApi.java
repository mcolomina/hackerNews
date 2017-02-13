package com.mcolomina.hackernews.net;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface HackerRestApi {

    @GET("/v0/item/{id}.json")
    Observable<Story> getStoryById(@Path("id") int id);

    @GET("/v0/topstories.json")
    Observable<ArrayList<Integer>> getTopStoriesId();


}
