package com.mcolomina.hackernews.net;

import android.util.Log;

import com.mcolomina.hackernews.home.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.mcolomina.hackernews.home.HomePresenter.INITIAL_SKIP_VALUE;
import static com.mcolomina.hackernews.home.HomePresenter.PAGE_LIMIT;

public class StoriesLoader {

    private List<Story> stories;
    private List<Integer> storiesIds;
    private LoaderCallback callback;
    private HackerRestApi service;
    private Retrofit retrofit;

    @Inject
    public StoriesLoader(Retrofit retrofit) {
        service = retrofit.create(HackerRestApi.class);
        this.retrofit = retrofit;
        storiesIds = new ArrayList<Integer>();
    }

    public void setCallback(LoaderCallback callback) {
        this.callback = callback;
    }

    public void load(final int skip) {
        if (skip == INITIAL_SKIP_VALUE) {
            loadIds(skip);
        } else {
            List<Integer> list = new ArrayList<Integer>();
            for (int i = skip; i < skip + PAGE_LIMIT; i++) {
                list.add(storiesIds.get(i));
            }
            loadStories(list);
        }
    }

    public void loadIds(final int skip) {
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
                        storiesIds.addAll(response);
                        List<Integer> list = new ArrayList<Integer>();
                        for (int i = skip; i < skip + PAGE_LIMIT; i++) {
                            list.add(storiesIds.get(i));
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
                            if (stories.size() >= PAGE_LIMIT) {
                                callback.updatelist(stories);
                                stories.clear();
                            }
                        }
                    });

        }
    }

    public List<Integer> getStoriesIds() {
        return storiesIds;
    }

    public interface LoaderCallback {
        void updatelist(ArrayList<Story> stories);
    }
}
