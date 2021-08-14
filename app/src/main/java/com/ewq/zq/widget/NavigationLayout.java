package com.ewq.zq.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.ewq.tools.common.Constant;
import com.ewq.tools.info.SharedInfo;
import com.ewq.zq.R;

import eli.avocado.utils.StringUtils;

public class NavigationLayout extends LinearLayout implements View.OnClickListener, ViewPager.OnPageChangeListener, View.OnLongClickListener, View.OnTouchListener {

    private ViewPager mViewPager;
    private OnTabClickListener tabClick;

    private final GestureDetector gestureDetector;

    private final Context context;

    public NavigationLayout(Context context) {
        this(context, null);
    }

    public NavigationLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        gestureDetector = new GestureDetector(context, new QYGestureDetector());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setOnClickListener(this);
            child.setOnTouchListener((v, event) -> {
                if (gestureDetector != null) {
                    gestureDetector.onTouchEvent(event);
                }
                return false;
            });
        }
    }

    public void setTabClickListener(OnTabClickListener listener) {
        tabClick = listener;
    }

    @Override
    public void onClick(View v) {
        if (shake(v)) {
            return;
        }
        int index = indexOfChild(v);
        if (context != null && StringUtils.isEmpty((String) SharedInfo.getInstance()
                .getValue(Constant.userId, ""))) {
            // AppManager.getInstance().jumpActivity(context, LoginCodeActivity.class);
            // return;
        }

        if (mViewPager != null) {
            mViewPager.setCurrentItem(index, true);
        }

        if (tabClick != null) {
            tabClick.onTabClick(v, index);
        }
    }

    public void attachViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
        select(viewPager.getCurrentItem());
    }

    public void locateTo(int position) {
        if (position >= 0 && position < getChildCount()) {
            View selected = getChildAt(position);
            if (selected != null) {
                selected.performClick();
            }
        }
    }

    /**
     * 选中tab
     */
    public void select(int index) {
        View selectView = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setSelected(index == i);
            if (index == i) {
                selectView = child;
            }
        }
        if (tabClick != null) {
            tabClick.onTabClick(selectView, index);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        select(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return false;
    }

    /**
     * 防止重复点击 1秒 内允许点击一次
     *
     * @param view view
     * @return boolean
     */
    private boolean shake(View view) {
        Object lastTime = view.getTag(R.id.view_shake);
        if (lastTime != null) {
            long dx = System.currentTimeMillis() - (long) lastTime;
            if (dx < 500) {
                return true;
            } else {
                view.setTag(R.id.view_shake, System.currentTimeMillis());
                return false;
            }
        } else {
            view.setTag(R.id.view_shake, System.currentTimeMillis());
            return false;
        }
    }

    public interface OnTabClickListener {
        void onTabClick(View view, int index);
    }

    class QYGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }
    }
}
