package com.gdy.dn_2019_10_27.news.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdy.dn_2019_10_27.Constant;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.dn_2019_10_27.news.adapter.NewsChannelAdapter;
import com.gdy.dn_2019_10_27.news.dao.NewsChannelDao;
import com.gdy.dn_2019_10_27.news.model.NewsChannelEntitiy;
import com.gdy.ytool.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsChannelActivity extends YBaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mChannelRecycleView;

    private NewsChannelDao dao = new NewsChannelDao();

    private NewsChannelAdapter mNewsChannelAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_channel;
    }

    @Override
    protected boolean isShowBacking() {
        return super.isShowBacking();
    }

    @Override
    protected void initView() {
        super.initView();
        mToolBar.setTitle("拖拽排序");
    }

    @Override
    protected void addEvent() {
        super.addEvent();
        mToolBar.setNavigationOnClickListener(v -> finish());

        //获取可用频道
        List<NewsChannelEntitiy> enableItems = dao.query(Constant.NEWS_CHANNEL_ENABLE);

        //获取不可用频道
        List<NewsChannelEntitiy> disableItems = dao.query(Constant.NEWS_CHANNEL_DISABLE);

        LogUtils.d("NewsChannelActivity",enableItems.size()+"size:"+disableItems.size());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);

        mNewsChannelAdapter = new NewsChannelAdapter(this,-1,enableItems,disableItems);


        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mNewsChannelAdapter.getItemViewType(position);
                return viewType == NewsChannelAdapter.TYPE_MY || viewType == NewsChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });

        mChannelRecycleView.setLayoutManager(layoutManager);
        mChannelRecycleView.setAdapter(mNewsChannelAdapter);

    }


}
