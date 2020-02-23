package com.gdy.dn_2019_10_27.main.util;

import com.gdy.dn_2019_10_27.main.model.ItemEntity;
import com.gdy.opengl.OpenGlActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public class OpenGlUtil {

    public static List<ItemEntity> loadOpenglData(){
        List<ItemEntity> datas = new ArrayList<>();
        datas.add(new ItemEntity("相机GLSurfaceView", OpenGlActivity.class));

        return datas;
    }

}
