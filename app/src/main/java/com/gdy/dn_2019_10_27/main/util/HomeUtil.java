package com.gdy.dn_2019_10_27.main.util;

import com.gdy.bitmax.market.HBitmaxActivity;
import com.gdy.dn_2019_10_27.LoadButtonActivity;
import com.gdy.dn_2019_10_27.main.activity.BehaviorActivity;
import com.gdy.dn_2019_10_27.main.activity.CircleImageActivity;
import com.gdy.dn_2019_10_27.main.activity.CustomViewActivity;
import com.gdy.dn_2019_10_27.main.activity.DataBindingActivity;
import com.gdy.dn_2019_10_27.main.activity.FlowLayoutActivity;
import com.gdy.dn_2019_10_27.main.activity.GuaguaKaActivity;
import com.gdy.dn_2019_10_27.main.activity.HFlutterActivity;
import com.gdy.dn_2019_10_27.main.activity.InflaterActivity;
import com.gdy.dn_2019_10_27.main.activity.KChartLineActivity;
import com.gdy.dn_2019_10_27.main.activity.LoadingView4Activity;
import com.gdy.dn_2019_10_27.main.activity.TreeActivity;
import com.gdy.dn_2019_10_27.main.activity.UpgradeActivity;
import com.gdy.dn_2019_10_27.main.activity.YBottomNavigation;
import com.gdy.dn_2019_10_27.main.activity.YMapActivity;
import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.dn_2019_10_27.md.MaterialActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public class HomeUtil {

    public static List<ItemEntity> loadHomeData(){
        List<ItemEntity> datas = new ArrayList<>();
        datas.add(new ItemEntity("静态广播", null,2));
        datas.add(new ItemEntity("自定义刮刮卡", GuaguaKaActivity.class));
        datas.add(new ItemEntity("自定义View", CustomViewActivity.class));
        datas.add(new ItemEntity("圆形Navigation", YBottomNavigation.class));
        datas.add(new ItemEntity("圆形图片", CircleImageActivity.class));
        datas.add(new ItemEntity("流式布局", FlowLayoutActivity.class));
        datas.add(new ItemEntity("Tree动画", TreeActivity.class));
        datas.add(new ItemEntity("购物车动画", null,2));
        datas.add(new ItemEntity("Behavior", BehaviorActivity.class));
        datas.add(new ItemEntity("地图", YMapActivity.class));
        datas.add(new ItemEntity("DataBing", DataBindingActivity.class));
        datas.add(new ItemEntity("后台下载", UpgradeActivity.class));
        datas.add(new ItemEntity("LoadButton", LoadButtonActivity.class));
        datas.add(new ItemEntity("K线图", KChartLineActivity.class));
        datas.add(new ItemEntity("材料设计", MaterialActivity.class));
        datas.add(new ItemEntity("B-Home", HBitmaxActivity.class));
        datas.add(new ItemEntity("Flutter", HFlutterActivity.class));
        datas.add(new ItemEntity("PopupWindow的创建", null,2));
        datas.add(new ItemEntity("LayoutInflater.inflate方法", InflaterActivity.class));
        datas.add(new ItemEntity("LoadingView4", LoadingView4Activity.class));
        return datas;
    }

}
