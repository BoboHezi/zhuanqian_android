package com.ewq.zq.rv.cell;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TaskHomeBean;
import com.ewq.zq.rv.base.RVBaseCell;

public class QuickTaskCell extends RVBaseCell<TaskHomeBean> {

    public QuickTaskCell(TaskHomeBean taskHomeBean) {
        super(taskHomeBean);
    }

    @Override
    public int getItemType() {
        return CellType.TYPE_TASK_QUICK.getValue();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskHolder(getView(parent));
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_quick_task;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskHolder taskHolder = (TaskHolder) holder;
        taskHolder.title.setText(mData.getTitle());
        taskHolder.subTitle.setText(mData.getSubTitle());
    }

    public static class TaskHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subTitle;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.sub_title);
        }
    }
}
