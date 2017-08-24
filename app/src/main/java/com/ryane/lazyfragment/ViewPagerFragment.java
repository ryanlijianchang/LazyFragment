package com.ryane.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ViewPagerFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int mFragmentType;

    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;

    public static ViewPagerFragment getInstance(int type) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.mFragmentType = type;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = (TabLayout) view.findViewById(R.id.mSubTabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.mSubViewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Fragment> mList = new ArrayList<>();
        List<String> mTitltes;
        ViewPagerAdapter mAdapter;
        switch (mFragmentType) {
            case TYPE_0:
                mTitltes = Arrays.asList(getResources().getStringArray(R.array.titles1));
                mList.add(SubFragment.getInstance(Arrays.asList(getResources().getStringArray(R.array.beauty)), "美女页"));
                mList.add(SubFragment.getInstance(Arrays.asList(getResources().getStringArray(R.array.hansome)), "帅哥页"));
                mList.add(SubFragment.getInstance(Arrays.asList(getResources().getStringArray(R.array.baby)), "萌娃页"));
                mAdapter = new ViewPagerAdapter(getChildFragmentManager(), mList, mTitltes);
                mViewPager.setAdapter(mAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                break;
            case TYPE_1:
                mTitltes = Arrays.asList(getResources().getStringArray(R.array.titles2));

                mList.add(SubFragment.getInstance(Arrays.asList(getResources().getStringArray(R.array.peking)), "北京页"));
                mList.add(SubFragment.getInstance(Arrays.asList(getResources().getStringArray(R.array.hongkong)), "香港页"));
                mList.add(SubFragment.getInstance(Arrays.asList(getResources().getStringArray(R.array.shanghai)), "上海页"));
                mAdapter = new ViewPagerAdapter(getChildFragmentManager(), mList, mTitltes);
                mViewPager.setAdapter(mAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                break;
        }
    }
}
