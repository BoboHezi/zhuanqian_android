package com.ewq.zq.module.dynamic.view;

import android.os.Bundle;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;

public class DynamicFragment extends BaseFragment {

    private static DynamicFragment dynamicFragment;

    private DynamicFragment() {
        super();
    }

    public static DynamicFragment getInstance() {
        if (dynamicFragment == null) {
            dynamicFragment = new DynamicFragment();
        }
        return dynamicFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
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
