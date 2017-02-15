package com.mcolomina.hackernews.home;

import com.mcolomina.hackernews.main.ActivityScope;
import com.mcolomina.hackernews.net.StoriesLoader;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HomeModule {

    @ActivityScope
    @Provides
    public HomePresenter provideHomePresenter(StoriesLoader storiesLoader) {
        return new HomePresenter(storiesLoader);
    }
}
