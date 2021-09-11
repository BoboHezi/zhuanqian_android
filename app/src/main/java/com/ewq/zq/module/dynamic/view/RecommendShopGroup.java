package com.ewq.zq.module.dynamic.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.dynamic.model.ShopBean;
import com.ewq.zq.rv.SimpleShopCell;
import com.ewq.zq.rv.UnifyRVAdapter;
import com.ewq.zq.rv.base.RVBaseCell;

import java.util.ArrayList;
import java.util.List;

public class RecommendShopGroup extends FrameLayout {

    private static final String TAG = "RecommendShopGroup";

    private final RecyclerView shopRecyView;

    private final List<RVBaseCell> shops;

    public RecommendShopGroup(@NonNull Context context) {
        this(context, null);
    }

    public RecommendShopGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View child = LayoutInflater.from(context).inflate(R.layout.group_dynamic_header,
                this, false);
        addView(child);

        shopRecyView = child.findViewById(R.id.rcy_shop);
        shopRecyView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        shops = getFakeData();
        UnifyRVAdapter adapter = new UnifyRVAdapter(shops);
        shopRecyView.setAdapter(adapter);
    }

    private List<RVBaseCell> getFakeData() {
        Drawable header = getResources().getDrawable(R.mipmap.yd_xo_bag_identify);
        ArrayList<RVBaseCell> beans = new ArrayList<>();
        beans.add(new SimpleShopCell(new ShopBean(header, "趣步悬赏主", "ID:323341  ", "", false)));
        beans.add(new SimpleShopCell(new ShopBean(header, "趣步悬赏主", "ID:323342  ", "", false)));
        beans.add(new SimpleShopCell(new ShopBean(header, "趣步悬赏主", "ID:323343  ", "", false)));
        beans.add(new SimpleShopCell(new ShopBean(header, "趣步悬赏主", "ID:323344  ", "", false)));
        beans.add(new SimpleShopCell(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false)));
        beans.add(new SimpleShopCell(new ShopBean(header, "趣步悬赏主", "ID:323346  ", "", false)));
        return beans;
    }
}
