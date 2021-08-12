package com.ewq.zq.module.login.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ewq.tools.common.BaseParams;
import com.ewq.tools.info.SharedInfo;
import com.ewq.zq.R;
import com.ewq.zq.module.login.contract.ILoginContract;
import com.ewq.zq.module.login.model.LoginBean;
import com.ewq.zq.module.login.presenter.LoginPresenterImpl;

public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {

    private ILoginContract.ILoginPresenter loginPresenter;

    private EditText edUsername;

    private EditText edPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.input_username);
        edPassword = findViewById(R.id.input_password);

        loginPresenter = new LoginPresenterImpl(this);

        SharedInfo.init(BaseParams.SP_NAME);
    }

    public void submit(View v) {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();

        Log.i("LoginActivity", "submit: " + username + "," + password);
        loginPresenter.doLogin(username, password, true);
    }

    @Override
    public void loginResult(int code, LoginBean bean) {
        Log.i("LoginActivity", "login Result code: " + code);
    }
}
