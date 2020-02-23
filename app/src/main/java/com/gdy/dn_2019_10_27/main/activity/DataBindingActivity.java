package com.gdy.dn_2019_10_27.main.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.databinding.ActivityDataBindBinding;
import com.gdy.dn_2019_10_27.main.click.MainClickHandler;
import com.gdy.dn_2019_10_27.main.model.UserEntitiy;

public class DataBindingActivity extends AppCompatActivity{

    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_data_bind);
        ActivityDataBindBinding dataBingMain = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);

        UserEntitiy userEntitiy = new UserEntitiy("龚东阳","M",30);
        userEntitiy.setHeader("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576579548328&di=c019e8393cfccfa745783fe993dcafc7&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2019-11-13%2F5dcb758c7ede9.jpg");
        dataBingMain.setUser(userEntitiy);

        MainClickHandler mainHandler = new MainClickHandler(this);
        dataBingMain.setMainHandler(mainHandler);
        /*handler.postDelayed(()->{
            userEntitiy.setName("Kenvi");
        },5000);*/

    }
}
