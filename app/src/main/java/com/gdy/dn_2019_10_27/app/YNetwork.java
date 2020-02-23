package com.gdy.dn_2019_10_27.app;

import android.app.Application;

import com.gdy.network.BuildConfig;
import com.gdy.network.net.INetworkRequiredInfo;

/**
 * Created by gongdongyang
 * on 2019/12/15
 */
public class YNetwork implements INetworkRequiredInfo {
    private Application mApplication;

    public YNetwork(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Override
    public String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String getAppVersionCode() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public Application getApplicationContext() {
        return mApplication;
    }
}
