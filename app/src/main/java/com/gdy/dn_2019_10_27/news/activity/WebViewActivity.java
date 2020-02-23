package com.gdy.dn_2019_10_27.news.activity;

import com.gdy.dn_2019_10_27.Constant;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.dn_2019_10_27.widget.JWebView;
import com.gdy.ytool.LogUtils;

import butterknife.BindView;

public class WebViewActivity extends YBaseActivity {
    @BindView(R.id.info_webview)
    JWebView mInfoWebview;

    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        super.initView();
        url = getIntent().getStringExtra(Constant.WEB_URL);
        LogUtils.d("WebViewActivity","url=="+url);
    }

    @Override
    protected void addEvent() {
        super.addEvent();
        mInfoWebview.loadUrl("https://news.163.com/19/1216/22/F0I4OBKR0001899O.html");
    }
}
