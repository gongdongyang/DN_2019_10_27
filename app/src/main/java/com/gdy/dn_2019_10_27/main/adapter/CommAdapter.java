package com.gdy.dn_2019_10_27.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/12/17
 */
public class CommAdapter<T> extends BaseAdapter {

    public Context Context;
    private LayoutInflater inflater;
    private int layoutId;

    private int varibaleId;//會自动生成

    private List<T> list;

    public CommAdapter(Context context, LayoutInflater inflater,
                       int layoutId, int varibaleId, List<T> list) {
        Context = context;
        this.inflater = inflater;
        this.layoutId = layoutId;
        this.varibaleId = varibaleId;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding dataBinding;
        if(convertView==null){
            dataBinding = DataBindingUtil.inflate(inflater,layoutId,parent,false);
        }else{
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setVariable(varibaleId,list.get(position));
        return dataBinding.getRoot().getRootView();
    }

}
