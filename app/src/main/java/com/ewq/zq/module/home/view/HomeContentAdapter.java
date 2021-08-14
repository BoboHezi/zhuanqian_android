package com.ewq.zq.module.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TaskHomeBean;

import java.util.ArrayList;

public class HomeContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HomeContentAdapter";

    private final Context mContext;

    private final ArrayList<View> mHeadViews;

    private final ArrayList<TaskHomeBean> mTaskBeans;

    public HomeContentAdapter(Context context, ArrayList<TaskHomeBean> beans) {
        mContext = context;
        mTaskBeans = beans;
        mHeadViews = new ArrayList<>();
    }

    public void addHeader(View header) {
        if (header != null) {
            mHeadViews.add(header);
            notifyItemInserted(mHeadViews.size() - 1);
        }
    }

    public void removeHeader(int position) {
        if (position >= 0 && position < mHeadViews.size()) {
            mHeadViews.remove(position);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // position as TYPE
        if (viewType < mHeadViews.size()) {
            return new ViewHolderHeader(mHeadViews.get(viewType));
        }
        return new ViewHolderItem(LayoutInflater.from(mContext).
                inflate(R.layout.holder_task_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // position as TYPE
        if (getItemViewType(position) < mHeadViews.size()) {
            return;
        }
        final int realPosition = getRealPosition(holder);
        final TaskHomeBean bean = mTaskBeans == null ? null : mTaskBeans.get(realPosition);
        if (bean != null) {

        }
    }

    @Override
    public int getItemViewType(int position) {
        // position as TYPE
        return position;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        // sub heads number
        return position - mHeadViews.size();
    }

    @Override
    public int getItemCount() {
        // plus heads number
        return mHeadViews.size() + (mTaskBeans != null ? mTaskBeans.size() : 0);
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {

        public ViewHolderHeader(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
        }
    }
}
