package com.mcolomina.hackernews.home;

import com.mcolomina.hackernews.net.Story;

import java.util.List;

public interface ListCallback {
    void onListLoaded(List<Story> stories);
}
