package com.mcolomina.hackernews.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mcolomina.hackernews.R;
import com.mcolomina.hackernews.main.MainActivity;
import com.mcolomina.hackernews.main.MainView;
import com.mcolomina.hackernews.net.Story;
import com.mcolomina.hackernews.util.di.Injector;
import com.mcolomina.hackernews.util.ui.BaseFragment;
import com.mcolomina.hackernews.util.ui.EndlessScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.Retrofit;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, EndlessScrollListener.Callback, HomePresenter.ListCallback, StoryHolder.Callback {

    @BindView(R.id.list)
    RecyclerView homeRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout refreshLayout;

    @Inject
    HomePresenter homePresenter;
    @Inject
    Retrofit retrofit;

    private ArrayList<Story> storiesList;
    private HomeListAdapter adapter;
    private EndlessScrollListener scrollListener;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void injectDependencies() {
        Injector.get().initHomeDependencies(this);
    }

    @Override
    public void releaseDependencies() {
        Injector.get().releaseHomeComponent();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storiesList = new ArrayList<Story>();
        homePresenter.setCallback(this);
        homePresenter.setMainView((MainView) getActivity());

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        homeRecyclerView.setLayoutManager(linearLayoutManager);
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        homeRecyclerView.addItemDecoration(new DividerItemDecoration(homeRecyclerView.getContext(), linearLayoutManager.getOrientation()));
        scrollListener = new EndlessScrollListener(linearLayoutManager, this);
        homeRecyclerView.addOnScrollListener(scrollListener);
        adapter = new HomeListAdapter(getActivity(), storiesList, this);
        homeRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter.getItems().isEmpty() && !scrollListener.isLoading()) {
            loadMore(HomePresenter.INITIAL_SKIP_VALUE);
        }
        ((MainActivity) getActivity()).setupToolbar(R.string.app_name, false);
    }


    @Override
    public void onRefresh() {
        if (!scrollListener.isLoading()) {
            refreshLayout.setEnabled(false);
            adapter.clear();
            loadMore(HomePresenter.INITIAL_SKIP_VALUE);
        }
    }

    protected void stopRefreshing() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void loadMore(int skip) {
        adapter.addLoadingItem();
        homePresenter.load(skip);
        refreshLayout.setEnabled(true);
    }

    @Override
    public void onListLoaded(List<Story> stories) {
        scrollListener.setLoaded();
        List<Story> shownStories = new ArrayList<>();
        if (isVisible()) {
            if (!stories.isEmpty()) {
                for (int i = 0; i < stories.size(); i++) {
                    shownStories.add(new Story(HomeListAdapter.TYPE_NEWS, stories.get(i)));
                }
                adapter.updateList(shownStories, refreshLayout.isRefreshing());
               // storiesList.addAll(shownStories);
            } else if (adapter.getItems().size() <= 1 && stories.isEmpty()) {
                adapter.removeLoadingItem();
            } else if (stories.isEmpty()) {
                adapter.removeLoadingItem();
            }
            stopRefreshing();
        }
    }

    @Override
    public void onFailedListLoading() {
        adapter.removeLoadingItem();
    }

    @Override
    public void listItemClicked(String url) {
        homePresenter.displayWebView(url);
    }


}


