package com.gdy.dn_2019_10_27.databind.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.gdy.dn_2019_10_27.BR;
import com.squareup.picasso.Picasso;

/**
 * Created by gongdongyang
 * on 2019/12/17
 */
public class UserViewModel extends BaseObservable {

    private String name;
    private String sex;
    private String age;
    private String header;

    public UserViewModel(String name, String sex, String age, String header) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.header = header;
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

    public String getAge() {
        return age;
    }

    @Bindable
    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(com.gdy.dn_2019_10_27.BR.age);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @BindingAdapter("header")
    public void getImage(ImageView view,String url){
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
