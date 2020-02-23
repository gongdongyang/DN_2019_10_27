package com.gdy.dn_2019_10_27.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gdy.base_recycleview.BaseRecycleViewAdapter;
import com.gdy.base_recycleview.BaseViewHolder;
import com.gdy.dn_2019_10_27.Constant;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.news.model.NewsChannelEntitiy;

import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/12/17
 */
public class NewsChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 我的频道 标题部分
    public static final int TYPE_MY_CHANNEL_HEADER = 0;
    // 我的频道
    public static final int TYPE_MY = 1;
    // 其他频道 标题部分
    public static final int TYPE_OTHER_CHANNEL_HEADER = 2;
    // 其他频道
    public static final int TYPE_OTHER = 3;

    private List<NewsChannelEntitiy> mEnableItems;

    private List<NewsChannelEntitiy> mDisableItems;

    private LayoutInflater mInflater;

    private Context mContext;

    public NewsChannelAdapter(Context context, int layoutId,
                              List<NewsChannelEntitiy> mEnableItems, List<NewsChannelEntitiy> mDisableItems) {
        this.mContext = context;
        this.mEnableItems = mEnableItems;
        this.mDisableItems = mDisableItems;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType){
            case TYPE_MY_CHANNEL_HEADER:{
                itemView = mInflater.inflate(R.layout.item_channel_my_header,parent,false);
                ChannelHeaderViewHolder channelHeaderViewHolder = new ChannelHeaderViewHolder(itemView);
                return channelHeaderViewHolder;
            }

            case TYPE_MY:{
                itemView = mInflater.inflate(R.layout.item_channel_my, parent, false);
                EnableChannelViewHolder enableChannelViewHolder = new EnableChannelViewHolder(itemView);
                return enableChannelViewHolder;
            }
            case TYPE_OTHER_CHANNEL_HEADER:{
                itemView = mInflater.inflate(R.layout.item_channel_other_header,parent,false);
                OtherChannelHeaderViewHolder otherChannelHeaderViewHolder = new OtherChannelHeaderViewHolder(itemView);
                return otherChannelHeaderViewHolder;
            }
            case TYPE_OTHER:{
                 itemView = mInflater.inflate(R.layout.item_channel_other,parent,false);
                 OtherChannelViewHolder otherChannelViewHolder = new OtherChannelViewHolder(itemView);
                 return otherChannelViewHolder;
             }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    class ChannelHeaderViewHolder extends RecyclerView.ViewHolder{
        public ChannelHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class EnableChannelViewHolder extends RecyclerView.ViewHolder{
        public EnableChannelViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class OtherChannelHeaderViewHolder extends RecyclerView.ViewHolder{
        public OtherChannelHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class OtherChannelViewHolder extends RecyclerView.ViewHolder{
        public OtherChannelViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mDisableItems.size()+mEnableItems.size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_MY_CHANNEL_HEADER;
        }else if(position>0 && position<=mEnableItems.size()){
            return TYPE_MY;
        }else if(position==mEnableItems.size()+1){
            return TYPE_OTHER_CHANNEL_HEADER;
        }else {
            return TYPE_OTHER;
        }
    }
}
