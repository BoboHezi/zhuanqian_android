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

public class ShopCell extends RVBaseCell<ShopBean> {

    public ShopCell(ShopBean shopBean) {
        super(shopBean);
    }

    @Override
    public int getItemType() {
        return CellType.TYPE_SHOP.getValue();
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopViewHolder(getView(parent));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShopViewHolder shopHolder = (ShopViewHolder) holder;
        shopHolder.userHeader.setImageDrawable(mData.getIcon());
        shopHolder.userName.setText(mData.getName());
        shopHolder.userId.setText(mData.getId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_dynamic_item;
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {
        ImageView userHeader;
        TextView userName;
        TextView userId;
        TextView userTime;
        TextView btnFollow;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            userHeader = itemView.findViewById(R.id.img_user_header);
            userName = itemView.findViewById(R.id.tv_user_name);
            userId = itemView.findViewById(R.id.tv_user_id);
            userTime = itemView.findViewById(R.id.tv_user_time);
            btnFollow = itemView.findViewById(R.id.btn_follow);
        }
    }
}
