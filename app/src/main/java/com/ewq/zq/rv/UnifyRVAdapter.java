package com.ewq.zq.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.rv.base.RVBaseCell;

import java.util.List;

public class UnifyRVAdapter<C extends RVBaseCell> extends RecyclerView.Adapter {

    private static final String TAG = "UnifyRVAdapter";

    private final List<C> mDateSets;

    public UnifyRVAdapter(List<C> dateSets) {
        mDateSets = dateSets;
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
    public int getItemViewType(int position) {
        return mDateSets.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mDateSets != null ? mDateSets.size() : 0;
    }
}
