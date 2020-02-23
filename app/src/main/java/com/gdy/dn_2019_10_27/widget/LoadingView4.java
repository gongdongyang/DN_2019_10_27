package com.gdy.dn_2019_10_27.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gdy.dn_2019_10_27.R;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.PixelUtils;

/**
 * Created by gongdongyang
 * on 2020/2/16
 */
public class LoadingView4 extends View {

    private int DEGREE_PER_CIRCLE = 360/12;

    private int mSize;
    private int mColor;

    private long mDuration;   //动画时长

    private Paint mPaint;

    private Context mContext;

    private float mMinCircleRadius,mMaxCircleRadius;

    private int mWidth,mHeight;

    private Paint mBlackPaint;

    private float[] mWholeCircleRadius = new float[12];  //记录所有小圆半径

    private ValueAnimator mValueAnimator;

    public LoadingView4(Context context) {
        this(context,null);
    }

    public LoadingView4(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
        initValue();
    }




    private void initAttrs(Context context, AttributeSet attrs) {

        mContext = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HW_Loading_View);

        mSize = (int)ta.getDimension(R.styleable.HW_Loading_View_hlv_size,100);
        LogUtils.d("LoadingView4","mSize=1="+mSize);
        //mSize = PixelUtils.dp2px(mContext,mSize);
        LogUtils.d("LoadingView4","mSize=2="+mSize);
        mColor = ta.getColor(R.styleable.HW_Loading_View_hlv_color, Color.parseColor("#333333"));

        mDuration = ta.getInt(R.styleable.HW_Loading_View_hlv_duration,1500);

        mMinCircleRadius = PixelUtils.dp2px(context,mMinCircleRadius<=0?5:mMinCircleRadius);

        mMaxCircleRadius = 2.5f*mMinCircleRadius;

        ta.recycle();

    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(visibility == View.VISIBLE){
            start();
        }else{
            stop();
        }
    }

    private void initPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setDither(true);

        mBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBlackPaint.setColor(mColor);
        mBlackPaint.setDither(true);
        mBlackPaint.setStrokeWidth(PixelUtils.dp2px(mContext,1));
        mBlackPaint.setStyle(Paint.Style.STROKE);
    }

    private void initValue() {
        for (int i = 0; i < 12 ; i++) {
            switch (i){
                case 7:
                    mWholeCircleRadius[i] = mMinCircleRadius * 1.25f;
                    break;
                case 8:
                    mWholeCircleRadius[i] = mMinCircleRadius * 1.5f;
                    break;
                case 9:
                    mWholeCircleRadius[i] = mMinCircleRadius * 1.75f;
                    break;
                case 10:
                    mWholeCircleRadius[i] = mMinCircleRadius * 2f;
                    break;
                case 11:
                    mWholeCircleRadius[i] = mMinCircleRadius * 2.25f;
                    break;
                case 12:
                    mWholeCircleRadius[i] = mMinCircleRadius * 2.5f;
                    break;
                default:
                    mWholeCircleRadius[i] = mMinCircleRadius;
                    break;
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(PixelUtils.dp2px(mContext,mSize),PixelUtils.dp2px(mContext,mSize));
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制12个小圆

        canvas.drawCircle(mWidth/2,mHeight/2,mSize,mBlackPaint);

        //绘制12个小圆
        for(int i =0 ;i<12;i++){
            canvas.save();
            canvas.rotate((i+1)*DEGREE_PER_CIRCLE,mWidth/2,mHeight/2);
            drawSmallCircle(canvas,mWholeCircleRadius[i]);
            canvas.restore();
        }
    }

    private void drawSmallCircle(Canvas canvas,float circleSize){
        canvas.drawCircle(mWidth/2,mHeight/2-mSize,circleSize,mPaint);
    }

    private ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int animateValue = (int)animation.getAnimatedValue();
            LogUtils.d("LoadingView4","animateValue=="+animateValue);
            invalidate();
        }
    };

    private void start() {
        if(mValueAnimator==null){
            mValueAnimator = ValueAnimator.ofInt(0,11);
            mValueAnimator.addUpdateListener(updateListener);
            mValueAnimator.setDuration(mDuration);
            mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
            mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.start();

        }else if(!mValueAnimator.isStarted()){
            mValueAnimator.start();
        }
    }

    private void stop() {
        if(mValueAnimator!=null){
            mValueAnimator.removeUpdateListener(updateListener);
            mValueAnimator.removeAllUpdateListeners();
            mValueAnimator.cancel();
            mValueAnimator=null;
        }
    }
}
