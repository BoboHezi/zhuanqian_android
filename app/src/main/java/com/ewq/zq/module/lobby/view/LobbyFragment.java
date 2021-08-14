package com.ewq.zq.module.lobby.view;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.widget.NavigationLayout;

import butterknife.BindView;

public class LobbyFragment extends BaseFragment implements NavigationLayout.OnTabClickListener {

    private static final String TAG = "LobbyFragment";

    private static LobbyFragment lobbyFragment;
    @BindView(R.id.main_pager)
    ViewPager mViewPager;
    @BindView(R.id.header_nav)
    NavigationLayout mNavigation;

    private LobbyFragment() {
        super();
    }

    public static LobbyFragment getInstance() {
        if (lobbyFragment == null) {
            lobbyFragment = new LobbyFragment();
        }
        return lobbyFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lobby;
    }

    @Override
    protected void initView() {
        mNavigation.setTabClickListener(this);
        mNavigation.attachViewPager(mViewPager);
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onTabClick(View view, int index) {

    }
}
