package com.gdy.network;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gdy.network.base.BasePresenter;
import com.gdy.network.receiver.NetWorkChangReceiver;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar mToolBar;

    private Unbinder mUnbinder;

    public BasePresenter mPresenter;

    private NetWorkChangReceiver netWorkChangReceiver;

    private boolean isRegistered = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initPresenter();
        ButterKnife.bind(this);

        initReceiver();
        initToolbar();
        initView();
        addEvent();
    }

    private void initReceiver() {

        netWorkChangReceiver = new NetWorkChangReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
        isRegistered = true;
    }

    protected abstract void initPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void addEvent();

    private void initToolbar(){
        mToolBar = findViewById(R.id.toolbar);
        if(mToolBar!=null){
            mToolBar.setTitle("");
            setSupportActionBar(mToolBar);
            getSupportActionBar().setHomeButtonEnabled(isShowBacking());
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShowBacking());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }

        //解绑
        if (isRegistered) {
            unregisterReceiver(netWorkChangReceiver);
        }
    }

    protected boolean isShowBacking() {
        return true;
    }

    public BasePresenter getPresenter() {
        return mPresenter;
    }

}
