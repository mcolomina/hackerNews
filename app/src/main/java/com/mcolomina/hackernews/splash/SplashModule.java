package com.mcolomina.hackernews.splash;

import com.mcolomina.hackernews.main.ActivityScope;
import com.mcolomina.hackernews.util.di.BaseActivityModule;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule extends BaseActivityModule<SplashActivity> {

    public SplashModule(SplashActivity activity) {
        super(activity);
    }

    @Provides
    @ActivityScope
    public SplashPresenter provideSplashPresenter() {
        return new SplashPresenter();
    }
}
