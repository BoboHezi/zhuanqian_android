package com.ewq.zq.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.ewq.zq.R;

public class InviteDialog extends Dialog {

    public InviteDialog(@NonNull Context context) {
        this(context, R.style.MyDialogStyle);
    }

    public InviteDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_invite_tip);
        getWindow().getAttributes().gravity = Gravity.CENTER;

        findViewById(R.id.img_check).setOnClickListener(v -> v.setSelected(!v.isSelected()));
        findViewById(R.id.btn_close).setOnClickListener(v -> dismiss());
    }
}
