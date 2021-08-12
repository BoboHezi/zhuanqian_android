package com.ewq.zq;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;

import com.ewq.network.manager.ExceptionHandling;
import com.ewq.tools.common.BaseParams;
import com.ewq.tools.info.SharedInfo;
import com.ewq.tools.log.Logger;
import com.ewq.tools.utils.ActivityManage;
import com.ewq.tools.utils.ContextHolder;

public class ZqApplication extends MultiDexApplication {

    protected static final String TAG = "ZqApplication";
    private static ZqApplication sInstance;
    private static Context mContext;
    // 当前活动的Activity数量
    private int activityCount = 0;

    public static ZqApplication getInstance() {
        return sInstance;
    }

    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ActivityManage.push(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                ActivityManage.setTopActivity(activity);
                if (activityCount++ == 0) {
                    Logger.i(TAG, ">>>>>>>>>>>>>>>>>>> 切到前台 <<<<<<<<<<<<<<<<<<<");
                    ExceptionHandling.isToast = false;
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                activityCount--;
                if (activityCount == 0) {
                    Logger.i(TAG, ">>>>>>>>>>>>>>>>>>> 切到后台 <<<<<<<<<<<<<<<<<<<");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManage.remove(activity);
            }
        });

        sInstance = this;
        mContext = getApplicationContext();

        ContextHolder.init(this);
        // 内存共享对象初始化
        SharedInfo.init(BaseParams.SP_NAME);
    }
}
