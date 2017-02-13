package com.mcolomina.hackernews.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mcolomina.hackernews.R;
import com.mcolomina.hackernews.main.MainActivity;
import com.mcolomina.hackernews.util.ui.BaseFragment;

import butterknife.BindView;

public class WebViewFragment extends BaseFragment {

    private static final String ARG_URL = "url";

    @BindView(R.id.webView)
    WebView webView;

    private String url;

    @Override
    public int getContentView() {
        return R.layout.fragment_webview;
    }

    @Override
    public void injectDependencies() {

    }

    @Override
    public void releaseDependencies() {

    }

    public static WebViewFragment newInstance(String urlRes) {
        Bundle args = new Bundle();
        args.putString(ARG_URL, urlRes);
        WebViewFragment fragment = new WebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(ARG_URL);
        ((MainActivity) getActivity()).setupToolbar(R.string.empty, true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
}
