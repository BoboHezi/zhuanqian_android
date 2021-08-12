package com.ewq.zq.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.ewq.zq.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author eli
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected BaseActivity mActivity;
    protected LayoutInflater mInflater;
    protected boolean mIsOnCreateFinish;
    private boolean mStateSaved;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
        setContentView(getLayoutId());
        mContext = getApplicationContext();
        mActivity = this;
        mInflater = getLayoutInflater();
        unbinder = ButterKnife.bind(this);
        mStateSaved = false;
        initView();
        loadData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void loadData();

    protected void initBefore() {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mIsOnCreateFinish = true;
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.left_view);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_view, R.anim.start_view);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

        if (intent == null) {
            intent = new Intent();
        }

        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.right_view, R.anim.start_view);
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mStateSaved = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mStateSaved = false;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStateSaved = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStateSaved = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!mStateSaved) {
            return super.onKeyDown(keyCode, event);
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (!mStateSaved) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind(); //解绑ButterKnife
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }
}
