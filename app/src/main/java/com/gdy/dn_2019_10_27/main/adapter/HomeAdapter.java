package com.gdy.dn_2019_10_27.main.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.gdy.base_recycleview.BaseRecycleViewAdapter;
import com.gdy.base_recycleview.BaseViewHolder;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.ytool.ToastUtils;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public class HomeAdapter extends BaseRecycleViewAdapter<HomeAdapter.HomeViewHolder, ItemEntity> {

    public HomeAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }


    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ItemEntity entity = mDatas.get(position);
        holder.tv_name.setText(entity.getTitle());
    }

    public static class HomeViewHolder extends BaseViewHolder {
        public AppCompatTextView tv_name;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }

    }
}
