package com.ewq.zq.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ewq.tools.log.Logger;
import com.ewq.zq.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

@SuppressLint("RestrictedApi")
public class GoldRefreshHeader extends LinearLayout implements RefreshHeader {

    private static final String TAG = "GoldRefreshHeader";

    private final ImageView headerIcon;
    private final TextView headerText;
    private final AnimationDrawable animation;

    public GoldRefreshHeader(Context context) {
        super(context);
        setGravity(Gravity.CENTER_HORIZONTAL);
        View header = LayoutInflater.from(context).inflate(R.layout.gold_refresh_header, null);
        addView(header);
        headerIcon = header.findViewById(R.id.refresh_header_icon);
        headerText = header.findViewById(R.id.refresh_header_text);
        animation = (AnimationDrawable) headerIcon.getDrawable();
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
        Logger.i(TAG, "onInitialized");
    }

    @Override
    public void onPulling(float percent, int offset, int height, int extendHeight) {
        Logger.i(TAG, "onPulling");
    }

    @Override
    public void onReleasing(float percent, int offset, int height, int extendHeight) {
        Logger.i(TAG, "onReleasing");
    }

    @Override
    public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {
        Logger.i(TAG, "onReleased");
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int extendHeight) {
        Logger.i(TAG, "onStartAnimator");
        animation.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        Logger.i(TAG, "onFinish");
        animation.stop();
        headerText.setText(success ? R.string.refresh_header_finish : R.string.refresh_header_failed);
        return 500;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
        Logger.i(TAG, "onHorizontalDrag");
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        Logger.i(TAG, "onStateChanged: " + newState);
        switch (newState) {
            case None:
            case PullDownToRefresh:
                headerText.setText(R.string.refresh_header_pulling);
                break;
            case Refreshing:
                headerText.setText(R.string.refresh_header_ing);
                break;
            case ReleaseToRefresh:
                headerText.setText(R.string.refresh_header_release);
                break;
        }
    }
}
