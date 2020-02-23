package com.gdy.dn_2019_10_27.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.stetho.common.LogUtil;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.PixelUtils;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public class CustomCanvasView extends View {

    private int mWidth, mHeight, mRadius;

    private Paint mGreenPaint, mRedPaint,mPaint;

    public CustomCanvasView(Context context) {
        this(context, null);
    }

    public CustomCanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGreenPaint.setDither(true);
        mGreenPaint.setColor(getResources().getColor(android.R.color.holo_green_dark));
        mGreenPaint.setStrokeWidth(PixelUtils.dp2px(getContext(), 3));

        mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRedPaint.setDither(true);
        mRedPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mRedPaint.setStrokeWidth(PixelUtils.dp2px(getContext(), 3));

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        //mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = mWidth / 3;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.d("CustomCanvasView", "onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        /*LogUtils.d("CustomCanvasView","mWidth=="+mWidth);
        //
        int state1 = canvas.save();
        canvas.drawCircle(mWidth/2,mHeight/2,mRadius,mGreenPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        mRedPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        canvas.drawRect(mWidth/2,mHeight/2,mWidth/2+mRadius,mHeight/2+mRadius,mRedPaint);

        mRedPaint.setXfermode(null);

        canvas.restoreToCount(state1);*/
        //int layerID = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(createDstBigmap(), 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(createSrcBigmap(), 0,0, mPaint);
        mPaint.setXfermode(null);

        //canvas.restoreToCount(layerID);

        /* Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(mWidth/2,mHeight/2,mRadius,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        paint.setColor(Color.GREEN);
        canvas.drawRect(mWidth/2,mHeight/2,mWidth/2+mRadius,mHeight/2+mRadius,paint);*/
        //paint.setXfermode(null);
    }

    public Bitmap createDstBigmap() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth,mHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(mWidth/2,mHeight/2,mRadius,mGreenPaint);
        return bitmap;
    }

    public Bitmap createSrcBigmap() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth,mHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(mWidth/2,mHeight/2,mWidth/2+mRadius+50,mHeight/2+mRadius+50,mRedPaint);
        return bitmap;
    }
}
