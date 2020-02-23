package com.gdy.network.net;

import android.app.Application;

/**
 * Created by gongdongyang
 * on 2019/12/15
 */
public interface INetworkRequiredInfo {
    String getAppVersionName();
    String getAppVersionCode();
    boolean isDebug();
    Application getApplicationContext();
}
