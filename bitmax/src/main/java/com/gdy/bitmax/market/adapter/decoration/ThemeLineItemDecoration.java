package com.gdy.bitmax.market.adapter.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public class ThemeLineItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;

    private Paint dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ThemeLineItemDecoration() {
        this(Color.parseColor("#F0f2f3"));
    }

    public ThemeLineItemDecoration(@ColorInt int colorInt) {
        this(1,Color.parseColor("#F0f2f3"));
    }

    public ThemeLineItemDecoration(int dividerHeight, @ColorInt int colorInt) {
        this.dividerHeight = dividerHeight;
        this.dividerPaint.setColor(colorInt);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = this.dividerHeight;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        int i = parent.getChildCount();
        int j = parent.getPaddingLeft();
        int k = parent.getWidth();
        int m = parent.getPaddingRight();
        byte b;
        for (b = 0; b < i - 1; b++) {
            View view = parent.getChildAt(b);
            float f1 = view.getBottom();
            float f2 = (view.getBottom() + this.dividerHeight);
            canvas.drawRect(j, f1, (k - m), f2, this.dividerPaint);
        }
    }
}
