package com.gdy.libannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by gongdongyang
 * on 2019/12/14
 */
@Target(ElementType.METHOD)
public @interface PermissionDenied {
    int value();
}
