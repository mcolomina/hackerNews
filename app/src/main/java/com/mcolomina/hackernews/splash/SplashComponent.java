package com.mcolomina.hackernews.splash;

import com.mcolomina.hackernews.main.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = SplashModule.class)
public interface SplashComponent {

    void inject(SplashActivity activity);
}
