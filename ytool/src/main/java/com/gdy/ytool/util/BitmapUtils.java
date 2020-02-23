package com.gdy.ytool.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.widget.ImageView;

/**
 * Created by gongdongyang
 * on 2019/10/30
 */
public class BitmapUtils {

    /**
     * 1.画笔Shader的方式
     *
     * @param bitmap
     * @param imageView
     */
    public static void createCircleImageViewByShader(Bitmap bitmap, ImageView imageView) {
        int width = bitmap.getWidth();
        width = Math.min(bitmap.getWidth(),bitmap.getHeight());
        int radius = width / 2;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        Bitmap blankBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(blankBitmap);
        canvas.drawCircle(radius, radius, radius, paint);
        imageView.setImageBitmap(blankBitmap);

    }

    /**
     *
     * @param bitmap
     * @param imageView
     */
    public static void createCircleImageViewByClipPath(Bitmap bitmap, ImageView imageView) {
        int width = bitmap.getWidth();
        width = Math.min(bitmap.getWidth(),bitmap.getHeight());
        int radius = width/2;
        //创建一个空白画布
        Bitmap blankBitmap = Bitmap.createBitmap(width,width,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(blankBitmap);
        Path path = new Path();
        //PathDirectionCCW   逆时针
        //PathDirectionCW  顺时针

        path.addCircle(radius,radius,radius, Path.Direction.CW);
        canvas.clipPath(path);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        canvas.drawBitmap(bitmap,0,0,paint);

        imageView.setImageBitmap(blankBitmap);
    }

    public static void createCircleImageViewByXfermode(Bitmap bitmap,ImageView imageView){

        int width = bitmap.getWidth();
        width = Math.min(bitmap.getWidth(),bitmap.getHeight());

        int radius = width/2;

        Paint p = new Paint();
        p.setAntiAlias(true); //去锯齿
        //创建一个空白画布
        Bitmap blankBitmap = Bitmap.createBitmap(width,width,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(blankBitmap);  //bitmap就是我们原来的图,比方头像
        //画第一个图层
        canvas.drawCircle(radius,radius,radius,p);

        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  //由于我们先画了图所以DST_IN
        //画第二个图
        canvas.drawBitmap(bitmap,0,0,p);
        imageView.setImageBitmap(blankBitmap);


    }
}