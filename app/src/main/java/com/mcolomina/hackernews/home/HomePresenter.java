package com.mcolomina.hackernews.home;

import com.mcolomina.hackernews.main.MainView;
import com.mcolomina.hackernews.net.HackerRestApi;
import com.mcolomina.hackernews.net.StoriesLoader;
import com.mcolomina.hackernews.net.Story;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class HomePresenter implements StoriesLoader.LoaderCallback {

    public static final int PAGE_LIMIT = 10;
    public static final int INITIAL_SKIP_VALUE = 0;

    private StoriesLoader storiesLoader;
    private ListCallback callback;
    private MainView mainView;


    @Inject
    public HomePresenter(StoriesLoader storiesLoader) {
        this.storiesLoader = storiesLoader;
    }

    public void initPresenter() {
        storiesLoader.setCallback(this);
    }

    @Override
    public void updatelist(ArrayList<Story> stories) {
        callback.onListLoaded(stories);
    }

    public void load(final int skip) {
        storiesLoader.load(skip);
    }

    public void setMainView(MainView view) {
        this.mainView = view;
    }

    public void setCallback(ListCallback callback) {
        this.callback = callback;
    }

    public void displayWebView(String url) {
        mainView.openWeb(url);
    }


}