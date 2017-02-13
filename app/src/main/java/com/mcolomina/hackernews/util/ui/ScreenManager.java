package com.mcolomina.hackernews.util.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class ScreenManager<T extends AppCompatActivity> {
    private T activity;
    private FragmentManager fragmentManager;

    @Inject
    public ScreenManager(T activity) {
        this.activity = activity;
        this.fragmentManager = activity.getFragmentManager();
    }

    public void placeFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void placeFragment(Fragment fragment, int containerId) {
        placeFragment(fragment, containerId, true);
    }

    public void startActivity(Class<? extends BaseActivity> targetActivity, boolean shouldCloseParent) {
        Intent intent = new Intent(activity, targetActivity);
        startActivity(intent, shouldCloseParent);
    }

    public void startActivity(Intent intent, boolean shouldCloseParent) {
        activity.startActivity(intent);
        if (shouldCloseParent) {
            activity.finish();
        }
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
