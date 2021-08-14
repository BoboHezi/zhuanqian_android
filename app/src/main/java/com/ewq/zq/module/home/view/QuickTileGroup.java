package com.ewq.zq.module.home.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TileBean;

import java.util.ArrayList;
import java.util.List;

public class QuickTileGroup extends FrameLayout {
    private final RecyclerView tilesRecyclerView;
    private final List<TileBean> tiles;

    public QuickTileGroup(Context context) {
        this(context, null);
    }

    public QuickTileGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View child = LayoutInflater.from(context).inflate(R.layout.group_quick_tiles,
                this, false);
        addView(child);

        tilesRecyclerView = child.findViewById(R.id.tiles_recycler);

        tilesRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        tilesRecyclerView.setAdapter(new TileAdapter());

        tiles = new ArrayList<>();
        Resources res = getResources();
        tiles.add(new TileBean(res.getDrawable(R.mipmap.yd_xo_union_finish),
                "每日抽奖", ""));
        tiles.add(new TileBean(res.getDrawable(R.mipmap.yd_xo_union_money),
                "热门", ""));
        tiles.add(new TileBean(res.getDrawable(R.mipmap.yd_xo_system_note),
                "1小时审核", ""));
        tiles.add(new TileBean(res.getDrawable(R.mipmap.yd_xo_union_no_money),
                "推广赚钱", ""));
        tiles.add(new TileBean(res.getDrawable(R.mipmap.yd_xo_red_package),
                "签到红包", ""));
        tiles.add(new TileBean(res.getDrawable(R.mipmap.yd_xo_sign_flag),
                "每日打卡", ""));
        tiles.add(new TileBean(res.getDrawable(R.mipmap.yd_xo_coupon_refresh),
                "悬赏红包", ""));
        tilesRecyclerView.getAdapter().notifyDataSetChanged();
    }

    class TileAdapter extends RecyclerView.Adapter<TileAdapter.TileHolder> {

        @NonNull
        @Override
        public TileAdapter.TileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TileAdapter.TileHolder(LayoutInflater.from(getContext()).inflate(R.layout.holder_tile, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TileAdapter.TileHolder holder, int position) {
            TileBean bean = tiles.get(position);
            holder.icon.setImageDrawable(bean.getIcon());
            holder.title.setText(bean.getTitle());
        }

        @Override
        public int getItemCount() {
            return tiles != null ? tiles.size() : 0;
        }

        class TileHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView title;

            public TileHolder(@NonNull View itemView) {
                super(itemView);

                icon = itemView.findViewById(R.id.icon);
                title = itemView.findViewById(R.id.title);
            }
        }
    }
}
