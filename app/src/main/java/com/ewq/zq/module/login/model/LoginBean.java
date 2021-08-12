package com.ewq.zq.module.login.model;

public class LoginBean {

    private UserInfo userInfo;

    private String token;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "userInfo=" + userInfo +
                ", token='" + token + '\'' +
                '}';
    }
}
