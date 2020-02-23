package com.gdy.dn_2019_10_27.main.click;

import android.content.Context;
import android.view.View;

import com.gdy.dn_2019_10_27.databind.activity.DataBindingListActivity;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.NavitateUtil;
import com.gdy.ytool.ToastUtils;

/**
 * Created by gongdongyang
 * on 2019/12/17
 */
public class MainClickHandler {
    private Context context;

    public MainClickHandler(Context context) {
        this.context = context;
    }

    public void onClickView(View view){
        LogUtils.d("======绑定事件成功====");
        NavitateUtil.startActivity(context, DataBindingListActivity.class);
        ToastUtils.showToast("绑定事件成功");
    };
}
