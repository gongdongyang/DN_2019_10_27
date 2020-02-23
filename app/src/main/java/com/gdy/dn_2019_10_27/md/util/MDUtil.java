package com.gdy.dn_2019_10_27.md.util;

import com.gdy.dn_2019_10_27.LoadButtonActivity;
import com.gdy.dn_2019_10_27.main.activity.BehaviorActivity;
import com.gdy.dn_2019_10_27.main.activity.CircleImageActivity;
import com.gdy.dn_2019_10_27.main.activity.CustomViewActivity;
import com.gdy.dn_2019_10_27.main.activity.DataBindingActivity;
import com.gdy.dn_2019_10_27.main.activity.FlowLayoutActivity;
import com.gdy.dn_2019_10_27.main.activity.GuaguaKaActivity;
import com.gdy.dn_2019_10_27.main.activity.KChartLineActivity;
import com.gdy.dn_2019_10_27.main.activity.UpgradeActivity;
import com.gdy.dn_2019_10_27.main.activity.YMapActivity;
import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.dn_2019_10_27.md.MaterialActivity;
import com.gdy.dn_2019_10_27.md.activity.CollapsingToolActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/12/21
 */
public class MDUtil {

    public static List<ItemEntity> loadMDData(){
        List<ItemEntity> datas = new ArrayList<>();
        datas.add(new ItemEntity("折叠标题", CollapsingToolActivity.class));
        return datas;
    }
}
