package com.gdy.network.base;

import android.app.Application;

/**
 * Created by gongdongyang
 * on 2019/10/1
 */
public class BaseApplication extends Application {
    private static  BaseApplication _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;

    }

    public static BaseApplication getInstance() {
        return _instance;
    }
}
