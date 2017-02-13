package com.mcolomina.hackernews.main;

import com.mcolomina.hackernews.app.AppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}

