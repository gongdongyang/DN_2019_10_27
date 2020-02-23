package com.gdy.dn_2019_10_27.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.gdy.ytool.LogUtils

/**
 *Created by gongdongyang
 *on 2020/2/11
 */
class BOOTReceiver :BroadcastReceiver() {

    val TAG = "BOOTReceiver";

    companion object{
        val device_start_action = "android.intent.action.BOOT_COMPLETED"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        LogUtils.d(TAG,"==onReceive==")

        if(intent?.action.equals(device_start_action)){
            Toast.makeText(context,"==onReceive==",Toast.LENGTH_LONG).show()
        }
    }
}