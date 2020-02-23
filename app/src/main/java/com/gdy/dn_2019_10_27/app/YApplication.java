package com.gdy.dn_2019_10_27.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.gdy.dn_2019_10_27.BuildConfig;
import com.gdy.network.base.BaseApplication;
import com.gdy.network.net.NetworkApi;
import com.gdy.ytool.ToastUtils;
import com.tencent.mmkv.MMKV;

/**
 * Created by gongdongyang
 * on 2019/10/1
 */
public class YApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //ARouter.init(BaseApplication.getInstance());

        Stetho.initializeWithDefaults(BaseApplication.getInstance());

        Stetho.initialize(
                Stetho.newInitializerBuilder(BaseApplication.getInstance())
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(BaseApplication.getInstance()))
                        .build());

        ToastUtils.init(BaseApplication.getInstance());

        NetworkApi.init(new YNetwork(BaseApplication.getInstance()));

        MMKV.initialize(this);
        arouterInit();
    }

    private void arouterInit(){
        if(BuildConfig.DEBUG){
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(YApplication.getInstance());
    }

}
