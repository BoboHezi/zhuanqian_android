package com.ewq.zq.module.home.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ewq.zq.R;
import com.ewq.zq.module.home.model.TaskHomeBean;
import com.ewq.zq.rv.QuickTaskCell;
import com.ewq.zq.rv.UnifyRVAdapter;
import com.ewq.zq.rv.base.RVBaseCell;

import java.util.ArrayList;
import java.util.List;

public class QuickTaskGroup extends FrameLayout {

    private final RecyclerView tilesRecyclerView;

    private final List<RVBaseCell> tasks;

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
        tilesRecyclerView.addItemDecoration(new FourSpacesItemDecoration(10, 20, 2));
        tasks = getFakeData();
        UnifyRVAdapter adapter = new UnifyRVAdapter(tasks);
        tilesRecyclerView.setAdapter(adapter);
    }

    private List<RVBaseCell> getFakeData() {
        String title = "H微信白给E";
        SpannableString titleSpan = new SpannableString(title);
        Drawable d = getResources().getDrawable(R.mipmap.yd_xo_withdrawal_wx);
        d.setBounds(0, 0, 60, 60);
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        titleSpan.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        d = getResources().getDrawable(R.mipmap.yd_xo_reply);
        d.setBounds(0, 0, 40, 40);
        span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        titleSpan.setSpan(span, 5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        String subTitle = "H试玩|笔趣阁\nH 4633人已赚\nH +9.28元";
        SpannableString subSpan = new SpannableString(subTitle);
        d = getResources().getDrawable(R.mipmap.yd_xo_top);
        d.setBounds(0, 0, 40, 40);
        span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        subSpan.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        d = getResources().getDrawable(R.mipmap.yd_xo_withdraw_margin_icon);
        d.setBounds(0, 0, 40, 40);
        span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        subSpan.setSpan(span, 8, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        d = getResources().getDrawable(R.mipmap.yd_xo_no1);
        d.setBounds(0, 0, 40, 40);
        span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        subSpan.setSpan(span, 18, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        List<RVBaseCell> tasks = new ArrayList<>();
        tasks.add(new QuickTaskCell(new TaskHomeBean(titleSpan, subSpan, 30f)));
        tasks.add(new QuickTaskCell(new TaskHomeBean(titleSpan, subSpan, 31f)));
        tasks.add(new QuickTaskCell(new TaskHomeBean(titleSpan, subSpan, 32f)));
        tasks.add(new QuickTaskCell(new TaskHomeBean(titleSpan, subSpan, 33f)));
        tasks.add(new QuickTaskCell(new TaskHomeBean(titleSpan, subSpan, 34f)));
        tasks.add(new QuickTaskCell(new TaskHomeBean(titleSpan, subSpan, 35f)));
        return tasks;
    }

    class FourSpacesItemDecoration extends RecyclerView.ItemDecoration {
        private final int horizontalSpace;
        private final int verticalSpace;
        private final int mSpan;

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
