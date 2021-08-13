package com.ewq.zq.module.home.view;

import android.os.Bundle;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    private static HomeFragment homeFragment;

    private HomeFragment() {
        super();
    }

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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
