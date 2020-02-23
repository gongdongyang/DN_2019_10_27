package com.gdy.dn_2019_10_27.main.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.ytool.LogUtils;

public class GuaguaKaActivity extends YBaseActivity {

    private static final String TAG = GuaguaKaActivity.class.getSimpleName();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_guagua_ka;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("TestActivtiy",TAG+"==onCreate==");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d("TestActivtiy",TAG+"==onRestart==");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("TestActivtiy",TAG+"==onStart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("TestActivtiy",TAG+"==onResume==");
    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("TestActivtiy",TAG+"==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("TestActivtiy",TAG+"==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("TestActivtiy",TAG+"==onDestroy==");
    }

}
