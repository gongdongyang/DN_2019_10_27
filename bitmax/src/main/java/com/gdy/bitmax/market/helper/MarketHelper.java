package com.gdy.bitmax.market.helper;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.gdy.bitmax.R;
import com.gdy.bitmax.market.utils.NumberUtil;
import com.gdy.network.base.BaseApplication;


/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public class MarketHelper {

    /**
     *
     * @param open
     * @param current
     * @param textView
     */
    public static void setTrendColor(String open, String current, TextView textView){
        Drawable greenDrawable = BaseApplication.getInstance().getDrawable(R.drawable.app_bg_btn_green);
        Drawable grayDrawable = BaseApplication.getInstance().getDrawable(R.drawable.app_bg_btn_gray);
        Drawable redDrawable = BaseApplication.getInstance().getDrawable(R.drawable.app_bg_btn_red);
        float f_open = Float.valueOf(open);
        float f_current = Float.valueOf(current);
        //跌
        if(f_open>f_current){
            textView.setBackgroundDrawable(redDrawable);
        }else if(f_open ==f_current){
            textView.setBackgroundDrawable(grayDrawable);
        }else{
            textView.setBackgroundDrawable(greenDrawable);
        }
    }

    /**
     *
     * @param textView
     */
    public static void setTrendText(String open,String current,TextView textView){
        double f_open = Double.parseDouble(open);
        double f_current = Double.parseDouble(current);
        double rate = NumberUtil.changeRate(f_current,f_open);
        //rate = rate*100;
        if(rate>0){
            textView.setText("+"+rate+"%");
        }else{
            textView.setText(rate+"%");
        }

    }
}
