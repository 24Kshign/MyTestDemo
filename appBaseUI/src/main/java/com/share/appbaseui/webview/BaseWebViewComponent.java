package com.share.appbaseui.webview;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jack.mc.cyg.cygtools.charset.CygCharset;
import com.jack.mc.cyg.cygtools.webview.CygWebViewUIComponent;

/**
 *
 */

public abstract class BaseWebViewComponent extends CygWebViewUIComponent {

    protected abstract void executeHttpProtocol(String url);


    @Override
    protected void onCreate() {
        super.onCreate();
        WebView webView = getWebView();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);     //让WebView支持JavaScript
//        webSettings.setDomStorageEnabled(true);     //启用H5 DOM API （默认false）
//        webSettings.setDatabaseEnabled(true);           //启用数据库api（默认false）可结合 setDatabasePath 设置路径
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);     //设置缓存模式,一共有四种模式
        webSettings.setAppCacheEnabled(true);       //启用应用缓存（默认false）可结合 setAppCachePath 设置缓存路径
//        webSettings.setAppCacheMaxSize(1);        //已过时，高版本API上，系统会自行分配
//        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);         //提高渲染的优先级
        webSettings.setUseWideViewPort(true);       //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);      // 缩放至屏幕的大小
        webSettings.setSupportZoom(true);       //支持缩放，默认为true
//        webSettings.setBuiltInZoomControls(true);       //设置内置的缩放控件（若SupportZoom为false，该设置项无效）
//        webSettings.setDisplayZoomControls(false);      //隐藏原生的缩放控件
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);      //支持内容重新布局
//        webSettings.supportMultipleWindows();       //支持多窗口
//        webSettings.setAllowFileAccess(true);       //设置可以访问文件
//        webSettings.setNeedInitialFocus(true);      //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);         //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);          //自动加载图片
        webSettings.setDefaultTextEncodingName(CygCharset.DEFAULT_CHARSET);        //设置编码格式

        webView.setWebViewClient(new WebViewClient() {
            /**
             * 捕获Url
             * 多页面在统一WebView中打开
             * @param view
             * @param urlString
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlString) {
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                BaseWebViewComponent.this.onReceivedError(errorCode, description, failingUrl);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                BaseWebViewComponent.this.onPageFinished(url);
            }
        });
    }

    private String networkErrorHtmlFilePath;

    public final void setNetworkErrorHtmlFilePath(String htmlFilePath) {
        this.networkErrorHtmlFilePath = htmlFilePath;
    }

    protected void onReceivedError(int errorCode, String description, String failingUrl) {
        loadUrl(networkErrorHtmlFilePath);
    }

    protected void onPageFinished(String url) {

    }

}