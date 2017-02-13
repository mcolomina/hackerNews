package com.mcolomina.hackernews.splash;

import android.os.Bundle;

import com.mcolomina.hackernews.R;
import com.mcolomina.hackernews.main.MainActivity;
import com.mcolomina.hackernews.util.di.Injector;
import com.mcolomina.hackernews.util.ui.BaseActivity;
import com.mcolomina.hackernews.util.ui.ScreenManager;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    ScreenManager screenManager;
    @Inject
    SplashPresenter presenter;

    @Override
    public int getContentView() {
        return R.layout.splash_activity;
    }

    @Override
    public void injectDependencies() {
        Injector.get().initSplashDependencies(this);
    }

    @Override
    public void releaseDependencies() {
        Injector.get().releaseSplashComponent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        presenter.openHomeScreen();
    }

    @Override
    public void openMainView() {
        screenManager.startActivity(MainActivity.class, true);
    }

}
