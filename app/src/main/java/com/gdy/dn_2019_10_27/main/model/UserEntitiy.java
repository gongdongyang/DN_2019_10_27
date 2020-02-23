package com.gdy.dn_2019_10_27.main.model;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.gdy.dn_2019_10_27.BR;
import com.gdy.ytool.LogUtils;
import com.squareup.picasso.Picasso;


/**
 * Created by gongdongyang
 * on 2019/12/17
 */
public class UserEntitiy extends BaseObservable {

    private String name;
    private String sex;
    private int age;
    private String header;


    public UserEntitiy(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.gdy.dn_2019_10_27.BR.name);
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(com.gdy.dn_2019_10_27.BR.sex);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(com.gdy.dn_2019_10_27.BR.age);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    //自定义属性：提供一个静态方法来加载image
    @BindingAdapter("header")
    public static void getImage(ImageView view,String url){
        LogUtils.d("UserEntitiy");
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
