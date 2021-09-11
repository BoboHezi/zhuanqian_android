package com.ewq.zq.rv;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TaskHomeBean;
import com.ewq.zq.rv.base.RVBaseCell;

public class TaskCell extends RVBaseCell<TaskHomeBean> {

    public TaskCell(TaskHomeBean taskHomeBean) {
        super(taskHomeBean);
    }

    @Override
    public int getItemType() {
        return CellType.TYPE_TASK.getValue();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(getView(parent));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskViewHolder taskHolder = (TaskViewHolder) holder;
        taskHolder.title.setText(mData.getTitle());
        taskHolder.subTitle.setText(mData.getSubTitle());
        taskHolder.money.setText("+" + mData.getMoney() + "元");
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_task_home;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subTitle;
        TextView money;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.label);
            money = itemView.findViewById(R.id.money);
        }
    }
}
