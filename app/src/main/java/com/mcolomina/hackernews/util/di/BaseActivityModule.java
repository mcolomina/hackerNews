package com.mcolomina.hackernews.util.di;

import android.support.v7.app.AppCompatActivity;

import com.mcolomina.hackernews.main.ActivityScope;
import com.mcolomina.hackernews.util.ui.ScreenManager;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule<T extends AppCompatActivity> {
    private T activity;

    public BaseActivityModule(T activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public ScreenManager provideScreenManager(){
        return new ScreenManager(activity);
    }

}
