package com.ewq.zq.activity;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseActivity;
import com.leaf.library.StatusBarUtil;

public class TaskDetailActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.yd_xo_new_task_detail;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    protected void loadData() {

    }
}
