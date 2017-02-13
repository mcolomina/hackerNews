package com.mcolomina.hackernews.home;

import com.mcolomina.hackernews.app.AppComponent;
import com.mcolomina.hackernews.main.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,modules = HomeModule.class)
public interface HomeComponent {

    void inject(HomeFragment fragment);

}
