package com.gdy.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by gongdongyang
 * on 2019/10/27
 */
public class GuaGuaCardView extends View {

    private Paint mBitPaint;
    private Bitmap BmpDST,BmpSRC,BmpText;
    private Path mPath;

    public GuaGuaCardView(Context context) {
        this(context,null);
    }

    public GuaGuaCardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GuaGuaCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitPaint = new Paint();
        mBitPaint.setColor(Color.RED);
        mBitPaint.setStyle(Paint.Style.STROKE);
        mBitPaint.setStrokeWidth(45);

        BmpText = BitmapFactory.decodeResource(getResources(), R.drawable.guaguaka_text1,null);
        BmpSRC = BitmapFactory.decodeResource(getResources(), R.drawable.guaguaka,null);
        BmpDST = Bitmap.createBitmap(BmpSRC.getWidth(), BmpSRC.getHeight(), Bitmap.Config.ARGB_8888);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(BmpText,0,0,mBitPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //Log.d("GuaGuaCardView","layerId==>:"+layerId);
        //先把手指轨迹画到目标Bitmap上
        Canvas c = new Canvas(BmpDST);
        c.drawPath(mPath,mBitPaint);

        //然后把目标图像画到画布上
        canvas.drawBitmap(BmpDST,0,0,mBitPaint);



        //计算源图像区域
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(BmpSRC,0,0,mBitPaint);

        mBitPaint.setXfermode(null);
        /*canvas.restoreToCount(2);*/
    }

    float mCurrentX,mCurrentY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mCurrentX = event.getX();
                mCurrentY = event.getY();
                mPath.moveTo(mCurrentX,mCurrentY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mCurrentX+event.getX())/2;
                float endY = (mCurrentY+event.getY())/2;
                mPath.quadTo(mCurrentX,mCurrentY,endX,endY);
                mCurrentX = event.getX();
                mCurrentY =event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();

        return super.onTouchEvent(event);
    }
}
