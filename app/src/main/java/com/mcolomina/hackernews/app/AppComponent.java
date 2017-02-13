package com.mcolomina.hackernews.app;

import android.content.Context;

import com.mcolomina.hackernews.net.NetModule;

import dagger.Component;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    Context context();

    Retrofit retrofit();

}
