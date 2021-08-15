package com.ewq.zq.module.home.model;

import android.text.SpannableString;

public class TaskHomeBean {

    private SpannableString title;

    private SpannableString subTitle;

    private float money;

    public TaskHomeBean() {
    }

    public TaskHomeBean(SpannableString title, SpannableString subTitle, float money) {
        this.title = title;
        this.subTitle = subTitle;
        this.money = money;
    }

    public SpannableString getTitle() {
        return title;
    }

    public void setTitle(SpannableString title) {
        this.title = title;
    }

    public SpannableString getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(SpannableString subTitle) {
        this.subTitle = subTitle;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "TaskHomeBean{" +
                "title=" + title +
                ", subTitle=" + subTitle +
                ", money=" + money +
                '}';
    }
}
