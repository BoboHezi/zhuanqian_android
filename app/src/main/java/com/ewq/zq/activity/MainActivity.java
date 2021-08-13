package com.ewq.zq.activity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.viewpager2.widget.ViewPager2;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseActivity;
import com.ewq.zq.widget.NavigationLayout;

import butterknife.BindView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends BaseActivity implements NavigationLayout.OnTabClickListener {

    @BindView(R.id.main_pager)
    ViewPager2 mViewPager;
    @BindView(R.id.nav_tab)
    NavigationLayout mNavigation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mNavigation.setTabClickListener(this);
        updateNaviView(mNavigation, 0);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onTabClick(View view, int index) {
        updateNaviView(mNavigation, index);
    }

    private void updateNaviView(NavigationLayout navigation, int select) {
        for (int i = 0; i < navigation.getChildCount(); i++) {
            ImageView icon = navigation.getChildAt(i).findViewById(R.id.nav_icon);
            TextView text = navigation.getChildAt(i).findViewById(R.id.nav_text);
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