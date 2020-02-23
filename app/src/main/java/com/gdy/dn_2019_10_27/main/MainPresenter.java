package com.gdy.dn_2019_10_27.main;

import android.app.Activity;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.main.fragment.HomeFragment;
import com.gdy.dn_2019_10_27.main.fragment.NewsFragment;
import com.gdy.dn_2019_10_27.main.fragment.OpenglFragment;
import com.gdy.network.BaseActivity;
import com.gdy.network.base.BasePresenter;
import com.gdy.ytool.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public class MainPresenter extends BasePresenter {

    public MainPresenter(BaseActivity activity) {
        mBaseActivity = activity;
    }


    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {
        super.onCreate(owner);
        LogUtils.d("MainPresenter","==onCreate==");
    }

    public void goMainFragment(){
        setCurrentFragment(HomeFragment.class, null);
    }

    public void goNewsragment(){
        setCurrentFragment(NewsFragment.class, null);
    }

    public void goOpenGl(){
        setCurrentFragment(OpenglFragment.class, null);
    }

    private void setCurrentFragment(Class fragmentClass, Bundle bundle) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment dstFragment = manager.findFragmentByTag(fragmentClass.getSimpleName());

        List<Fragment> fragments = manager.getFragments();

        FragmentTransaction transaction = manager.beginTransaction();

        if (fragments != null)
            for (Fragment frg : fragments)
                transaction.hide(frg);

        if (dstFragment == null) {
            dstFragment = Fragment.instantiate(getActivity(), fragmentClass.getName(), bundle);
            transaction.add(R.id.fl_content, dstFragment, fragmentClass.getSimpleName()).commitAllowingStateLoss();
        } else {
            transaction.show(dstFragment).commitAllowingStateLoss();
        }

    }


}
