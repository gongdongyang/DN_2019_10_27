package com.gdy.bitmax.market.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gdy.base_recycleview.BaseRecycleViewAdapter;
import com.gdy.base_recycleview.BaseViewHolder;
import com.gdy.bitmax.R;
import com.gdy.bitmax.market.helper.MarketHelper;
import com.gdy.bitmax.market.model.MarketAllEntity;

import java.util.Collections;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public class MarketListAdapter extends BaseRecycleViewAdapter<MarketListAdapter.MarketListViewHolder, MarketAllEntity.MarketItemEntity> {



    public MarketListAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }



    @Override
    public void onBindViewHolder(@NonNull MarketListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MarketAllEntity.MarketItemEntity item = getDatas().get(position);
        holder.icon_fu.setText(item.ba+" ");
        holder.icon_zhu.setText("/"+item.qa);
        holder.icon_volume.setText(item.v);
        holder.icon_close.setText(item.c);
        MarketHelper.setTrendColor(item.o,item.c,holder.icon_trend);
        MarketHelper.setTrendText(item.o,item.c,holder.icon_trend);

    }

    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(getDatas(),fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    class MarketListViewHolder extends BaseViewHolder{
        public TextView icon_fu;
        public TextView icon_zhu;
        public TextView icon_volume;

        public TextView icon_price;
        public TextView icon_trend;
        public TextView icon_close;

        public MarketListViewHolder(@NonNull View itemView) {
            super(itemView);

            icon_fu = itemView.findViewById(R.id.icon_fu);
            icon_zhu = itemView.findViewById(R.id.icon_zhu);
            icon_volume = itemView.findViewById(R.id.icon_volume);

            icon_price = itemView.findViewById(R.id.icon_price);
            icon_trend = itemView.findViewById(R.id.icon_trend);
            icon_close = itemView.findViewById(R.id.icon_close);
        }

    }
}
