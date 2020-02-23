package com.gdy.dn_2019_10_27.download;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import com.gdy.basedialog.BaseDialogFragment;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dwlibrary.DownloadApiInterface;
import com.gdy.dwlibrary.DownloadProgressHandler;
import com.gdy.dwlibrary.FileDownloader;
import com.gdy.dwlibrary.RetrofitHelper;
import com.gdy.ytool.LogUtils;
import com.gdy.ytool.ToastUtils;

import java.io.File;

import butterknife.BindView;

/**
 * Created by gongdongyang
 * on 2019/12/18
 * /storage/emulated/0/Android/data/com.gdy.dn_2019_10_27/files/test.apk
 */
public class UpgradeDailog extends BaseDialogFragment {

    @BindView(R.id.iv_close)
    ImageView mImgClose;
    @BindView(R.id.btn_cancel)
    public AppCompatButton mBtnCancel;
    @BindView(R.id.btn_confirm)
    public AppCompatButton mBtnConfirm;

    String fileName = "/storage/emulated/0/Android/data/com.gdy.dn_2019_10_27/files/test.apk";
    @Override
    public int getLayout() {
        return R.layout.dialog_upgrade;
    }


    @Override
    public int getPaddingStart() {
        return 30;
    }

    @Override
    public int getPaddingTop() {
        return 100;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImgClose.setOnClickListener(v -> dismiss());
        mBtnCancel.setOnClickListener(v ->{
            ToastUtils.showToast("mBtnCancel");
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            File file = new File(fileName);
            LogUtils.d("UpgradeDailog","length:"+file.length());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                Uri uri = FileProvider.getUriForFile(getActivity(), "com.gdy.dn_2019_10_27.fileprovider", new File(fileName));
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
            }
            startActivity(intent);
            //dismiss();
        } );
        mBtnConfirm.setOnClickListener(v ->{
            DownloadApiInterface apiService = RetrofitHelper.getInstance().getApiService(DownloadApiInterface.class);
            FileDownloader.downloadFile(apiService.downloadApkFile(), getContext().getExternalFilesDir(null).getPath() , "test.apk", new DownloadProgressHandler() {
                @Override
                public void onProgress(int progress, long total, long speed) {
                    LogUtils.d("UpgradeDailog","progress="+progress);
                }

                @Override
                public void onCompleted(File file) {
                    openFile(file);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
            dismiss();
        });


    }


    private void openFile(File file) {
        // TODO Auto-generated method stub
        LogUtils.d("UpgradeDailog",file.getAbsolutePath());
        /*Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);*/
    }
}
