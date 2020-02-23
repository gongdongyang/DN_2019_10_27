package com.gdy.ytool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by gongdongyang
 * on 2019/10/2
 */
public class NavitateUtil {

    /**
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class cls, String ...params) {
        Intent intent = new Intent(context, cls);
        if(params!=null && params.length>1){
            for(int index=0;index<params.length/2;index++){
                intent.putExtra(params[index],params[index+1]);
            }
        }
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param cls
     */
    /*public static void startActivity(Context context, Class cls, Map<String,String> params) {
        Intent intent = new Intent(context, cls);
        //intent.putExtra("bundle", bundle);
        //params.
        Iterator<String> iterator =  params.keySet().iterator();
        while(iterator.hasNext()){
            String key  = iterator.next();
            intent.putExtra(key, params.get(key));
        }
        context.startActivity(intent);
    }*/

    /**
     *
     * @param context
     * @param url
     */
    public static void startOutsideBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}
