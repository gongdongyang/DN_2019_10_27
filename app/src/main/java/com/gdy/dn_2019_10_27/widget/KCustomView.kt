package com.gdy.dn_2019_10_27.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.app.ActivityCompat
import com.gdy.dn_2019_10_27.R
import com.gdy.ytool.PixelUtils
import android.graphics.Rect
import android.graphics.fonts.Font
import com.gdy.ytool.LogUtils


/**
 *Created by gongdongyang
 *on 2020/1/17
 */
class KCustomView : View  {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var radius:Float = 70f

    var paint:Paint? = null

    var widthSize:Int = 0;

    var heightSize:Int = 0;

    //绘制文字
    var stringT:String = "3058"
    var textBound = Rect()

    var textPaint:Paint = Paint(Paint.ANTI_ALIAS_FLAG);

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint?.color = resources.getColor(R.color.Red)
        paint?.style = Paint.Style.STROKE
        paint?.strokeWidth = 30f

        textPaint.color = resources.getColor(R.color.Red)
        textPaint.style = Paint.Style.FILL
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = PixelUtils.dp2px(context,40f).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textPaint.getTextBounds(stringT,0,stringT.length,textBound)
        LogUtils.d("KCustomView","stringT==top:"+textBound.top+"=bottom:"+textBound.bottom)
        var offset:Int = (textBound.top + textBound.bottom).toInt()/2;
        radius = Math.min(widthSize,heightSize)/3.5f



        var  fontMetrics:Paint.FontMetrics = Paint.FontMetrics()
        textPaint.getFontMetrics(fontMetrics)

        offset = ((fontMetrics.ascent+fontMetrics.descent)/2).toInt()

        canvas?.drawCircle(widthSize/2.0f,heightSize/2.0f,radius,paint!!);
        canvas?.drawText(stringT,widthSize/2.0f,heightSize/2.0f-offset,textPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);


    }



}