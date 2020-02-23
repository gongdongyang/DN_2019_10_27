package com.gdy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.gdy.ytool.LogUtils;

/**
 * Created by gongdongyang
 * on 2019/10/31
 */
public class YFlowLayout extends ViewGroup {

    boolean flag = false;

    private final static String TAG  =  YFlowLayout.class.getName();

    public YFlowLayout(Context context) {
        super(context);
    }

    public YFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //MeasureSpec.AT_MOST：大小不可超过某数值，如：wrap_content
    //MeasureSpec.EXACTLY：确切的大小，如：100dp或者march_parent
    //MeasureSpec.UNSPECIFIED：不对View大小做限制，如：ListView，ScrollView
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(flag){
            return;
        }
        flag = true;
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量宽高尺寸
        //获取父布局宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //获取父布局高度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //宽高的测量模式
        //获取父布局宽度模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取父布局高度模式
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);

        LogUtils.d(TAG,"widthSize=="+widthSize+"widthMode=:"+MeasureSpec.toString(widthMode));
        LogUtils.d(TAG,"heightSize=="+heightSize+"heightMode=:"+MeasureSpec.toString(heightMode));

        //测量所有子布局(当父布局的测量需要子布局的支持，需要先测量子布局)
        int childCount = getChildCount();
        for (int index=0;index<childCount;index++){
            View childView = getChildAt(index);
            measureChildWithMargins(childView,widthMeasureSpec,0, heightMeasureSpec, 0);
        }

        int currentWidth = getPaddingLeft();//当前测量宽度
        int currentHeight = getPaddingTop();//当前测量高度

        int currentLineMaxHeight = 0;//当前行最大高度

        int destMeasureWidth = 0;//父布局最终宽度
        int destMeasureHeight = 0;//父布局最终高度

        for (int index=0; index<getChildCount(); index++){

            View childView = getChildAt(index);

            if(currentWidth + childView.getMeasuredWidth() + getPaddingRight()> widthSize) {
                //如果大于父布局的宽度
                currentHeight = currentHeight + currentLineMaxHeight;
                currentWidth = 0;
                currentLineMaxHeight = 0;
            }

            currentWidth = currentWidth + childView.getMeasuredWidth();

            currentLineMaxHeight = Math.max(currentLineMaxHeight, childView.getMeasuredHeight());

            if(currentWidth + childView.getMeasuredWidth() + getPaddingRight()> widthSize){
                destMeasureWidth = Math.max(destMeasureWidth, currentWidth + getPaddingRight());
            }

            destMeasureHeight = currentHeight + currentLineMaxHeight;

        }

        //测量父亲布局
        if(getChildCount() == 0){
            setMeasuredDimension(0, 0);
        } if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(destMeasureWidth , destMeasureHeight);
        } else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(destMeasureWidth, heightMeasureSpec);
        } else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthMeasureSpec, destMeasureHeight);
        } else{
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bootom) {
        if(flag){
           return;
        }
        int measuredWidth = getMeasuredWidth();//获取父布局的实际宽度

        int currentWidth = getPaddingLeft();//当前摆放宽度
        int currentHeight = getPaddingTop();//当前摆放高度
        int currentLineMaxHeight = 0; //当前行最大高度

        for(int index=0;index<getChildCount();index++){
            View child = getChildAt(index);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            currentWidth = currentWidth + lp.leftMargin;
            //currentHeight = currentHeight + lp.topMargin;

            if(currentWidth + child.getMeasuredWidth() + lp.rightMargin + getPaddingRight()>  measuredWidth){
                currentHeight = currentHeight + currentLineMaxHeight + lp.bottomMargin;
                currentLineMaxHeight = 0;
                currentWidth = getPaddingLeft() + lp.leftMargin;
            }

            //这里使用getMeasuredWidth和getMeasuredHeight获取子view的真实宽度和高度，getWidth和getHeight这两个方法只有view摆放之后才能获取到值
            //getMeasuredWidth是获取实际的宽度，getWidth是获取屏幕上显示的宽度
            child.layout(currentWidth, currentHeight, currentWidth + child.getMeasuredWidth(), currentHeight + child.getMeasuredHeight());

            currentWidth = currentWidth + child.getMeasuredWidth() + lp.rightMargin;
            //currentHeight = currentHeight + lp.bottomMargin;
            currentLineMaxHeight = Math.max(currentLineMaxHeight, child.getMeasuredHeight());

        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet params) {
        return new MarginLayoutParams(getContext(), params);
    }
}
