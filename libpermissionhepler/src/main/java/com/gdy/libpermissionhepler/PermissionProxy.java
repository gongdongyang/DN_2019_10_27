package com.gdy.libpermissionhepler;

/**
 * Created by gongdongyang
 * on 2019/12/14
 */
public interface PermissionProxy<T> {

    void grant(T source, int requestCode, String... permission);

    void denied(T source, int requestCode, String... permission);

    //boolean rationale(T source, int requestCode, String[] permission, PermissionRationalCallback rationalCallback);
    boolean rationale(T source, int requestCode, String[] permission);

    //boolean needShowRationale(int requestCode, String... permission);
}
