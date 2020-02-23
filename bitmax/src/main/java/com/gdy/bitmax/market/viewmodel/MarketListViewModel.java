package com.gdy.bitmax.market.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.gdy.bitmax.market.api.BtmxApiInterface;
import com.gdy.bitmax.market.api.BtmxNetworkApi;
import com.gdy.bitmax.market.model.MarketAllEntity;
import com.gdy.network.RxSchedulersHelper;
import com.gdy.network.observer.BaseObserver;

import java.util.List;


/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public class MarketListViewModel extends AutoDisposViewModel {

    public MutableLiveData<List<MarketAllEntity.MarketItemEntity>> marketDatas= new MutableLiveData();


    /**
     *
     */
    public void loadMarketAllData(){
        BaseObserver<MarketAllEntity> observer = new BaseObserver<MarketAllEntity>() {
            @Override
            public void onSuccess(MarketAllEntity marketAllEntity) {
                marketDatas.postValue(marketAllEntity.data);
            }

            @Override
            public void onFailure(Throwable e) {
                //Toast.makeText(HBitmaxActivity.this, ((ExceptionHandle.ResponeThrowable) e).message, Toast.LENGTH_SHORT).show();
            }
        };

        BtmxNetworkApi.getService(BtmxApiInterface.class)
                .getMarketAllData()
                .compose(RxSchedulersHelper.<MarketAllEntity>io_main())
                .compose(BtmxNetworkApi.getInstance().<MarketAllEntity>applySchedulersMap())
                .compose(BtmxNetworkApi.getInstance().<MarketAllEntity>applySchedulersError())
                .subscribe(observer);
    }
}
