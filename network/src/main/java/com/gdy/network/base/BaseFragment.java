package com.gdy.network.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.gdy.network.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by gongdongyang
 * on 2019/10/1
 */
public abstract class BaseFragment extends Fragment {

    protected static final String TAG = BaseFragment.class.getSimpleName();

    protected View mRootView  ;

    private BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if(mRootView==null){
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this,mRootView);
            initView();
        }else{
            if(mRootView.getParent()!=null){
                ((ViewGroup) mRootView.getParent()).removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    public abstract int getLayoutId();

    protected abstract void initView();


    public BaseActivity getYActivity() {
        return mActivity;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }
}
