package com.ewq.zq.module.my.view;

import android.os.Bundle;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;

public class MyFragment extends BaseFragment {

    private static MyFragment myFragment;

    private MyFragment() {
        super();
    }

    public static MyFragment getInstance() {
        if (myFragment == null) {
            myFragment = new MyFragment();
        }
        return myFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {

    }
}
