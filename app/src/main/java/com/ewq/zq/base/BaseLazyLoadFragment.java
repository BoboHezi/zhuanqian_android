/*
 * Fragment的生命周期
 * onAttach                 当Activity与Fragment发生关联时调用
 * onCreate
 * setUserVisibleHint
 * onCreateView             创建该Fragment的视图
 * onViewCreated
 * onActivityCreated        当Activity的onCreate()；方法返回时调用
 * onViewStateRestored
 * onStart
 * onResume
 * onPause
 * onSaveInstanceState
 * onStop
 * onDestroyView            与onCreateView相对应，当该Fragment被移除时调用
 * onDestroy
 * onDetach                 与onAttach()相对应，当Fragment与Activity的关联被取消时调用
 *
 * http://www.jianshu.com/p/662c46cd3b5f
 *
 */
package com.ewq.zq.base;

import android.os.Bundle;

/**
 * @author eli
 */
public abstract class BaseLazyLoadFragment extends BaseFragment {

    // DATA初始化是否完成
    public boolean isDataInitiated;
    // UI初始化是否完成
    private boolean isViewInitiated;
    // 当前UI是否可见
    private boolean isVisibleToUser;
    // 是否stop
    private boolean isStop;

    // 获取数据方法
    public abstract void fetchData();

    // 重新stop返回之后的执行方法
    public abstract void resetData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 当前UI是否可见
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // UI初始化完成
        isViewInitiated = true;
        prepareFetchData();
    }

    /**
     * 准备获取数据
     */
    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /**
     * 准备获取数据
     *
     * @param forceUpdate 是否强制性更新
     */
    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isStop = false;
            isDataInitiated = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isStop) {
            isStop = false;
            resetData();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        isStop = true;
    }
}
