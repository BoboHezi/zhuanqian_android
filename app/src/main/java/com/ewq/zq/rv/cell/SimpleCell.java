package com.ewq.zq.rv.cell;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.rv.base.RVBaseCell;

public class SimpleCell extends RVBaseCell<View> {

    public SimpleCell(View v) {
        super(v);
    }

    @Override
    public int getItemType() {
        return CellType.TYPE_SIMPLE.getValue();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(getView(parent));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewGroup parent = (ViewGroup) mData.getParent();
        if (parent != null) {
            parent.removeView(mData);
        }
        ((ViewGroup) holder.itemView).addView(mData);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getView(ViewGroup parent) {
        return new FrameLayout(parent.getContext());
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
