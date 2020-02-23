package com.gdy.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.annotation.Nullable;

import com.gdy.ytool.LogUtils;

/**
 * Created by gongdongyang
 * on 2019/12/19
 */
public class LoginButton extends View {

    private final int duration = 300;//按钮缩短动画的持续时间

    private Paint paintBg;

    private int bgColor;

    private Paint paintCircle;
    private Paint paintText;
    private int textColor;
    private String text;
    private int textSize;

    private int marginLength;


    private int mWidth,mHeight;
    private int mTextWidth,mTextHeight;

    private boolean drawCircle = false;

    public LoginButton(Context context) {
        this(context,null);
    }

    public LoginButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public LoginButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.d("LoginButton","heightMode="+MeasureSpec.toString(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.d("LoginButton","height=="+getHeight());
        mWidth = getWidth();
        mHeight = getHeight();
    }

    /**
     *
     */
    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.LoginButton);

        bgColor = array.getColor(R.styleable.LoginButton_bgColor, Color.RED);

        textColor = array.getColor(R.styleable.LoginButton_textColor, Color.WHITE);
        text = array.getString(R.styleable.LoginButton_text);
        textSize = array.getDimensionPixelSize(R.styleable.LoginButton_textSize, 24);

        array.recycle();
        //设置按钮可点击，可获得焦点
        setClickable(true);
        setFocusable(true);

        paintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBg.setColor(bgColor);
        paintBg.setStyle(Paint.Style.FILL);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(textColor);
        paintText.setTextSize(textSize);

        paintCircle = new Paint();
        paintCircle.setColor(textColor);
        paintCircle.setStrokeWidth(3);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);

        Rect rect = new Rect();
        paintText.getTextBounds(text,0,text.length(),rect);
        mTextWidth = rect.width();
        mTextHeight = rect.height();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);

        if(!drawCircle){
            drawText(canvas);
        }else{
            drawCircle(canvas);
        }

    }

    /**
     * 绘制圆形
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        RectF oval = new RectF();
        oval.left = mWidth / 2 - mHeight / 2 + mHeight / 5;
        oval.right = oval.left + mHeight / 5 * 3;
        oval.top = mHeight / 5;
        oval.bottom = oval.top + mHeight / 5 * 3;
        canvas.drawArc(oval, 0, 120, false, paintCircle);
    }


    /**
     *
     * @param canvas
     * RectF
     */
    private void drawBg(Canvas canvas) {

        RectF rectF = new RectF();
        rectF.left = marginLength;
        rectF.right = mWidth - marginLength;
        rectF.top = 0;
        rectF.bottom = mHeight;
        canvas.drawRoundRect(rectF,mHeight/2,mHeight/2,paintBg);
    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        canvas.drawText(text,mWidth/2-mTextWidth/2,mHeight/2+mTextHeight/2,paintText);

    }

    public void click(){
        setClickable(false);
        float scale = (float) mHeight / mWidth;
        ValueAnimator animator = ValueAnimator.ofFloat(1,scale);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float)animation.getAnimatedValue();
                LogUtils.d("LoginButton","f=="+f);
                marginLength = (int) (mWidth * (1 - f)) / 2;
                if ((mWidth - marginLength * 2) < mTextWidth * 1.5)
                    drawCircle = true;
                invalidate();

            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startCircleAnim();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    //开始按钮的旋转动画，表示加载中
    private void startCircleAnim() {
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(duration * 2);
        ra.setInterpolator(new LinearInterpolator());
        ra.setRepeatCount(-1);
        ra.setFillAfter(true);
        startAnimation(ra);
    }
}
