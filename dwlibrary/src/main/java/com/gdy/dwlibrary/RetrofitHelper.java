package com.gdy.dwlibrary;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gongdongyang
 * on 2019/12/18
 */
public class RetrofitHelper {

    private volatile static RetrofitHelper sInstance;//使线程共享一个实例
    private Retrofit mRetrofit;
    private RetrofitHelper() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.izis.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 返回接口服务实例
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T getApiService(Class<T> clz) {
        return mRetrofit.create(clz);
    }

}
