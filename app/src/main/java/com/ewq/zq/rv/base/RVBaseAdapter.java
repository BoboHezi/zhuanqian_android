package com.ewq.zq.rv.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class RVBaseAdapter<C extends RVBaseCell> extends RecyclerView.Adapter {

    private static final String TAG = "RVBaseAdapter";

    protected final List<C> mDateSets;

    public RVBaseAdapter() {
        mDateSets = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for (int i = 0; i < getItemCount(); i++) {
            RVBaseCell cell = mDateSets.get(i);
            if (viewType == cell.getItemType()) {
                return cell.onCreateViewHolder(parent, viewType);
            }
        }
        throw new RuntimeException("wrong viewType");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mDateSets.get(position).onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDateSets != null ? mDateSets.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mDateSets.get(position).getItemType();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        int position = holder.getAdapterPosition();
        if (position < 0 || position >= mDateSets.size()) {
            return;
        }
        mDateSets.get(position).releaseResource();
    }

    protected abstract void onViewHolderBound(RVBaseViewHolder holder, int position);

    public void add(C cell) {
        mDateSets.add(cell);
        int index = mDateSets.indexOf(cell);
        notifyItemChanged(index);
    }

    public void add(int index, C cell) {
        mDateSets.add(index, cell);
        notifyItemChanged(index);
    }

    public void remove(C cell) {
        int indexOfCell = mDateSets.indexOf(cell);
        remove(indexOfCell);
    }

    public void remove(int index) {
        mDateSets.remove(index);
        notifyItemRemoved(index);
    }

    public void remove(int start, int count) {
        if ((start + count) > mDateSets.size()) {
            return;
        }
        int size = getItemCount();
        for (int i = start; i < size; i++) {
            mDateSets.remove(i);
        }

        notifyItemRangeRemoved(start, count);
    }

    public void addAll(List<C> cells) {
        if (cells == null || cells.size() == 0) {
            return;
        }
        mDateSets.addAll(cells);
        notifyItemRangeChanged(mDateSets.size() - cells.size(), mDateSets.size());
    }

    public void addAll(int index, List<C> cells) {
        if (cells == null || cells.size() == 0) {
            return;
        }
        mDateSets.addAll(index, cells);
        notifyItemRangeChanged(index, index + cells.size());
    }

    public void clear() {
        mDateSets.clear();
        notifyDataSetChanged();
    }

    public List<C> getData() {
        return mDateSets;
    }

    public void setData(List<C> data) {
        addAll(data);
        notifyDataSetChanged();
    }
}
