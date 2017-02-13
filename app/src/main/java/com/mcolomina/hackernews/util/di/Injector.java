package com.mcolomina.hackernews.util.di;

import android.app.Application;

import com.mcolomina.hackernews.app.AppComponent;
import com.mcolomina.hackernews.app.AppModule;
import com.mcolomina.hackernews.app.DaggerAppComponent;
import com.mcolomina.hackernews.home.DaggerHomeComponent;
import com.mcolomina.hackernews.home.HomeComponent;
import com.mcolomina.hackernews.home.HomeFragment;
import com.mcolomina.hackernews.home.HomeModule;
import com.mcolomina.hackernews.main.DaggerMainComponent;
import com.mcolomina.hackernews.main.MainActivity;
import com.mcolomina.hackernews.main.MainComponent;
import com.mcolomina.hackernews.main.MainModule;
import com.mcolomina.hackernews.net.NetModule;
import com.mcolomina.hackernews.splash.DaggerSplashComponent;
import com.mcolomina.hackernews.splash.SplashActivity;
import com.mcolomina.hackernews.splash.SplashComponent;
import com.mcolomina.hackernews.splash.SplashModule;

public class Injector {
    private static Injector INSTANCE;

    private AppComponent appComponent;
    private SplashComponent splashComponent;
    private MainComponent mainComponent;
    private HomeComponent homeComponent;


    public static Injector get() {
        if (INSTANCE == null) {
            synchronized (Injector.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Injector();
                }
            }
        }
        return INSTANCE;
    }

    public void initAppDependencies(Application application) {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(application))
                    .netModule(new NetModule())
                    .build();
        }
    }

    public void initSplashDependencies(SplashActivity activity) {
        if (splashComponent == null) {
            splashComponent = DaggerSplashComponent.builder()
                    .splashModule(new SplashModule(activity))
                    .build();
            splashComponent.inject(activity);
        }
    }

    public void initMainDependencies(MainActivity activity) {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder()
                    .appComponent(appComponent)
                    .mainModule(new MainModule(activity))
                    .build();
            mainComponent.inject(activity);
        }
    }

    public void initHomeDependencies(HomeFragment fragment) {
        if (homeComponent == null) {
            homeComponent = DaggerHomeComponent.builder()
                    .appComponent(appComponent)
                    .homeModule(new HomeModule())
                    .build();
            homeComponent.inject(fragment);
        }
    }


    public void releaseSplashComponent() {
        splashComponent = null;
    }

    public void releaseMainComponent() {
        mainComponent = null;
    }

    public void releaseHomeComponent() {
        homeComponent = null;
    }

}
