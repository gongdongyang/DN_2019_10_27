package com.gdy.bitmax.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.gdy.bitmax.market.adapter.MarketListAdapter;

/**
 * Created by gongdongyang
 * on 2019/12/31
 */
public class IHCallback extends ItemTouchHelper.Callback {

    MarketListAdapter marketListAdapter;

    public IHCallback(MarketListAdapter marketListAdapter) {
        this.marketListAdapter = marketListAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        if(viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }

        /*if(onItemDragListener != null){
            onItemDragListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }*/
        marketListAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


    }

}
