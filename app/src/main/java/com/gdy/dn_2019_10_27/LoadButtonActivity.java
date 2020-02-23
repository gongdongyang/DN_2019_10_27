package com.gdy.dn_2019_10_27;


import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.widget.LoginButton;

import butterknife.BindView;

public class LoadButtonActivity extends YBaseActivity {


    @BindView(R.id.btn_login)
    LoginButton btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_load_button;
    }

    @Override
    protected void addEvent() {
        super.addEvent();
        btnLogin.setOnClickListener(v -> {
            btnLogin.click();
        });
    }
}
