package com.gdy.dn_2019_10_27.main.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseFragment;
import com.gdy.dn_2019_10_27.main.adapter.HomeAdapter;
import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.dn_2019_10_27.main.util.HomeUtil;
import com.gdy.dn_2019_10_27.main.util.OpenGlUtil;
import com.gdy.ytool.NavitateUtil;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenglFragment extends YBaseFragment {

    @BindView(R.id.rcv_widget)
    RecyclerView mRcvWidget;

    private HomeAdapter mHomeAdapter;

    private List<ItemEntity> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_opengl;
    }

    @Override
    protected void initView() {
        //初始化数据
        mList = OpenGlUtil.loadOpenglData();

        mHomeAdapter = new HomeAdapter(getYActivity(),R.layout.item_home);
        mHomeAdapter.addAll(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getYActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcvWidget.setLayoutManager(layoutManager);
        mRcvWidget.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemClickListener(position -> {
            ItemEntity itemEntity = mList.get(position);
            NavitateUtil.startActivity(getYActivity(), itemEntity.getClassName());
        });



    }

}
