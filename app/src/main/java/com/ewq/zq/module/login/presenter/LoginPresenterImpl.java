package com.ewq.zq.module.login.presenter;

import android.util.Log;

import com.ewq.network.entity.HttpResult;
import com.ewq.network.manager.RDClient;
import com.ewq.network.manager.RequestCallBack;
import com.ewq.tools.common.Constant;
import com.ewq.tools.info.SharedInfo;
import com.ewq.zq.module.login.contract.ILoginContract;
import com.ewq.zq.module.login.model.LoginBean;
import com.ewq.zq.network.api.LoginService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginPresenterImpl implements ILoginContract.ILoginPresenter {

    private final ILoginContract.ILoginView iLoginView;

    public LoginPresenterImpl(ILoginContract.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void doLogin(String username, String password, boolean rememberMe) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("remember_me", rememberMe);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("LoginPresenterImpl", new Gson().toJson(jsonObject));

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), jsonObject.toString());
        Call<HttpResult<LoginBean>> call = RDClient.getService(LoginService.class).authLogin(body);

        call.enqueue(new RequestCallBack<HttpResult<LoginBean>>() {
            @Override
            public void onSuccess(Call<HttpResult<LoginBean>> call,
                                  Response<HttpResult<LoginBean>> response) {
                if (response.body().getData() != null) {
                    LoginBean loginBean = response.body().getData();
                    SharedInfo.getInstance().saveValue(Constant.token, loginBean.getToken());
                    SharedInfo.getInstance().saveValue(Constant.userId, loginBean.getUserInfo().getId());
                    iLoginView.loginResult(200, loginBean);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<LoginBean>> call, Throwable t) {
                super.onFailure(call, t);
                iLoginView.loginResult(201, null);
            }

            @Override
            public void onFailed(Call<HttpResult<LoginBean>> call, Response<HttpResult<LoginBean>> response) {
                super.onFailed(call, response);
                iLoginView.loginResult(201, null);
            }
        });
    }
}
