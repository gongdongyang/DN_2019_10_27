package com.gdy.ytool;

import android.view.Choreographer;

/**
 * Created by gongdongyang
 * on 2019/10/4
 */
public class SMFrameCallback implements Choreographer.FrameCallback{

    public static long currentFrameTimeNanos = 0L;

    public static final float deviceRefreshRateMs = 16.6F;

    public static long lastFrameTimeNanos;

    public static SMFrameCallback sInstance;

    private String TAG = "SMFrameCallback";

    public static SMFrameCallback getInstance() {
        if (sInstance == null)
            sInstance = new SMFrameCallback();
        return sInstance;
    }

    private int skipFrameCount(long startFrameTimeNanos, long endFrameTimeNanos, float inter) {
        startFrameTimeNanos = Math.round((float)(endFrameTimeNanos - startFrameTimeNanos) / 1000000.0F);
        endFrameTimeNanos = Math.round(inter);
        return (startFrameTimeNanos > endFrameTimeNanos) ? (int)(startFrameTimeNanos / endFrameTimeNanos) : 0;
    }
    @Override
    public void doFrame(long frameTimeNanos) {
        long l1 = lastFrameTimeNanos;
        if (l1 == 0L) {
            lastFrameTimeNanos = frameTimeNanos;
            Choreographer.getInstance().postFrameCallback(this);
            return;
        }
        currentFrameTimeNanos = frameTimeNanos;
        long l2 = currentFrameTimeNanos;
        float f = (float)(l2 - l1) / 1000000.0F;
        int i = skipFrameCount(l1, l2, 16.6F);
        if (f > 100.0F) {
            String str = this.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("两次绘制的时间间隔value=");
            stringBuilder.append(f);
            stringBuilder.append("  frameTimeNanos=");
            stringBuilder.append(frameTimeNanos);
            stringBuilder.append("  currentFrameTimeNanos=");
            stringBuilder.append(currentFrameTimeNanos);
            stringBuilder.append("  skipFrameCount=");
            stringBuilder.append(i);
            stringBuilder.append("");
        }
        lastFrameTimeNanos = currentFrameTimeNanos;
        Choreographer.getInstance().postFrameCallback(this);
    }

    public void start() { Choreographer.getInstance().postFrameCallback(getInstance()); }
}
