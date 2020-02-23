package com.gdy.dn_2019_10_27.main.plugin;

import android.app.Activity;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * Created by gongdongyang
 * on 2019/12/27
 */
public class MyFlutterPlugin implements MethodChannel.MethodCallHandler {

    private final Activity activity;

    public MyFlutterPlugin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

    }
}
