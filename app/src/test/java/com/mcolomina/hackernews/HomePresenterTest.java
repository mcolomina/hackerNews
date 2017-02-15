package com.mcolomina.hackernews;

import com.mcolomina.hackernews.home.HomePresenter;
import com.mcolomina.hackernews.home.ListCallback;
import com.mcolomina.hackernews.main.MainView;
import com.mcolomina.hackernews.net.HackerRestApi;
import com.mcolomina.hackernews.net.StoriesLoader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {
    public static final int INITIAL_SKIP_VALUE = 0;

    private StoriesLoader storiesLoader;
    private HomePresenter homePresenter;


    @Before
    public void setUp(){
        storiesLoader = mock(StoriesLoader.class);
        homePresenter = new HomePresenter(storiesLoader);
    }

    @Test
    public void loadBehavoir(){
        homePresenter.load(INITIAL_SKIP_VALUE);
        verify(storiesLoader).load(anyInt());
    }

}
