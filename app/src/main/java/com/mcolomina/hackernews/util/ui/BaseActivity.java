package com.mcolomina.hackernews.util.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements InjectableView {

    protected final static int MISSING_CONTENT_VIEW = 0;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentView() != MISSING_CONTENT_VIEW) {
            setContentView(getContentView());
        }
        unbinder = ButterKnife.bind(this);
        injectDependencies();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        releaseDependencies();
    }
}
