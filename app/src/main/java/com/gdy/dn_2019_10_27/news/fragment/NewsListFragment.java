package com.gdy.dn_2019_10_27.news.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdy.dn_2019_10_27.Constant;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.api.NewsApiInterface;
import com.gdy.dn_2019_10_27.api.NewsChannelsBean;
import com.gdy.dn_2019_10_27.news.activity.WebViewActivity;
import com.gdy.dn_2019_10_27.news.adapter.NewsListAdapter;
import com.gdy.dn_2019_10_27.news.model.NewsContentEntitiy;
import com.gdy.dn_2019_10_27.news.model.TxNewsChannelsEntity;
import com.gdy.network.base.LazyLoadFragment;
import com.gdy.network.net.TecentNetworkApi;
import com.gdy.network.observer.BaseObserver;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.NavitateUtil;
import com.gdy.ytool.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class NewsListFragment extends LazyLoadFragment {

    @BindView(R.id.news_rcv)
    RecyclerView mNewsRecycleView;

    NewsListAdapter mNewsListAdapter;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void fetchData() {

        BaseObserver<NewsChannelsBean> observer1 =  new BaseObserver<NewsChannelsBean>() {
            @Override
            public void onSuccess(NewsChannelsBean newsChannelsBean) {
            }

            @Override
            public void onFailure(Throwable e) {
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        };
        TecentNetworkApi.getService(NewsApiInterface.class).getNewsChannels()
                .compose(TecentNetworkApi.getInstance().applySchedulers(observer1));

        //YBaseObserver<NewsContentEntitiy> observer2 =
        BaseObserver<TxNewsChannelsEntity> observer2 = new BaseObserver<TxNewsChannelsEntity>() {
            @Override
            public void onSuccess(TxNewsChannelsEntity newsChannelsBean) {
                //mNewsListAdapter.notifyDataSetChanged();
                mNewsListAdapter.addAll(newsChannelsBean.showapiResBody.pageBean.contentlist);
                mNewsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable e) {
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        };
        TecentNetworkApi.getService(NewsApiInterface.class)
                .getNewsList("5572a108b3cdc86cf39001cd", "1")
                .compose(TecentNetworkApi.getInstance().applySchedulers(observer2));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initView() {
        mNewsListAdapter  = new NewsListAdapter(getYActivity(),R.layout.item_news_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getYActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNewsRecycleView.setLayoutManager(layoutManager);


        mNewsListAdapter.setOnItemClickListener(position -> {
            //ToastUtils.showToast("====position====="+position);
            List<NewsContentEntitiy> list = mNewsListAdapter.getDatas();
            NewsContentEntitiy newsItem = list.get(position);
            NavitateUtil.startActivity(getYActivity(), WebViewActivity.class, Constant.WEB_URL, newsItem.link);
        });

        mNewsRecycleView.setAdapter(mNewsListAdapter);
    }




}
