package com.ewq.zq.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.concurrent.ConcurrentHashMap;

public class FragmentsPagerAdapter extends FragmentStatePagerAdapter {

    private ConcurrentHashMap<Integer, Fragment> mFragments;

    public FragmentsPagerAdapter(FragmentManager fm, ConcurrentHashMap<Integer, Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (mFragments.containsKey(position)) {
            fragment = mFragments.get(position);
            return fragment;
        }

        if (fragment != null) {
            mFragments.put(position, fragment);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
