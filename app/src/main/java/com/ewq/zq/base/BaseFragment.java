package com.ewq.zq.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ewq.zq.R;

import butterknife.ButterKnife;


/**
 * @author eli
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected butterknife.Unbinder unbinder;
    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity().getApplicationContext();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        unbinder = ButterKnife.bind(this, mRootView);
        initBefore();
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData(getArguments());
        setListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData(Bundle arguments);

    protected abstract void setListener();

    protected void initBefore() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (getActivity() != null) {
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.right_view, R.anim.start_view);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int code) {
        if (intent == null) {
            intent = new Intent();
        }

        if (getActivity() != null) {
            getActivity().startActivityForResult(intent, code);
            getActivity().overridePendingTransition(R.anim.right_view, R.anim.start_view);
        }
    }
}
