package com.ewq.zq.module.home.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TaskHomeBean;

import java.util.ArrayList;
import java.util.List;

public class QuickTaskGroup extends FrameLayout {

    private final RecyclerView tilesRecyclerView;

    private final List<TaskHomeBean> tasks;

    public QuickTaskGroup(@NonNull Context context) {
        this(context, null);
    }

    public QuickTaskGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View child = LayoutInflater.from(context).inflate(R.layout.group_quick_task,
                this, false);
        addView(child);

        tilesRecyclerView = child.findViewById(R.id.tasks_recycler);
        tilesRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        tilesRecyclerView.setAdapter(new TaskAdapter());
        //tilesRecyclerView.addItemDecoration(new FourSpacesItemDecoration(1, 1, 2));

        tasks = new ArrayList<>();
        tasks.add(new TaskHomeBean());
        tasks.add(new TaskHomeBean());
        tasks.add(new TaskHomeBean());
        tasks.add(new TaskHomeBean());
        tasks.add(new TaskHomeBean());
        tasks.add(new TaskHomeBean());
        tilesRecyclerView.getAdapter().notifyDataSetChanged();
    }

    class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TaskHolder(LayoutInflater.from(getContext())
                    .inflate(R.layout.holder_quick_task, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            /*TaskHomeBean bean = tasks.get(position);
            holder.title.setText(bean.getTitle());
            holder.subTitle.setText(bean.getSubTitle());*/
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        class TaskHolder extends RecyclerView.ViewHolder {

            TextView title;
            TextView subTitle;

            public TaskHolder(@NonNull View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                subTitle = itemView.findViewById(R.id.sub_title);
            }
        }
    }

    class FourSpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int horizontalSpace;
        private int verticalSpace;
        private int mSpan;

        public FourSpacesItemDecoration(int horizontal, int vertical, int span) {
            horizontalSpace = horizontal;
            verticalSpace = vertical;
            mSpan = span;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = verticalSpace / 2;
            outRect.bottom = verticalSpace / 2;
            int position = parent.getChildLayoutPosition(view);
            if (position % mSpan == 0) {
                //第一列
                outRect.left = 0;
                outRect.right = horizontalSpace / 2;
            } else if ((position + 1) % mSpan == 0) {
                //最后一列
                outRect.left = horizontalSpace / 2;
                outRect.right = 0;
            } else {
                //其他列
                outRect.left = horizontalSpace / 2;
                outRect.right = horizontalSpace / 2;
            }
        }
    }
}
