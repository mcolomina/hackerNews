package com.mcolomina.hackernews.home;

import com.mcolomina.hackernews.main.ActivityScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HomeModule {

    @ActivityScope
    @Provides
    public HomePresenter provideHomePresenter(Retrofit retrofit) {
        return new HomePresenter(retrofit);
    }
}
