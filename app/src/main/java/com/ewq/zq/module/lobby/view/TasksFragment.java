package com.ewq.zq.module.lobby.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.tools.log.Logger;
import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;
import com.ewq.zq.module.home.model.TaskHomeBean;
import com.ewq.zq.rv.cell.TaskCell;
import com.ewq.zq.rv.UnifyRVAdapter;
import com.ewq.zq.rv.base.RVBaseCell;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class TasksFragment extends BaseFragment {

    private static final String TAG = "TasksFragment";

    @BindView(R.id.tv_filter_1)
    TextView filter1;
    @BindView(R.id.recy_view)
    RecyclerView contentRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private boolean refreshLayoutInitFlag;

    private ArrayList<RVBaseCell> beans;
    private final TYPE mType;

    public TasksFragment(TYPE type) {
        super();
        mType = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task_list;
    }

    @Override
    protected void initView() {
        filter1.setSelected(true);
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
        ArrayList<RVBaseCell> beans = new ArrayList<>(10);
        beans.add(new TaskCell(new TaskHomeBean()));
        beans.add(new TaskCell(new TaskHomeBean()));
        beans.add(new TaskCell(new TaskHomeBean()));
        beans.add(new TaskCell(new TaskHomeBean()));
        beans.add(new TaskCell(new TaskHomeBean()));
        beans.add(new TaskCell(new TaskHomeBean()));
        beans.add(new TaskCell(new TaskHomeBean()));
        beans.add(new TaskCell(new TaskHomeBean()));
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
            /*refreshLayout.setEnableLoadMore(true);
            refreshLayout.autoRefresh();*/
        }
    }

    enum TYPE {
        ALL, HOT, SIMPLE, EXPENSIVE
    }
}
