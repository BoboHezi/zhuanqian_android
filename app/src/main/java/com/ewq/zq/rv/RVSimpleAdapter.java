package com.ewq.zq.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ewq.zq.rv.base.Cell;
import com.ewq.zq.rv.base.RVBaseAdapter;
import com.ewq.zq.rv.base.RVBaseViewHolder;
import com.ewq.zq.rv.cell.CellType;

public class RVSimpleAdapter extends RVBaseAdapter {

    public RVSimpleAdapter() {

    }

    @Override
    protected void onViewHolderBound(RVBaseViewHolder holder, int position) {

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        if (lm instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) lm;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    return CellType.isFixedType(viewType) ? gridLayoutManager.getSpanCount() : 0;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        int position = holder.getAdapterPosition();
        int viewType = getItemViewType(position);

        if (isStaggeredGridLayout(holder) && CellType.isFixedType(viewType)) {
            StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams)
                    holder.itemView.getLayoutParams();
            lp.setFullSpan(true);
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }
}
