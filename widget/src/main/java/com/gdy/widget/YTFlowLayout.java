package com.gdy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.gdy.ytool.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/10/31
 */
public class YTFlowLayout extends ViewGroup {

    //private int mWidth;

    List<List<View>> mListView = new ArrayList<>();

    public YTFlowLayout(Context context) {
        super(context);
    }

    public YTFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YTFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取父布局穿过的宽度和高度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //流式布局padding尺寸
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        int lineWidth = 0,currentHeight = getPaddingTop(),currentLineHeight = 0, destMeasureHeight = 0;

        //测量子View个宽度和高度
        for(int ind = 0;ind < getChildCount();ind++){

            //获取子View
            View childView = getChildAt(ind);

            measureChildWithMargins(childView,widthMeasureSpec,0,heightMeasureSpec,0);

            MarginLayoutParams lp = (MarginLayoutParams)childView.getLayoutParams();

            //获取子View的宽高
            int childWidth = childView.getMeasuredWidth() + lp.getMarginEnd() + lp.getMarginStart();


            if(lineWidth+paddingLeft+paddingRight+childWidth > widthSize){
                lineWidth = 0;
                currentHeight = currentHeight + currentLineHeight;
                currentLineHeight = 0;
            }

            lineWidth = lineWidth + childWidth;

            currentLineHeight = Math.max(currentLineHeight,childView.getMeasuredHeight()+lp.topMargin+lp.bottomMargin);

            destMeasureHeight = currentHeight + currentLineHeight;

            LogUtils.d("YTFlowLayout","lineWidth="+lineWidth+"=destMeasureHeight="+destMeasureHeight);
        }

        setMeasuredDimension(widthSize,destMeasureHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineWidth = getPaddingLeft(),currentHeight = getPaddingTop(),currentLineHeight = 0;
        int widthSize = getMeasuredWidth();
        for(int ind = 0;ind < getChildCount();ind++){
            //获取子View
            View childView = getChildAt(ind);
            MarginLayoutParams lp = (MarginLayoutParams)childView.getLayoutParams();
            //获取子View的宽高
            int childWidth = childView.getMeasuredWidth() + lp.getMarginEnd() + lp.getMarginStart();
            if(lineWidth+childWidth+getPaddingRight()>widthSize){
                lineWidth = getPaddingLeft();
                currentHeight = currentHeight + currentLineHeight;
                currentLineHeight= 0;
            }

            currentLineHeight = Math.max(currentLineHeight,childView.getMeasuredHeight()+lp.topMargin+lp.bottomMargin);

            childView.layout(lineWidth,currentHeight,lineWidth+childView.getMeasuredWidth(),currentHeight+childView.getMeasuredHeight());



            lineWidth = lineWidth + childWidth;

        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
