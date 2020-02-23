package com.gdy.dn_2019_10_27.main;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.stetho.common.LogUtil;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.app.YBaseActivity;
import com.gdy.dn_2019_10_27.main.activity.GuaguaKaActivity;
import com.gdy.dn_2019_10_27.receiver.BOOTReceiver;
import com.gdy.libannotation.PermissionDenied;
import com.gdy.libannotation.PermissionGrant;
import com.gdy.libannotation.PermissionRational;
import com.gdy.libpermissionhepler.PermissionProxy;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends YBaseActivity  {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int PERMISSION_REQUEST_CODE_SD = 100;
    private static final int PERMISSION_REQUEST_CODE_PHONE = 101;
    private static final int PERMISSION_REQUEST_CODE_MULTIPLE = 102;

    @BindView(R.id.main_bottom)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public View indexView_1;

    private BOOTReceiver bootReceiver;

    @Override
    protected void initView() {
        //设置全屏
        View decorView = getWindow().getDecorView();//获取屏幕的decorView
        //设置修改状态栏
        /*getYActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
        getYActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));*/

        //设置全屏和状态栏透明
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        indexView_1 = findViewById(R.id.tab_home);

        /*while(true){

        }*/

    }



    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void addEvent() {
        super.addEvent();
        LogUtil.d("MainPresenter",getLifecycle().getClass().getName());

        getLifecycle().addObserver(getPresenter());

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_home:
                    mToolBar.setTitle("自定义View");
                    ((MainPresenter) getPresenter()).goMainFragment();
                    return true;
                case R.id.tab_opengl:
                    mToolBar.setTitle("OpenGl");
                    ((MainPresenter)getPresenter()).goOpenGl();
                    return true;
                case R.id.tab_discover:
                    mToolBar.setTitle("资讯");
                    ((MainPresenter) getPresenter()).goNewsragment();
                    return true;
                case R.id.tab_my:
                    ((MainPresenter) getPresenter()).goMainFragment();
                    return true;
            }
            return false;
        });

        mBottomNavigationView.setSelectedItemId(mBottomNavigationView.getMenu().getItem(0).getItemId());

        //申请运行时权限
        requestPermission();

        //
        bootReceiver = new BOOTReceiver();
        IntentFilter intentFilter = new IntentFilter();
        //亮屏
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        //息屏
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        //解锁
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(bootReceiver,intentFilter);

    }

    @Override
    protected boolean isShowBacking() {
        return false;
    }

    /**
     *
     */
    public void requestPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE_SD);
            }
        }
    }

    @PermissionGrant(PERMISSION_REQUEST_CODE_SD)
    public void onRequestWriteExternalStorageGranted(String[] grantPermissions){
        ToastUtils.showToast("权限申请成功");
    }

    @PermissionDenied(PERMISSION_REQUEST_CODE_SD)
    public void onRequestWriteExternalStorageDenied(String[] deniedPermissions){
        ToastUtils.showToast("权限申请失败");
    }

    @PermissionRational(PERMISSION_REQUEST_CODE_SD)
    public void onRequestWriteExternalStorageRational(String[] deniedPermissions){
        ToastUtils.showToast("onRequestWriteExternalStorageRational");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE_SD:
                StringBuilder builder = new StringBuilder();
                for (int i = 0,len = grantResults.length; i < len; i++) {
                    if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                        builder.append(permissions[i]);
                    }
                }

                if(builder.length()>0){
                    new AlertDialog.Builder(this).setTitle("权限授权提示").setMessage("请授予一下权限,以继续功能的使用\n\n"+builder.toString())
                            .setPositiveButton("好的",new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();
                }
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("TestActivtiy",TAG+"==onCreate==");

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d("TestActivtiy",TAG+"==onSaveInstanceState==");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.d("TestActivtiy",TAG+"==onRestoreInstanceState==");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d("TestActivtiy",TAG+"==onRestart==");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("TestActivtiy",TAG+"==onStart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("TestActivtiy",TAG+"==onResume==");
    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("TestActivtiy",TAG+"==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("TestActivtiy",TAG+"==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("TestActivtiy",TAG+"==onDestroy==");

        if(bootReceiver!=null){
            unregisterReceiver(bootReceiver);
        }
    }

}
