package com.gdy.network.observer;

import com.gdy.network.base.BaseHttpResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public abstract class YBaseObserver<T> implements Observer<BaseHttpResult<T>> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseHttpResult<T> tBaseHttpResult) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
    public abstract void onFailure(Throwable e);
}
