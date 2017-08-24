package com.ryane.lazyfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * Creator: lijianchang
 * Create Time: 2017/8/15.
 * Email: lijianchang@yy.com
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private List<String> mTitles;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> mList, List<String> mTitles) {
        super(fm);
        this.mList = mList;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles.get(position);
    }
}
