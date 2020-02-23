package com.gdy.dn_2019_10_27.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gdy.dn_2019_10_27.Constant;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseFragment;
import com.gdy.dn_2019_10_27.news.activity.NewsChannelActivity;
import com.gdy.dn_2019_10_27.news.adapter.BasePagerAdapter;
import com.gdy.dn_2019_10_27.news.dao.NewsChannelDao;
import com.gdy.dn_2019_10_27.news.fragment.NewsListFragment;
import com.gdy.dn_2019_10_27.news.model.NewsChannelEntitiy;
import com.gdy.network.base.BaseFragment;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.NavitateUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends YBaseFragment {

    private ViewPager mViewPager;

    private BasePagerAdapter pagerAdapter;

    private NewsChannelDao dao = new NewsChannelDao();

    private Map<String, BaseFragment> map = new HashMap<>();

    private List<BaseFragment> fragmentList = new ArrayList<>();

    private List<String> titleList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        super.initView();

        TabLayout tab_layout = mRootView.findViewById(R.id.tab_layout_news);
        mViewPager = mRootView.findViewById(R.id.view_pager_news);

        tab_layout.setupWithViewPager(mViewPager);

        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);

        ImageView add_channel_iv = mRootView.findViewById(R.id.add_channel_iv);

        add_channel_iv.setOnClickListener(v -> NavitateUtil.startActivity(getActivity(),NewsChannelActivity.class));


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        initData();
    }

    private void initData(){

        List<NewsChannelEntitiy> channelList = dao.query(1);

        if (channelList.size() == 0) {
            dao.addInitData();
            channelList = dao.query(Constant.NEWS_CHANNEL_ENABLE);
        }


        for (NewsChannelEntitiy entitiy : channelList) {
            String channelId = entitiy.getChannelId();
            if (map.containsKey(channelId)) {
                fragmentList.add(map.get(channelId));
            }else{
                fragmentList.add(NewsListFragment.newInstance());
            }

            titleList.add(entitiy.getChannelName());
        }


        pagerAdapter = new BasePagerAdapter(getChildFragmentManager(), fragmentList, titleList);

        mViewPager.setAdapter(pagerAdapter);
    }

}