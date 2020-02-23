package com.gdy.dn_2019_10_27.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gongdongyang
 * on 2019/12/23
 */
public class StatusBarView  extends View {

    private Context context;

    public StatusBarView(Context context) { this(context, null); }

    public StatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(getStatusBarHeight(this.context), heightMeasureSpec));
    }


}
