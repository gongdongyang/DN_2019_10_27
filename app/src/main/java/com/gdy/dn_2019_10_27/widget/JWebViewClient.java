package com.gdy.dn_2019_10_27.widget;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dongyanggong
 * on 2019/6/13
 */
public class JWebViewClient extends WebViewClient {

    private JWebView.WebViewListener listener;
    public JWebViewClient(JWebView.WebViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (listener != null)
            listener.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (listener != null)
            listener.onPageFinished(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        boolean isShould = listener.shouldOverrideUrlLoading(view,url);
        if (isShould){
            return true;
        }else{
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handleSslError(view, handler, error);
    }

    private void handleSslError(WebView view, SslErrorHandler handler, SslError error) {

    }



}
