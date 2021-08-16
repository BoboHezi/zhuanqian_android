package com.ewq.zq.module.dynamic.model;

import android.graphics.drawable.Drawable;

public class ShopBean {
    private Drawable icon;

    private String name;

    private String id;

    private String desc;

    private boolean followed;

    public ShopBean() {
    }

    public ShopBean(String name, String id, String desc, boolean followed) {
        this.name = name;
        this.id = id;
        this.desc = desc;
        this.followed = followed;
    }

    public ShopBean(Drawable icon, String name, String id, String desc, boolean followed) {
        this.icon = icon;
        this.name = name;
        this.id = id;
        this.desc = desc;
        this.followed = followed;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", followed=" + followed +
                '}';
    }
}
