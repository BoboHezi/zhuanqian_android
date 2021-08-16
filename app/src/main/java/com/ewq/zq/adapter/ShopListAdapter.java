package com.ewq.zq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.dynamic.model.ShopBean;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopViewHolder> {

    private List<ShopBean> mBeans;

    private Context mContext;

    public ShopListAdapter(Context context, List<ShopBean> beans) {
        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.holder_quick_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBeans != null ? mBeans.size() : 0;
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
