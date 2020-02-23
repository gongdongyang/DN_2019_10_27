package com.gdy.dn_2019_10_27.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;

public class YMapActivity extends YBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_ymap;
    }

    @Override
    protected void addEvent() {
        AnimatedVectorDrawable  animatedVectorDrawable = (AnimatedVectorDrawable)ContextCompat.getDrawable(this,R.drawable.refresh_vector);
        ((ImageView)findViewById(R.id.imageView)).setImageDrawable(animatedVectorDrawable);
        animatedVectorDrawable.start();
        final Handler mainHandler = new Handler(Looper.getMainLooper());
        /*animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                *//*mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        animatedVectorDrawable.start();
                    }
                });*//*
            }
        });*/
    }
}
