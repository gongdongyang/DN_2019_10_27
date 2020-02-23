package com.gdy.dn_2019_10_27.widget;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by dongyanggong
 * on 2019/6/13
 */
public class JWebChromeClient extends WebChromeClient {

    private WebView mWebView;

    private final Context mContext;
    private JWebView.WebViewListener listener;

    public WebView getmWebView() {
        return mWebView;
    }

    public void setmWebView(WebView mWebView) {
        this.mWebView = mWebView;
    }


    public JWebChromeClient(JWebView.WebViewListener listener, Context context) {
        this.listener = listener;
        this.mContext = context;

    }
}
