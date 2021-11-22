package com.ewq.zq.rv.cell;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TileBean;
import com.ewq.zq.rv.base.RVBaseCell;

import eli.avocado.utils.StringUtils;

public class QuickTileCell extends RVBaseCell<TileBean> {

    public QuickTileCell(TileBean tileBean) {
        super(tileBean);
    }

    @Override
    public int getItemType() {
        return CellType.TYPE_TILE_QUICK.getValue();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TileHolder(getView(parent));
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_quick_tile;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TileHolder tileHolder = (TileHolder) holder;
        tileHolder.icon.setImageDrawable(mData.getIcon());
        tileHolder.title.setText(mData.getTitle());
        if (!StringUtils.isEmpty(mData.getAction())) {
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName(mData.getAction().split("/")[0],
                        mData.getAction().split("/")[1]);
                intent.setComponent(cn);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            });
        }
    }

    public static class TileHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        public TileHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }
    }
}
