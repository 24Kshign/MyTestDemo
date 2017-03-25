package com.share.appbaseui;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.jack.mc.cyg.cygtools.util.CygString;
import com.share.appbaseui.base.BaseActivity;
import com.share.appbaseui.titlebar.TitleBarUIComponent;
import com.share.appbaseui.webview.MyWebChromeClient;
import com.share.appbaseui.webview.WebViewUIComponent;

/**
 *
 */

public class WebViewActivity extends BaseActivity {

    public static void start(Context context, String url, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        CygStartActivity.start(context, WebViewActivity.class, bundle, flags);
    }

    public static void start(Context context, String title, String html, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(HTML, html);
        CygStartActivity.start(context, WebViewActivity.class, bundle, flags);
    }

    public static void start(Context context, String url) {
        start(context, url, CygStartActivity.DEFAULT_FLAGS);
    }

    public static void start(Context context, String title, String html) {
        start(context, title, html, CygStartActivity.DEFAULT_FLAGS);
    }

    private static final String URL = "url";
    private static final String TITLE = "title";
    private static final String HTML = "html";

    private TitleBarUIComponent titleBarUIComponent;
    private WebViewUIComponent webViewUIComponent;
    private ProgressBar progressBar;
    private MyWebChromeClient myWebChromeClient;

    private String url;
    private String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setAutoHideSoftInput(true);

        Bundle extras = getIntent().getExtras();
        url = extras.getString(URL);
        html = extras.getString(HTML);
        String title = extras.getString(TITLE);
        initView();

        if (!CygString.isEmpty(title)) {
            titleBarUIComponent.setTitle(title);
        } else {
            titleBarUIComponent.setTitle("正在加载中...");
        }
    }

    private void initView() {
        titleBarUIComponent = (TitleBarUIComponent) findViewById(R.id.aw_titlebar);
        titleBarUIComponent.setBackgroundResource(R.color.colorPrimary);
        webViewUIComponent = WebViewUIComponent.build((WebView) findViewById(R.id.aw_webview));
        progressBar = (ProgressBar) findViewById(R.id.aw_progress_bar);

        myWebChromeClient = new MyWebChromeClient(this, progressBar);
        webViewUIComponent.setWebChromeClient(myWebChromeClient);

        loadData();
    }

    private void loadData() {
        if (!CygString.isEmpty(url)) {
            webViewUIComponent.loadUrl(url);
        } else if (!CygString.isEmpty(html)) {
            webViewUIComponent.loadData(html);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webViewUIComponent.canGoBack()) {
                webViewUIComponent.goBack();
                return true;
            }
        }
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
