package com.ewq.zq.module.home.model;

import android.graphics.drawable.Drawable;

public class TileBean {

    private Drawable icon;

    private String title;

    private String action;

    public TileBean(Drawable icon, String title, String action) {
        this.icon = icon;
        this.title = title;
        this.action = action;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "TileBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
