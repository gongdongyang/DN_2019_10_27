package com.gdy.dn_2019_10_27.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.stetho.common.LogUtil;

/**
 * Created by gongdongyang
 * on 2020/2/11
 */
public class MyBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        LogUtil.e("MyBroadCastReceiver","==onReceive==");

        Toast.makeText(context,"MyBroadCastReceiver",Toast.LENGTH_LONG).show();
    }


}
