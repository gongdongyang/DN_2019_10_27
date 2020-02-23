package com.gdy.network.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by gongdongyang
 * on 2019/12/15
 */
public class CommonResponseInterceptor implements Interceptor {

    private static final String TAG = "ResponseInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        Log.d(TAG, "requestTime="+ (System.currentTimeMillis() - requestTime));
        return response;
    }
}
