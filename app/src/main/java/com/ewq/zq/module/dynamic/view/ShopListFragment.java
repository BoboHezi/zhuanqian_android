package com.ewq.zq.module.dynamic.view;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.tools.log.Logger;
import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.module.dynamic.model.ShopBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class ShopListFragment extends BaseFragment {

    private static final String TAG = "ShopListFragment";
    @BindView(R.id.recy_view)
    RecyclerView contentRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private TYPE mType;
    private boolean refreshLayoutInitFlag;

    private ArrayList<ShopBean> beans;

    public ShopListFragment(TYPE type) {
        super();
        mType = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shops;
    }

    @Override
    protected void initView() {
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,
                RecyclerView.VERTICAL, false));
        beans = getFakeData();
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    private ArrayList<ShopBean> getFakeData() {
        ArrayList<ShopBean> beans = new ArrayList<>(10);
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        beans.add(new ShopBean());
        return beans;
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
        }
    }

    enum TYPE {
        FOLLOW, RECOMMEND
    }
}