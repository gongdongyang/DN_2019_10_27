package com.gdy.dn_2019_10_27.main.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.ytool.util.BitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleImageActivity extends YBaseActivity {

    @BindView(R.id.image1)
    AppCompatImageView image1;
    @BindView(R.id.image2)
    AppCompatImageView image2;
    @BindView(R.id.image3)
    AppCompatImageView image3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_image;
    }

    @Override
    protected void initView() {
        super.initView();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.timg);
        BitmapUtils.createCircleImageViewByShader(bitmap,image1);


        BitmapUtils.createCircleImageViewByClipPath(bitmap,image2);

        BitmapUtils.createCircleImageViewByXfermode(bitmap,image3);

    }
}
