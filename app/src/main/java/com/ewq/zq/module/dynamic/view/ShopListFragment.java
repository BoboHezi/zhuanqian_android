package com.ewq.zq.module.dynamic.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.tools.log.Logger;
import com.ewq.zq.R;
import com.ewq.zq.adapter.ShopListAdapter;
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
        View header = LayoutInflater.from(mContext).
                inflate(R.layout.header_shop_list, contentRecyclerView, false);
        ShopListAdapter adapter = new ShopListAdapter(mContext, beans, header);
        contentRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    private ArrayList<ShopBean> getFakeData() {
        Drawable header = getResources().getDrawable(R.mipmap.yd_xo_bag_identify);
        ArrayList<ShopBean> beans = new ArrayList<>(10);
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
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
