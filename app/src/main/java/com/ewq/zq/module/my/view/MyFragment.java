package com.ewq.zq.module.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.module.login.view.ZqLoginActivity;

import butterknife.BindView;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private static MyFragment myFragment;
    @BindView(R.id.img_me_user_icon)
    ImageView userIcon;

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
        userIcon.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        if (v == userIcon) {
            startActivity(new Intent(getActivity(), ZqLoginActivity.class));
        }
    }
}
