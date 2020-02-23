package com.gdy.bitmax.market.api;

import com.gdy.bitmax.market.model.MarketAllEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public interface BtmxApiInterface {
    @GET("api/r/v1/market/all")
    Observable<MarketAllEntity> getMarketAllData();
}
