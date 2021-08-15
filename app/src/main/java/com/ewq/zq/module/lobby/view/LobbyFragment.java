package com.ewq.zq.module.lobby.view;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ewq.zq.R;
import com.ewq.zq.adapter.FragmentsPagerAdapter;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.widget.NavigationLayout;

import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;

public class LobbyFragment extends BaseFragment implements NavigationLayout.OnTabClickListener {

    private static final String TAG = "LobbyFragment";
    private static LobbyFragment lobbyFragment;
    @BindView(R.id.main_pager)
    ViewPager mViewPager;
    @BindView(R.id.header_nav)
    NavigationLayout mNavigation;
    private ConcurrentHashMap<Integer, Fragment> mFragments = new ConcurrentHashMap<>();

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
        mViewPager.setSaveEnabled(false);
    }

    @Override
    protected void initData(Bundle arguments) {
        mFragments.put(0, new TasksFragment(TasksFragment.TYPE.ALL));
        mFragments.put(1, new TasksFragment(TasksFragment.TYPE.HOT));
        mFragments.put(2, new TasksFragment(TasksFragment.TYPE.SIMPLE));
        mFragments.put(3, new TasksFragment(TasksFragment.TYPE.EXPENSIVE));
        FragmentsPagerAdapter pagerAdapter = new FragmentsPagerAdapter(getChildFragmentManager(),
                mFragments);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onTabClick(View view, int index) {

    }
}
