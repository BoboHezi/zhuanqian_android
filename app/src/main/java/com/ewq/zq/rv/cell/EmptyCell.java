package com.ewq.zq.rv.cell;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.rv.base.RVBaseCell;
import com.ewq.zq.rv.base.RVBaseViewHolder;

public class EmptyCell extends RVBaseCell {
    public EmptyCell() {
        super(null);
    }

    @Override
    public int getItemType() {
        return CellType.TYPE_EMPTY.getValue();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(getView(parent));
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_empty;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }
}
