package com.gdy.dwlibrary;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by gongdongyang
 * on 2019/12/18
 */
public interface DownloadApiInterface {

    @Streaming
    @GET("mygoedu/yztv_1.apk")
    Observable<ResponseBody> downloadApkFile();
}
