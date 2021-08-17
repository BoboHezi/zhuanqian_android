package com.ewq.zq.module.dynamic.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
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
import com.ewq.zq.module.dynamic.model.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class RecommendShopGroup extends FrameLayout {

    private static final String TAG = "RecommendShopGroup";

    private final RecyclerView shopRecyView;

    private final List<ShopBean> shops;

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
        shopRecyView.setAdapter(new SimpleShopAdapter());
    }

    private List<ShopBean> getFakeData() {
        Drawable header = getResources().getDrawable(R.mipmap.yd_xo_bag_identify);
        ArrayList<ShopBean> beans = new ArrayList<>();
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        beans.add(new ShopBean(header, "趣步悬赏主", "ID:323345  ", "", false));
        return beans;
    }

    class SimpleShopAdapter extends RecyclerView.Adapter<SimpleShopAdapter.SimpleShopHolder> {

        @NonNull
        @Override
        public SimpleShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SimpleShopHolder(LayoutInflater.from(getContext())
                    .inflate(R.layout.holder_simple_shop, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SimpleShopHolder holder, int position) {
            ShopBean bean = shops.get(position);
            holder.icon.setImageDrawable(bean.getIcon());
            holder.title.setText(bean.getName());
        }

        @Override
        public int getItemCount() {
            Log.i(TAG, "getItemCount: " + shops.size());
            return shops.size();
        }

        class SimpleShopHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView title;

            public SimpleShopHolder(@NonNull View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.icon);
                title = itemView.findViewById(R.id.title);
            }
        }
    }
}
