package com.gdy.dn_2019_10_27.md;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.dn_2019_10_27.main.adapter.HomeAdapter;
import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.dn_2019_10_27.md.util.MDUtil;
import com.gdy.ytool.NavitateUtil;

import java.util.List;

import butterknife.BindView;

public class MaterialActivity extends YBaseActivity {

    @BindView(R.id.rcv_widget)
    RecyclerView mRcvWidget;

    private HomeAdapter mHomeAdapter;

    private List<ItemEntity> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_material;
    }


    @Override
    protected void initView() {
        //初始化数据
        mList = MDUtil.loadMDData();

        mHomeAdapter = new HomeAdapter(this,R.layout.item_home);
        mHomeAdapter.addAll(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcvWidget.setLayoutManager(layoutManager);
        mRcvWidget.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemClickListener(position -> {
            ItemEntity itemEntity = mList.get(position);
            //ToastUtils.showToast(itemEntity.getAction().getName());
            NavitateUtil.startActivity(this, itemEntity.getClassName());
        });



    }
}
