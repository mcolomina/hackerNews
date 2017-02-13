package com.mcolomina.hackernews.main;

import com.mcolomina.hackernews.util.di.BaseActivityModule;

import dagger.Module;

@Module
public class MainModule extends BaseActivityModule<MainActivity> {

    public MainModule(MainActivity activity) {
        super(activity);
    }
}
