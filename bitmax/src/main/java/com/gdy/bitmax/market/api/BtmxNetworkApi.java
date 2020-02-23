package com.gdy.bitmax.market.api;

import com.gdy.bitmax.base.BtmxBaseResponse;
import com.gdy.network.dns.OkHttpDns;
import com.gdy.network.errorhandler.ExceptionHandle;
import com.gdy.network.net.NetworkApi;
import com.gdy.network.utils.TecentUtil;

import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public class BtmxNetworkApi extends NetworkApi {

    private static volatile BtmxNetworkApi sInstance;

    public static BtmxNetworkApi getInstance(){
        if(sInstance==null){
            synchronized (BtmxNetworkApi.class){
                if(sInstance==null){
                    sInstance = new BtmxNetworkApi();
                }
            }
        }
        return sInstance;
    }

    public static  <T> T getService(Class<T> service) {
        return getInstance().getRetrofit(service).create(service);
    }

    @Override
    protected Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String timeStr = TecentUtil.getTimeStr();
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Source", "source");
                builder.addHeader("Authorization", TecentUtil.getAuthorization(timeStr));
                builder.addHeader("Date", timeStr);
                return chain.proceed(builder.build());
            }
        };
    }


    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //response中code码不会0 出现错误
                if (response instanceof BtmxBaseResponse && ((BtmxBaseResponse) response).code != 0) {
                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                    exception.code = ((BtmxBaseResponse) response).code;
                    exception.message = ((BtmxBaseResponse) response).message != null ? ((BtmxBaseResponse) response).message : "";
                    throw exception;
                }
                return response;
            }
        };
    }

    @Override
    protected Dns getDNS() {
        return new OkHttpDns();
    }

    @Override
    public String getFormal() {
        return "https://btmxapp.co/";
    }

    @Override
    public String getTest() {
        return "https://bitmax-sandbox.io/";
    }
}
