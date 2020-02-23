package com.gdy.dn_2019_10_27.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gdy.dn_2019_10_27.R;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.PathParser;
import com.gdy.ytool.ToastUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by gongdongyang
 * on 2020/2/21
 */
public class MapView extends View {

    private Context context;

    private Paint paint;

    private List<ProviceItem> itemList;

    private int[] colorArray =  new int[]{0xFF239BD7, 0xFF30A9E5, 0xFF80CBF1, 0xFFFFFFFF};

    //适配比例
    private float scale=1.0f;

    private int mViewWidth,mViewHeight;

    //地图的边界
    private RectF boundRect = new RectF();

    //被选中的省份
    private ProviceItem select;

    public MapView(Context context) {
        this(context,null);
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        localThread.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    private Handler mMainHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //绘制地图
            ToastUtils.showToast(boundRect.left+"=="+boundRect.top+"right:"+boundRect.right+"bottom:"+boundRect.bottom);

            if(itemList==null || itemList.size()==0){
                return ;
            }

            for(int i = 0;i<itemList.size(); i++){
                ProviceItem item = itemList.get(i);
                item.setDrawColor(colorArray[i%4]);
            }
            //LogUtils.d("MapView","3333=="+scale);
            postInvalidate();
        }
    };

    private Thread localThread = new Thread(){
        @Override
        public void run() {
            //打开raw文件
            InputStream inputStream = context.getResources().openRawResource(R.raw.china);
            //List<ProviceItem> itemList = new ArrayList<>();
            try{
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(inputStream);

                //获取到根元素
                Element rootElement = document.getDocumentElement();

                //
                NodeList pathList = rootElement.getElementsByTagName("path");

                //获取地图的边界
                float top = -1,right = -1, bottom =-1,left =-1;

                if(pathList!=null && pathList.getLength()>0){

                    itemList = new ArrayList<>();

                    for(int i=0;i<pathList.getLength();i++){
                        Element element  = (Element) pathList.item(i);
                        String pathData = element.getAttribute("android:pathData");
                        Path path  = PathParser.createPathFromPathData(pathData);


                        ProviceItem proviceItem = new ProviceItem(path);

                        itemList.add(proviceItem);

                        //获取Path的边界
                        RectF itemRect = new RectF();
                        path.computeBounds(itemRect,true);

                        left = left==-1?itemRect.left:Math.min(itemRect.left,left);

                        top = top==-1?itemRect.top:Math.min(itemRect.top,top);

                        right = right==-1?itemRect.right:Math.max(itemRect.right,right);

                        bottom = bottom==-1?itemRect.bottom:Math.max(itemRect.bottom,bottom);
                    }

                    boundRect.set(left,top,right,bottom);
                    //计算scale
                    scale = (float) mViewWidth/boundRect.width();
                    //绘制地图 子线程通知主线程
                    //LogUtils.d("MapView","scale=3="+scale);

                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Message message = mMainHandler.obtainMessage();
                mMainHandler.sendEmptyMessage(1);
            }

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(itemList==null || itemList.size()==0){
            return;
        }
        canvas.save();
        LogUtils.d("MapView","scale=="+scale);

        canvas.scale(scale,scale);
        for(int i=0; i<itemList.size();i++){
            ProviceItem item = itemList.get(i);
            item.drawItem(canvas,paint,false);
        }
        if(select!=null){
            select.drawItem(canvas,paint,true);

        }
        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handlerTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //处理点击事件
    private void handlerTouchEvent(MotionEvent event) {
        LogUtils.d("MapView","handlerTouchEvent");
        if(itemList==null || itemList.size()==0){
            return;
        }
        ProviceItem selectItem = null;
        for (int i = 0; i < itemList.size(); i++) {
            ProviceItem item = itemList.get(i);
            if(item.isTouch(event.getX()/scale,event.getY()/scale)){
                selectItem = item;
                LogUtils.d("MapView","selectIndex=="+i);
                break;
            }
        }
        if(selectItem!=null){
            select = selectItem;
            postInvalidate();
        }

    }
}
