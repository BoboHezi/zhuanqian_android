package com.ewq.zq.activity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseActivity;
import com.ewq.zq.module.discovery.view.DiscoveryFragment;
import com.ewq.zq.module.dynamic.view.DynamicFragment;
import com.ewq.zq.module.home.view.HomeFragment;
import com.ewq.zq.module.lobby.view.LobbyFragment;
import com.ewq.zq.module.my.view.MyFragment;
import com.ewq.zq.widget.NavigationLayout;

import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends BaseActivity implements NavigationLayout.OnTabClickListener {

    @BindView(R.id.main_pager)
    ViewPager mViewPager;
    @BindView(R.id.nav_tab)
    NavigationLayout mNavigation;

    private ConcurrentHashMap<Integer, Fragment> mFragments = new ConcurrentHashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mNavigation.setTabClickListener(this);
        updateNaviView(mNavigation, 0);
        mNavigation.attachViewPager(mViewPager);
    }

    @Override
    protected void loadData() {
        mFragments.put(0, HomeFragment.getInstance());
        mFragments.put(1, LobbyFragment.getInstance());
        mFragments.put(2, DynamicFragment.getInstance());
        mFragments.put(3, DiscoveryFragment.getInstance());
        mFragments.put(4, MyFragment.getInstance());
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onTabClick(View view, int index) {
        updateNaviView(mNavigation, index);
    }

    private void updateNaviView(NavigationLayout navigation, int select) {
        for (int i = 0; i < navigation.getChildCount(); i++) {
            View child = navigation.getChildAt(i);
            ImageView icon = child.findViewById(R.id.nav_icon);
            TextView text = child.findViewById(R.id.nav_text);
            int color = getColor(select == i ? R.color.holo_red_light : R.color.material_grey_850);
            if (icon != null) {
                icon.setImageTintList(ColorStateList.valueOf(color));
            }
            if (text != null) {
                text.setTextColor(color);
            }
        }
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (mFragments.containsKey(position)) {
                fragment = mFragments.get(position);
                return fragment;
            }

            if (fragment != null) {
                mFragments.put(position, fragment);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}