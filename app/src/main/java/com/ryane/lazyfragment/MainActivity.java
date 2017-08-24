package com.ryane.lazyfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private RelativeLayout mContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initTabLayout();
    }

    private void findView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mContainer = (RelativeLayout) findViewById(R.id.mContainer);
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("人物"));
        mTabLayout.addTab(mTabLayout.newTab().setText("风景"));
        setCurrentPage(0);
        mTabLayout.getTabAt(0).select();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TAB", "onTabSelected()" + tab.getPosition());
                setCurrentPage(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("TAB", "onTabUnselected()" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("TAB", "onTabReselected()" + tab.getPosition());
            }
        });
    }

    private void setCurrentPage(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.replace(R.id.mContainer, ViewPagerFragment.getInstance(ViewPagerFragment.TYPE_0));
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.mContainer, ViewPagerFragment.getInstance(ViewPagerFragment.TYPE_1));
                transaction.commit();
                break;
        }
    }

}
