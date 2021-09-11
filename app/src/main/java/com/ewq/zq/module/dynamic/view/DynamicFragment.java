package com.ewq.zq.module.dynamic.view;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ewq.tools.log.Logger;
import com.ewq.zq.R;
import com.ewq.zq.adapter.FragmentsPagerAdapter;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.widget.NavigationLayout;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;

public class DynamicFragment extends BaseFragment implements NavigationLayout.OnTabClickListener {

    private static final String TAG = "DynamicFragment";
    private static DynamicFragment dynamicFragment;
    private final HashMap<Integer, Rect> tabReacts = new HashMap<>();
    @BindView(R.id.main_pager)
    ViewPager mViewPager;
    @BindView(R.id.header_nav)
    NavigationLayout mNavigation;
    @BindView(R.id.nav_block)
    FrameLayout slideBlock;
    private boolean navChildInitFlag;
    private final ConcurrentHashMap<Integer, Fragment> mFragments = new ConcurrentHashMap<>();

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
        mNavigation.setTabClickListener(this);
        mNavigation.attachViewPager(mViewPager);
        mViewPager.setSaveEnabled(false);
        initTabPosition();
    }

    private void initTabPosition() {
        if (!navChildInitFlag) {
            for (int i = 0; i < mNavigation.getChildCount(); i++) {
                View child = mNavigation.getChildAt(i);
                int l = child.getLeft();
                int t = child.getTop();
                int r = child.getRight();
                int b = child.getBottom();
                if (l >= 0 && r > l && t >= 0 && b > t) {
                    navChildInitFlag = true;
                    Rect rect = new Rect(l, t, r, b);
                    tabReacts.put(i, rect);
                } else {
                    navChildInitFlag = false;
                    break;
                }
            }
        }

        if (!navChildInitFlag) {
            slideBlock.postDelayed(() -> initTabPosition(), 500);
        } else {
            slideBlock.setX(calcSlideX(0));
            Rect rect = tabReacts.get(0);
            RelativeLayout.LayoutParams lps = (RelativeLayout.LayoutParams)
                    slideBlock.getLayoutParams();
            lps.width = rect.right - rect.left - 2 * 40;
            slideBlock.setLayoutParams(lps);
        }
    }

    private int calcSlideX(int select) {
        if (navChildInitFlag) {
            final int offset = 40;
            return tabReacts.get(select).left + offset;
        }
        return 0;
    }

    private void slideBlock(int from, int to, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(slideBlock, "translationX",
                from, to);
        animator.setDuration(duration);
        animator.start();
    }

    @Override
    protected void initData(Bundle arguments) {
        mFragments.put(0, new ShopListFragment(ShopListFragment.TYPE.FOLLOW));
        mFragments.put(1, new ShopListFragment(ShopListFragment.TYPE.RECOMMEND));
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
        int slideBeforeX = (int) slideBlock.getX();
        int slideAfterX = calcSlideX(index);
        Logger.i(TAG, "beforeX: " + slideBeforeX + ", afterX: " + slideAfterX);
        if (slideAfterX > 0 && slideBeforeX > 0) {
            slideBlock(slideBeforeX, slideAfterX, 150);
        }
    }
}
