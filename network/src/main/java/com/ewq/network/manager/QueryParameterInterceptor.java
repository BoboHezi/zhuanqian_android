package com.ewq.network.manager;

import com.ewq.tools.common.Constant;
import com.ewq.tools.info.SharedInfo;
import com.ewq.tools.log.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @create 2019/9/30
 * @Describe
 */
public class QueryParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder newRequest = request.newBuilder();
        Request build = newRequest.build();
        newRequest.addHeader("token", (String) SharedInfo.getInstance().getValue(Constant.token, ""));
        Logger.e("Interceptor", (String) SharedInfo.getInstance().getValue(Constant.token, ""));
        return chain.proceed(build);
    }
}