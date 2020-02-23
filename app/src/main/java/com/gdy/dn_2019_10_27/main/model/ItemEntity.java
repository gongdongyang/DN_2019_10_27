package com.gdy.dn_2019_10_27.main.model;


public class ItemEntity{

    private String title;
    private Class className;
    private int type;//1 跳转Activity 2 Action

    public ItemEntity(String title, Class className) {
        this.title = title;
        this.className = className;
        this.type = 1;
    }

    public ItemEntity(String title, Class className, int type) {
        this.title = title;
        this.className = className;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}