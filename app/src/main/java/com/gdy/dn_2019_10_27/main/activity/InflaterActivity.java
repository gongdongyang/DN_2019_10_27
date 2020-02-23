package com.gdy.dn_2019_10_27.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;

public class InflaterActivity extends YBaseActivity {

    private LinearLayout linearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inflater;
    }

    @Override
    protected void addEvent() {
        super.addEvent();

        linearLayout = findViewById(R.id.ll);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_popup_window,null,false);

        linearLayout.addView(view);
        /*LayoutInflater inflater = LayoutInflater.from(this);

        inflater.inflate(R.layout.linearlayout, ll,true);*/


    }
}
