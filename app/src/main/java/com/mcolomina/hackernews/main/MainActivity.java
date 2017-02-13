package com.mcolomina.hackernews.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mcolomina.hackernews.R;
import com.mcolomina.hackernews.home.HomeFragment;
import com.mcolomina.hackernews.util.di.Injector;
import com.mcolomina.hackernews.util.ui.BaseActivity;
import com.mcolomina.hackernews.util.ui.ScreenManager;
import com.mcolomina.hackernews.web.WebViewFragment;

import javax.inject.Inject;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ScreenManager screenManager;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void injectDependencies() {
        Injector.get().initMainDependencies(this);

    }

    @Override
    public void releaseDependencies() {
        Injector.get().releaseMainComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        screenManager.placeFragment(HomeFragment.newInstance(), R.id.content);
    }

    public void setupToolbar(int titleRes, boolean visibility) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(visibility);
        getSupportActionBar().setDisplayShowHomeEnabled(visibility);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(titleRes);

    }

    @Override
    public void openWeb(String url) {
        screenManager.placeFragment(WebViewFragment.newInstance(url), R.id.content);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = screenManager.getFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
