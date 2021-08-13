package com.ewq.zq.module.lobby.view;

import android.os.Bundle;

import com.ewq.zq.R;
import com.ewq.zq.base.BaseFragment;

public class LobbyFragment extends BaseFragment {

    private static LobbyFragment lobbyFragment;

    private LobbyFragment() {
        super();
    }

    public static LobbyFragment getInstance() {
        if (lobbyFragment == null) {
            lobbyFragment = new LobbyFragment();
        }
        return lobbyFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lobby;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {

    }
}
