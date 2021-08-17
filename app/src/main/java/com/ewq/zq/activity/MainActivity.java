package com.ewq.zq.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ewq.zq.R;
import com.ewq.zq.adapter.FragmentsPagerAdapter;
import com.ewq.zq.base.BaseActivity;
import com.ewq.zq.module.discovery.view.DiscoveryFragment;
import com.ewq.zq.module.dynamic.view.DynamicFragment;
import com.ewq.zq.module.home.view.HomeFragment;
import com.ewq.zq.module.lobby.view.LobbyFragment;
import com.ewq.zq.module.my.view.MyFragment;
import com.ewq.zq.views.InviteDialog;
import com.ewq.zq.widget.NavigationLayout;
import com.leaf.library.StatusBarUtil;

import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends BaseActivity implements NavigationLayout.OnTabClickListener {

    private final ConcurrentHashMap<Integer, Fragment> mFragments = new ConcurrentHashMap<>();
    @BindView(R.id.main_pager)
    ViewPager mViewPager;
    @BindView(R.id.nav_tab)
    NavigationLayout mNavigation;
    @BindView(R.id.ll_order)
    View floatBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mNavigation.setTabClickListener(this);
        updateNaviView(mNavigation, 0);
        mNavigation.attachViewPager(mViewPager);
        StatusBarUtil.setTransparentForWindow(this);
        mViewPager.setSaveEnabled(false);

        new InviteDialog(this).show();
        floatBtn.setOnClickListener(v -> startActivity(new Intent(this, PendingActivity.class)));
    }

    @Override
    protected void loadData() {
        mFragments.put(0, HomeFragment.getInstance());
        mFragments.put(1, LobbyFragment.getInstance());
        mFragments.put(2, DynamicFragment.getInstance());
        mFragments.put(3, DiscoveryFragment.getInstance());
        mFragments.put(4, MyFragment.getInstance());
        FragmentsPagerAdapter pagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager(),
                mFragments);
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
}