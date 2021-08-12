package com.ewq.zq.network.api;

import com.ewq.network.entity.HttpResult;
import com.ewq.zq.module.login.model.LoginBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    /**
     * 查询登录用户积分
     *
     * @return
     */
    @POST("sys/login")
    Call<HttpResult<LoginBean>> authLogin(@Body RequestBody type);
}
