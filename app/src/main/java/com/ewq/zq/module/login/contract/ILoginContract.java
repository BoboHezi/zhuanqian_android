package com.ewq.zq.module.login.contract;

import com.ewq.zq.module.login.model.LoginBean;

public interface ILoginContract {

    interface ILoginView {
        void loginResult(int code, LoginBean bean);
    }

    interface ILoginPresenter {
        void doLogin(String username, String password, boolean rememberMe);
    }
}
