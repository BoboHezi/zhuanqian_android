package com.ewq.network.manager;


import com.ewq.network.R;
import com.ewq.network.exception.ApiException;
import com.ewq.tools.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description: 网络请求回调封装类
 */
public abstract class RequestCallBack<T> implements Callback<T> {
    private SmartRefreshLayout swipeLayout;

    public RequestCallBack() {
    }

    public RequestCallBack(SmartRefreshLayout swipeLayout) {
        this.swipeLayout = swipeLayout;
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        NetworkUtil.dismissCutscenes();
        if (swipeLayout != null && swipeLayout.isRefreshing()) {
            swipeLayout.finishRefresh();
        }
        if (swipeLayout != null && swipeLayout.isEnableLoadMore()) {
            swipeLayout.finishLoadMore();
        }
        if (response.isSuccessful() && response.body() != null) {
            onSuccess(call, response);
        } else {
            onFailed(call, response);
        }
    }

    public void onFailed(Call<T> call, Response<T> response) {
        if (response.code() == AppResultCode.HTTP_NET_EXCEPTION) { //400
            ToastUtil.toast(R.string.app_network_http_exception);
        } else if (response.code() == AppResultCode.HTTP_NET_TIME_OUT) { //408
            ToastUtil.toast(R.string.app_network_socket_timeout);
        } else if (response.code() == AppResultCode.HTTP_NO_NET_EXCEPTION) { //1003
            ToastUtil.toast(R.string.app_network_error);
        } else if (response.code() == AppResultCode.HTTP_NET_HTTP_ERROR) { //500
            ToastUtil.toast(R.string.app_network_http_error);
        } else if (response.code() == AppResultCode.HTTP_NET_HTTP_NO) {  //404
            ToastUtil.toast(R.string.app_network_http_no);
        } else {
            ToastUtil.toast(R.string.app_network_http_exception_all);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        NetworkUtil.dismissCutscenes();

        if (swipeLayout != null && swipeLayout.isRefreshing()) {
            swipeLayout.finishRefresh();
        }
        if (swipeLayout != null && swipeLayout.isEnableLoadMore()) {
            swipeLayout.finishLoadMore(false);
        }
        if (t instanceof ApiException) {
            ExceptionHandling.operate(((ApiException) t).getResult());
        }

        if (t instanceof IOException) {
            ToastUtil.toast(R.string.app_network_socket_timeout);
        }
        t.printStackTrace();
    }
}
