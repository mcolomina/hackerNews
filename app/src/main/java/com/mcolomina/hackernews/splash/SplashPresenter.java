package com.mcolomina.hackernews.splash;

public class SplashPresenter {

    private SplashView view;

    public void setView(SplashView view) {
        this.view = view;
    }

    public void openHomeScreen() {
        view.openMainView();
    }
}
