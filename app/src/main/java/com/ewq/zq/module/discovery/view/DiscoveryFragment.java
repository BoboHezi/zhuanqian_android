package com.ewq.zq.module.discovery.view;

import android.os.Bundle;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;

public class DiscoveryFragment extends BaseFragment {

    private static DiscoveryFragment discoveryFragment;

    private DiscoveryFragment() {
        super();
    }

    public static DiscoveryFragment getInstance() {
        if (discoveryFragment == null) {
            discoveryFragment = new DiscoveryFragment();
        }
        return discoveryFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery;
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
