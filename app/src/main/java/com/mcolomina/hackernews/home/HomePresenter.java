package com.mcolomina.hackernews.home;

import android.util.Log;

import com.mcolomina.hackernews.main.MainView;
import com.mcolomina.hackernews.net.HackerRestApi;
import com.mcolomina.hackernews.net.Story;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter {

    public static final int PAGE_LIMIT = 10;
    public static final int INITIAL_SKIP_VALUE = 0;

    Retrofit retrofit;
    final ArrayList<Story> topStories;
    ListCallback callback;
    HackerRestApi service;
    ArrayList<Integer> topStoriesIds;
    MainView mainView;


    @Inject
    public HomePresenter(Retrofit retrofit) {
        this.retrofit = retrofit;
        service = retrofit.create(HackerRestApi.class);
        topStories = new ArrayList<Story>();
        topStoriesIds = new ArrayList<Integer>();
    }

    public void setMainView(MainView view) {
        this.mainView = view;
    }

    public void load(final int skip) {
        service.getTopStoriesId()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Integer>>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("GithubDemo", e.getMessage());
                    }

                    @Override
                    public final void onNext(ArrayList<Integer> response) {
                        topStoriesIds.addAll(response);
                        List<Integer> list = new ArrayList<Integer>();
                        for (int i = skip; i < skip + PAGE_LIMIT; i++) {
                            list.add(topStoriesIds.get(i));
                        }
                        loadStories(list);

                    }
                });
    }

    public void loadStories(List<Integer> list) {
        final ArrayList<Story> stories = new ArrayList<Story>();
        for (int id : list) {
            service.getStoryById(id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Story>() {
                        @Override
                        public final void onCompleted() {

                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("MYERROR", e.getMessage());
                        }

                        @Override
                        public final void onNext(Story response) {
                            response.setTypeItem(HomeListAdapter.TYPE_NEWS);
                            stories.add(response);
                            if (stories.size() >= PAGE_LIMIT){
                                callback.onListLoaded(stories);
                                stories.clear();
                            }
                        }
                    });

        }

    }

    public void setCallback(ListCallback callback) {
        this.callback = callback;
    }

    public void displayWebView(String url){
        mainView.openWeb(url);
    }

    public interface ListCallback {
        void onListLoaded(List<Story> stories);

        void onFailedListLoading();
    }

}