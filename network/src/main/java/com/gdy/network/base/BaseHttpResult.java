package com.gdy.network.base;

import java.io.Serializable;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public class BaseHttpResult<T> implements Serializable {

    public int code;
    public String message;
    public T data;
}
