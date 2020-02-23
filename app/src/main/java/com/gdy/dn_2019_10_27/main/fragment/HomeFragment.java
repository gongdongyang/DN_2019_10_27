package com.gdy.dn_2019_10_27.main.fragment;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseFragment;
import com.gdy.dn_2019_10_27.main.MainActivity;
import com.gdy.dn_2019_10_27.main.adapter.HomeAdapter;
import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.dn_2019_10_27.main.util.HomeUtil;
import com.gdy.dn_2019_10_27.receiver.MyBroadCastReceiver;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.NavitateUtil;
import com.gdy.ytool.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends YBaseFragment {

    @BindView(R.id.rcv_widget)
    RecyclerView mRcvWidget;

    private HomeAdapter mHomeAdapter;

    private List<ItemEntity> mList;

    private LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        //初始化数据
        mList = HomeUtil.loadHomeData();

        mHomeAdapter = new HomeAdapter(getYActivity(),R.layout.item_home);
        mHomeAdapter.addAll(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getYActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcvWidget.setLayoutManager(layoutManager);
        mRcvWidget.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemClickListener(position -> {
            View itemView = layoutManager.findViewByPosition(position);
            TextView tv = itemView.findViewById(R.id.tv_name);
            LogUtils.d("HomeFragment","tv.name:"+tv.getText());
            ItemEntity itemEntity = mList.get(position);
            if(itemEntity.getClassName()!=null){
                NavitateUtil.startActivity(getYActivity(), itemEntity.getClassName());
            }else{
                if(itemEntity.getTitle().equals("PopupWindow的创建")){
                    ToastUtils.showToast("====");
                    createPopupWindow();
                    return ;
                }

                if(itemEntity.getTitle().equals("购物车动画")){
                    shopAnimator(itemView);
                    return;
                }

                if(itemEntity.getTitle().equals("静态广播")){
                    /*Intent intent = new Intent();
                    intent.setAction("com.my.sentborad");
                    //intent.addFlags(Intent.FLAG_RECEIVER_EXCLUDE_BACKGROUND);
                    intent.setPackage("com.gdy.dn_2019_10_27");
                    getYActivity().sendBroadcast(intent);*/

                    //localBroadcastManager.sendBroadcast(new Intent("com.demo.action"));

                    /*Intent intent = new Intent(Intent.ACTION_BOOT_COMPLETED);
                    intent.setPackage("com.gdy.dn_2019_10_27");
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    getYActivity().sendBroadcast(intent);*/
                }
            }
        });



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.demo.action");


        mBroadcastReceiver = new MyBroadCastReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(getYActivity());
        localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void createPopupWindow() {
        View popuWindowView = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_popup_window,null);
        PopupWindow popupWindow = new PopupWindow(this.getContext());
        popupWindow.setContentView(popuWindowView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(popuWindowView, Gravity.BOTTOM,0,0);
    }

    public void shopAnimator(View view){
        int[] viewCoordinate = new int[2];
        view.getLocationInWindow(viewCoordinate);
        LogUtils.d("HomeFragment","x.location:"+viewCoordinate[0]+":y.location:"+viewCoordinate[1]);

        int[] tabCoordinate = new int[2];
        ((MainActivity)getYActivity()).indexView_1.getLocationInWindow(viewCoordinate);
        LogUtils.d("HomeFragment","indexView_1--x.location:"+viewCoordinate[0]+":y.location:"+viewCoordinate[1]);
    }


    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }
}
