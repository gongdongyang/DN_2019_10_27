package com.gdy.dn_2019_10_27.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.dn_2019_10_27.download.UpgradeDailog;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.ToastUtils;

public class UpgradeActivity extends YBaseActivity {

    private UpgradeDailog upgradeDailog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upgrade;
    }

    @Override
    protected void addEvent() {
        //super.addEvent();
        //弹出升级对话框
        showDailog();
    }

    /**
     *
     */

    private void showDailog(){
        upgradeDailog = new UpgradeDailog();
        upgradeDailog.show(getSupportFragmentManager(),UpgradeDailog.class.getSimpleName());
        LogUtils.d("UpgradeActivity","==showDailog==");

    }


}
