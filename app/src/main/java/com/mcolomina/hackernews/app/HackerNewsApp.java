package com.mcolomina.hackernews.app;

import android.app.Application;

import com.mcolomina.hackernews.util.di.Injector;

public class HackerNewsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.get().initAppDependencies(this);
    }
}
