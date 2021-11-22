package com.ewq.zq.module.home.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TileBean;
import com.ewq.zq.rv.cell.QuickTileCell;
import com.ewq.zq.rv.UnifyRVAdapter;
import com.ewq.zq.rv.base.RVBaseCell;

import java.util.ArrayList;
import java.util.List;

public class QuickTileGroup extends FrameLayout {
    private final RecyclerView tilesRecyclerView;
    private final List<RVBaseCell> tiles;

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
        tiles = getFakeData();
        UnifyRVAdapter adapter = new UnifyRVAdapter(tiles);
        tilesRecyclerView.setAdapter(adapter);
    }

    private List<RVBaseCell> getFakeData() {
        Resources res = getResources();
        List<RVBaseCell> tasks = new ArrayList<>();
        tasks.add(new QuickTileCell(new TileBean(res.getDrawable(R.mipmap.yd_xo_union_finish),
                "每日抽奖", "com.ewq.zq/com.ewq.zq.activity.LotteryActivity")));
        tasks.add(new QuickTileCell(new TileBean(res.getDrawable(R.mipmap.yd_xo_union_money),
                "热门", "")));
        tasks.add(new QuickTileCell(new TileBean(res.getDrawable(R.mipmap.yd_xo_system_note),
                "1小时审核", "")));
        tasks.add(new QuickTileCell(new TileBean(res.getDrawable(R.mipmap.yd_xo_union_no_money),
                "推广赚钱", "")));
        tasks.add(new QuickTileCell(new TileBean(res.getDrawable(R.mipmap.yd_xo_red_package),
                "签到红包", "")));
        tasks.add(new QuickTileCell(new TileBean(res.getDrawable(R.mipmap.yd_xo_sign_flag),
                "每日打卡", "")));
        tasks.add(new QuickTileCell(new TileBean(res.getDrawable(R.mipmap.yd_xo_coupon_refresh),
                "悬赏红包", "")));
        return tasks;
    }
}
