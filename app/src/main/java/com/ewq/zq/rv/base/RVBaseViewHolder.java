package com.ewq.zq.rv.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVBaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    private View mItemView;

    public RVBaseViewHolder(@NonNull View itemView) {
        super(itemView);

        mItemView = itemView;
        mViews = new SparseArray<>();
    }

    public View getItemView() {
        return mItemView;
    }

    public View getView(int resId) {
        return retrieveView(resId);
    }

    public TextView getTextView(int resId) {
        return retrieveView(resId);
    }

    public Button getButton(int resId) {
        return retrieveView(resId);
    }

    public ImageView getImageView(int resId) {
        return retrieveView(resId);
    }

    public void setText(int resId, CharSequence text) {
        TextView textView = getTextView(resId);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setText(int resId, int strId) {
        TextView textView = getTextView(resId);
        if (textView != null) {
            textView.setText(strId);
        }
    }

    protected <V extends View> V retrieveView(int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = mItemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (V) view;
    }
}
