package com.ryane.lazyfragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SubFragment extends Fragment {
    private String mTagName;
    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecyclerView;
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    private List<String> mPhotos;
    private MyAdapter adapter = null;

    public static SubFragment getInstance(List<String> photos, String name) {
        SubFragment subFragment = new SubFragment();
        subFragment.mPhotos = photos;
        subFragment.mTagName = name;
        return subFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", mTagName + " onCreate() --> isVisibleToUser = "+ getUserVisibleHint());
        DisplayMetrics dm = SubFragment.this.getActivity().getResources().getDisplayMetrics();
        System.out.println(dm.heightPixels + "");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", mTagName + " onCreateView()");
        DisplayMetrics dm = SubFragment.this.getActivity().getResources().getDisplayMetrics();
        System.out.println(dm.heightPixels + "");

        return inflater.inflate(R.layout.fragment_sub, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG", mTagName + " onViewCreated()");
        DisplayMetrics dm = SubFragment.this.getActivity().getResources().getDisplayMetrics();
        System.out.println(dm.heightPixels + "");
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.mRefresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", mTagName + " onActivityCreated()");

        adapter = new MyAdapter(null, getActivity());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", mTagName + " onStart()");

        mRefresh.setRefreshing(true);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullData();
            }
        });
    }

    /**
     *  模拟网络耗时操作拉取数据
     */
    private void pullData() {
        // 模拟网络耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mUIHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mRefresh != null) {
                                mRefresh.setRefreshing(false);
                                if (adapter != null) {
                                    adapter.setPhotos(mPhotos);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("TAG", mTagName + " setUserVisibleHint() --> isVisibleToUser = " + isVisibleToUser);

        if (isVisibleToUser) {
            pullData();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
