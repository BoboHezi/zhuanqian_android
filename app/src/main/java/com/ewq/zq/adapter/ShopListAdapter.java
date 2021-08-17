package com.ewq.zq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.dynamic.model.ShopBean;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private List<ShopBean> mBeans;

    private Context mContext;

    private View mHeader;

    public ShopListAdapter(Context context, List<ShopBean> beans) {
        this(context, beans, null);
    }

    public ShopListAdapter(Context context, List<ShopBean> beans, View header) {
        mContext = context;
        mBeans = beans;
        mHeader = header;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new ShopHeaderHolder(mHeader);
        }
        return new ShopViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.holder_dynamic_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            return;
        }
        final int realPosition = getRealPosition(holder);
        ShopBean bean = mBeans.get(realPosition);

        ShopViewHolder shopHolder = (ShopViewHolder) holder;
        shopHolder.userHeader.setImageDrawable(bean.getIcon());
        shopHolder.userName.setText(bean.getName());
        shopHolder.userId.setText(bean.getId());
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        // sub heads number
        return position - (mHeader != null ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return mHeader != null && position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return (mHeader != null ? 1 : 0) + (mBeans != null ? mBeans.size() : 0);
    }

    class ShopHeaderHolder extends RecyclerView.ViewHolder {
        public ShopHeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
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
