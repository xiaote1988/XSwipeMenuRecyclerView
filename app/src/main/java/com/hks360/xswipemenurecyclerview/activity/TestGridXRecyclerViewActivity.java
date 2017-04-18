package com.hks360.xswipemenurecyclerview.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.hks360.library.mtech.Mode;
import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.listener.OnLoadingListener;
import com.hks360.library.mtech.recycler.XRecyclerView;
import com.hks360.library.mtech.widget.ProgressStyle;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.adapter.MyAdapter;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.ArrayList;

/**
 * Created by xiaote on 2017/4/14.
 */

public class TestGridXRecyclerViewActivity extends BaseActivity {

    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    public int getContentViewId() {
        return R.layout.activity_test_xrecyclerview;
    }

    @Override
    public void setupView() {
        mRecyclerView = UIUtil.findViewById(this, R.id.test_xrv);
    }

    @Override
    public void initData(Intent getIntent) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        listData = new ArrayList<>();
        mAdapter = new MyAdapter(this);
        mAdapter.setDataList(listData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setMode(Mode.BOTH);
    }

    @Override
    public void addListener() {
        mRecyclerView.setOnItemClickListener(new MyOnItemClickListener());
        mRecyclerView.setOnLoadingListener(new MyOnLoadingListener());
        mRecyclerView.refresh();
    }

    private class MyOnItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            CommonUtil.showToast(getBaseContext(), "点击了第" + position + "个");
        }
    }

    private class MyOnLoadingListener implements OnLoadingListener {

        public void onRefresh() {
            refreshTime++;
            times = 0;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    listData.clear();
                    for (int i = 0; i < 50; i++) {
                        listData.add("item" + i + "after " + refreshTime + " times of refresh");
                    }
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            if (times < 2) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 50; i++) {
                            listData.add("item" + (1 + listData.size()));
                        }
                        mRecyclerView.loadMoreComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 30; i++) {
                            listData.add("item" + (1 + listData.size()));
                        }
                        mRecyclerView.setNoMore(true);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
            times++;
        }
    }
}