package com.mcolomina.hackernews.util.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mcolomina.hackernews.home.HomePresenter;

public class EndlessScrollListener extends RecyclerView.OnScrollListener {


    private boolean isLoading;

    private LinearLayoutManager linearLayoutManager;

    public Callback callback;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager, Callback callback) {
        this.linearLayoutManager = linearLayoutManager;
        this.callback = callback;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        if (!isLoading && (totalItemCount % HomePresenter.PAGE_LIMIT == 0)) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= HomePresenter.PAGE_LIMIT) {
                isLoading = true;
                callback.loadMore(totalItemCount);
            }
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public interface Callback {
        void loadMore(int skip);
    }
}

