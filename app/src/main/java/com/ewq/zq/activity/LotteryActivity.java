package com.ewq.zq.activity;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseActivity;
import com.leaf.library.StatusBarUtil;

public class LotteryActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_lottery;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, 0xffe83238);
    }

    @Override
    protected void loadData() {

    }
}
