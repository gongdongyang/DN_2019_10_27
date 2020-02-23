package com.gdy.dn_2019_10_27.api;

import com.gdy.dn_2019_10_27.news.model.TxNewsChannelsEntity;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by gongdongyang
 * on 2019/12/15
 */
public interface NewsApiInterface {

    @GET("release/channel")
    @Headers("host: service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com")
    Observable<NewsChannelsBean> getNewsChannels();

    @GET("release/news")
    @Headers("host: service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com")
    Observable<TxNewsChannelsEntity> getNewsList(@Query("channelId") String channelId,
                                                 @Query("page") String page );

}
