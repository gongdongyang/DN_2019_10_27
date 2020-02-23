package com.gdy.dn_2019_10_27.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gdy.ytool.LogUtils;

/**
 * Created by gongdongyang
 * on 2020/2/11
 */
public class ShutdownBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "ShutdownBroadcastReceiver";

    private static final String ACTION_SHUTDOWN  = "android.intent.action.ACTION_SHUTDOWN";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_SHUTDOWN)) {
            LogUtils.i(TAG, "ShutdownBroadcastReceiver onReceive(), Do thing!");
        }

    }
}
