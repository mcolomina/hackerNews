package com.mcolomina.hackernews.util.ui;

public interface InjectableView {

    int getContentView();

    void injectDependencies();

    void releaseDependencies();
}
