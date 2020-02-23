package com.gdy.basedialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.gdy.ytool.LogUtils;
import com.gdy.ytool.PixelUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gongdongyang
 * on 2019/12/18
 */
public abstract class BaseDialogFragment extends DialogFragment {

    public View mRootView;

    public Unbinder unbinder;

    private Dialog mBaseDialog;

    public static int DEFAULT_PADDING_START = 50;

    public static int DEFAULT_PADDING_TOP = 50;

    public BaseDialogFragment(){

    }

    public abstract  int getLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView = inflater.inflate(getLayout(),container,false);
            unbinder = ButterKnife.bind(this,mRootView);
        }

        return mRootView;
    }



    @Override
    public void onStart() {
        super.onStart();
        mBaseDialog = getDialog();
        mBaseDialog.setCanceledOnTouchOutside(false);

        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //获取屏幕分辨率
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        //获取Window对象的LayoutParms
        WindowManager.LayoutParams layoutParams = window.getAttributes();

        layoutParams.width = dm.widthPixels - 2*PixelUtils.dp2px(getContext(),getPaddingStart()>0?getPaddingStart():DEFAULT_PADDING_START);
        layoutParams.height = dm.heightPixels - 2*PixelUtils.dp2px(getContext(),getPaddingTop()>0?getPaddingTop():DEFAULT_PADDING_TOP);
        window.setAttributes(layoutParams);

    }



    public abstract int getPaddingStart();

    public abstract int getPaddingTop();


}
