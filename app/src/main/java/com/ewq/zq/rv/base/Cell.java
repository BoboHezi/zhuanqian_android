package com.ewq.zq.rv.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * created by eli
 */
public interface Cell {

    /**
     * 释放资源
     */
    void releaseResource();

    /**
     * 获取viewType
     *
     * @return
     */
    int getItemType();

    /**
     * 创建viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 获取布局ID
     *
     * @return
     */
    int getLayoutId();

    /**
     * 创建View
     *
     * @param parent
     * @return
     */
    View getView(ViewGroup parent);

    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);
}
