package com.surface.resourcecenter.ui.home.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by yangzhipeng on 2018/6/21.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    Fragment[] fragments;

    public void setFragments(Fragment[] fragments) {
        this.fragments = fragments;
    }

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
