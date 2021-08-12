package com.ewq.network.manager;

import com.ewq.network.entity.HttpResult;
import com.ewq.tools.info.SharedInfo;
import com.ewq.tools.utils.ToastUtil;

/**
 * Description: 异常处理
 */
@SuppressWarnings("unchecked")
public final class ExceptionHandling {
    public static boolean isToast = false;

    static void operate(final HttpResult result) {
        if (!result.getCode().equals("0000")) {
            ToastUtil.toast(result.getMsg());
            if (result.getCode().equals("0001")) {
                SharedInfo.getInstance().saveValue("userId", "");
            }
        }
    }
}
