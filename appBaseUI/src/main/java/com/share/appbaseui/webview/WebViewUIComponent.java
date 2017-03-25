package com.share.appbaseui.webview;

import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class WebViewUIComponent extends BaseWebViewComponent {

    private List<String> historyUrlList = new ArrayList<>();

    public static WebViewUIComponent build(WebView view) {
        return build(WebViewUIComponent.class, view);
    }

    @Override
    protected void executeHttpProtocol(String url) {

    }

    @Override
    protected void onPageFinished(String url) {
        super.onPageFinished(url);
        historyUrlList.add(url);
    }

    public List<String> getHistoryUrlList() {
        return historyUrlList;
    }
}