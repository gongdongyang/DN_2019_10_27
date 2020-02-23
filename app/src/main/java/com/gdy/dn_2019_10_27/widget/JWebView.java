package com.gdy.dn_2019_10_27.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gdy.dn_2019_10_27.app.YApplication;

/**
 * Created by gongdongyang
 * on 2019/12/16
 */
public class JWebView extends WebView {

    public JWebView(Context context) {
        super(context);
        init(context);
    }

    public JWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public JWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public JWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        WebSettings webSettings = getSettings();
        if (webSettings == null)
            return;

        /**
         * 从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式。
         * 在Android5.0以下，默认是采用的MIXED_CONTENT_ALWAYS_ALLOW模式，即总是允许WebView同时加载Https和Http；而从Android5.0开始，默认用MIXED_CONTENT_NEVER_ALLOW模式，即总是不允许WebView同时加载Https和Http。
         * 虽然官网给出的建议是，为了安全考虑，使用MIXED_CONTENT_NEVER_ALLOW模式，但是在实际引用中，当我们的服务器已经升级到Https，
         * 但是一些页面的资源是第三方的，我们不能要求第三方也都升级到Https，所以我们只能根据系统版本，
         * 用代码去设置加载模式为MIXED_CONTENT_ALWAYS_ALLOW。
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//或者MIXED_CONTENT_COMPATIBILITY_MODE
        }

        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);
        setWebViewClient(new JWebViewClient(wrapper));

        JWebChromeClient jWebChromeClient = new JWebChromeClient(wrapper,context);
        jWebChromeClient.setmWebView(this);
        setWebChromeClient(jWebChromeClient);

        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //user-agent`
        webSettings.setUserAgentString(webSettings.getUserAgentString());

        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = YApplication.getInstance().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true);
    }


    private WebViewListener listener;

    public interface WebViewListener {
        void onPageStarted(WebView view, String url, Bitmap favicon);

        void onPageFinished(WebView view, String url);

        void onProgressChanged(WebView view, int newProgress);

        boolean shouldOverrideUrlLoading(WebView view, String url);
    }

    public WebViewListener wrapper = new WebViewListener() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (listener != null)
                listener.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (listener != null)
                listener.onPageFinished(view, url);

        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (listener != null)
                listener.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            boolean isShould = true;
            if (listener != null)
                isShould = listener.shouldOverrideUrlLoading(view,url);
            return isShould;
        }
    };
}
