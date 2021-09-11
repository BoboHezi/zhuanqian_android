package com.ewq.zq.rv;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.dynamic.model.ShopBean;
import com.ewq.zq.rv.base.RVBaseCell;

public class SimpleShopCell extends RVBaseCell<ShopBean> {

    public SimpleShopCell(ShopBean shopBean) {
        super(shopBean);
    }

    @Override
    public int getItemType() {
        return CellType.TYPE_SHOP_SIMPLE.getValue();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleShopViewHolder(getView(parent));
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_simple_shop;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleShopViewHolder shopHolder = (SimpleShopViewHolder) holder;
        shopHolder.icon.setImageDrawable(mData.getIcon());
        shopHolder.title.setText(mData.getName());
    }

    public static class SimpleShopViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        public SimpleShopViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }
    }
}
