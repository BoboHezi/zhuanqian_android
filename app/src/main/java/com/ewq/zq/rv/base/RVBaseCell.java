package com.ewq.zq.rv.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class RVBaseCell<T> implements Cell {

    public T mData;

    public RVBaseCell(T t) {
        mData = t;
    }

    @Override
    public void releaseResource() {
    }

    @Override
    public View getView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(getLayoutId(), parent, false);
    }
}
