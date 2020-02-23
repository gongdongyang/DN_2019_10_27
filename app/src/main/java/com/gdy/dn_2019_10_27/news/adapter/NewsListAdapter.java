package com.gdy.dn_2019_10_27.news.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.gdy.base_recycleview.BaseRecycleViewAdapter;
import com.gdy.base_recycleview.BaseViewHolder;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.dn_2019_10_27.news.model.NewsContentEntitiy;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.ToastUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by gongdongyang
 * on 2019/12/15
 */
public class NewsListAdapter extends BaseRecycleViewAdapter<NewsListAdapter.NewsViewHolder, NewsContentEntitiy> {

    public NewsListAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        NewsContentEntitiy entity = mDatas.get(position);

        holder.newsTitleTv.setText(entity.title);

        if(!TextUtils.isEmpty(entity.img)){
            Picasso.with(getContext()).load(entity.img).into(holder.newsIv);
            Glide.with(getContext()).load(entity.img).into(holder.newsIv);
        }
    }

    class NewsViewHolder extends BaseViewHolder{
        TextView newsTitleTv;
        ImageView newsIv;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitleTv = itemView.findViewById(R.id.tv_news_title);

            newsIv = itemView.findViewById(R.id.iv_news_img);
        }
    }
}
