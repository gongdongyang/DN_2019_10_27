package com.gdy.network.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.gdy.network.BaseActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public abstract class BasePresenter implements IPresenter {
    protected BaseActivity mBaseActivity;

    public BaseActivity getActivity() {
        return mBaseActivity;
    }


    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onStart(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onPuase(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }
}
