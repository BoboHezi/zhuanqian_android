package com.ewq.zq.module.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.tools.log.Logger;
import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.module.home.model.TaskHomeBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    private static HomeFragment homeFragment;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recy_view)
    RecyclerView contentRecycleView;
    private boolean refreshLayoutInitFlag;
    private boolean contentInitFlag;

    private HomeFragment() {
        super();
    }

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        // init content recycle view
        if (!contentInitFlag && contentRecycleView != null) {
            contentInitFlag = true;
            contentRecycleView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
            ArrayList<TaskHomeBean> beans = new ArrayList<>(10);
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            beans.add(new TaskHomeBean());
            HomeContentAdapter adapter = new HomeContentAdapter(mContext, beans);
            contentRecycleView.setAdapter(adapter);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View header1 = new QuickTaskGroup(mContext);
            View header2 = new QuickTileGroup(mContext);
            View title = inflater.inflate(R.layout.home_header_title, contentRecycleView, false);
            adapter.addHeader(header1);
            adapter.addHeader(header2);
            adapter.addHeader(title);
        }
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {
        // init smart refresh layout
        if (!refreshLayoutInitFlag && refreshLayout != null) {
            refreshLayoutInitFlag = true;
            refreshLayout.setOnRefreshListener(refreshLayout -> {
                Logger.i(TAG, "onRefresh");
                refreshLayout.finishRefresh(3 * 1000, true);
            });
            refreshLayout.setOnLoadMoreListener(refreshLayout -> {
                Logger.i(TAG, "onLoadMore");
                refreshLayout.finishLoadMore(3 * 1000, true, false);
            });
            /*refreshLayout.setEnableLoadMore(true);
            refreshLayout.autoRefresh();*/
        }
    }
}
