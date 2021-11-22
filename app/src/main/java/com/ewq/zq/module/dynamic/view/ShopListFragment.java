package com.ewq.zq.module.dynamic.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.tools.log.Logger;
import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.module.dynamic.model.ShopBean;
import com.ewq.zq.rv.cell.ShopCell;
import com.ewq.zq.rv.cell.SimpleCell;
import com.ewq.zq.rv.UnifyRVAdapter;
import com.ewq.zq.rv.base.RVBaseCell;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class ShopListFragment extends BaseFragment {

    private static final String TAG = "ShopListFragment";
    @BindView(R.id.recy_view)
    RecyclerView contentRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private final TYPE mType;
    private boolean refreshLayoutInitFlag;

    private ArrayList<RVBaseCell> beans;

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
        UnifyRVAdapter adapter = new UnifyRVAdapter(beans);
        contentRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    private ArrayList<RVBaseCell> getFakeData() {
        Drawable header = getResources().getDrawable(R.mipmap.yd_xo_bag_identify);
        ArrayList<RVBaseCell> dates = new ArrayList<>();
        View titleView = LayoutInflater.from(mContext).
                inflate(R.layout.header_shop_list, contentRecyclerView, false);
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) titleView.getLayoutParams();
        lp.width = 720;
        titleView.setLayoutParams(lp);
        dates.add(new SimpleCell(titleView));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323341  ", "", false)));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323342  ", "", false)));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323343  ", "", false)));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323344  ", "", false)));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false)));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323346  ", "", false)));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323347  ", "", false)));
        dates.add(new ShopCell(new ShopBean(header, "趣步悬赏主", "ID:323348  ", "", false)));
        return dates;
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
