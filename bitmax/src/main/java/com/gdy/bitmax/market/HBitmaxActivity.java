package com.gdy.bitmax.market;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdy.bitmax.R;
import com.gdy.bitmax.R2;
import com.gdy.bitmax.TBaseActivity;
import com.gdy.bitmax.callback.IHCallback;
import com.gdy.bitmax.market.adapter.MarketListAdapter;
import com.gdy.bitmax.market.adapter.decoration.ThemeLineItemDecoration;
import com.gdy.bitmax.market.model.MarketAllEntity;
import com.gdy.bitmax.market.viewmodel.MarketListViewModel;

import java.util.List;

import butterknife.BindView;

public class HBitmaxActivity extends TBaseActivity {


    @BindView(R2.id.rec_market_list)
    RecyclerView mMarketListRecycleView;

    MarketListAdapter marketListAdapter;

    MarketListViewModel marketListViewModel;

    private ItemTouchHelper itemTouchHelper;
    private IHCallback itemCallBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bitmax_h;
    }


    @Override
    protected void initView() {
        super.initView();
        //mMarketListRecycleView = findViewById(R.id.rec_market_list);
        marketListAdapter = new MarketListAdapter(this, R.layout.item_market_data);
        mMarketListRecycleView.setAdapter(marketListAdapter);
        mMarketListRecycleView.addItemDecoration(new ThemeLineItemDecoration(getResources().getColor(R.color.line_color)));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMarketListRecycleView.setLayoutManager(layoutManager);

        //
        itemCallBack = new IHCallback(marketListAdapter);
        itemTouchHelper = new ItemTouchHelper(itemCallBack);
        itemTouchHelper.attachToRecyclerView(mMarketListRecycleView);
    }

    @Override
    protected void addEvent() {
        super.addEvent();

        marketListViewModel = ViewModelProviders.of(this).get(MarketListViewModel.class);

        marketListViewModel.marketDatas.observe(this, new Observer<List<MarketAllEntity.MarketItemEntity>>() {
            @Override
            public void onChanged(List<MarketAllEntity.MarketItemEntity> marketItemEntities) {
                marketListAdapter.addAll(marketItemEntities);
                marketListAdapter.notifyDataSetChanged();
            }
        });


        marketListViewModel.loadMarketAllData();

    }


}
