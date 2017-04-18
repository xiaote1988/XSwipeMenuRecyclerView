package com.hks360.xswipemenurecyclerview.activity;

import android.content.Intent;
import
        android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

public class CollapsingToolbarLayoutActivity extends BaseActivity {
    private XRecyclerView mRecyclerView;
    private Toolbar toolbar;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    public int getContentViewId() {
        return R.layout.activity_collapsing_toolbar_layout;
    }

    @Override
    public void setupView() {
        toolbar = UIUtil.findViewById(this,R.id.toolbar);
        mRecyclerView = UIUtil.findViewById(this,R.id.test_collapsing_xrv);
    }

    @Override
    public void initData(Intent getIntent) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setMode(Mode.BOTH);
        listData = new ArrayList<String>();
        for(int i = 0; i < 15 ;i++){
            listData.add("item" + i);
        }
        mAdapter = new MyAdapter(this);
        mAdapter.setDataList(listData);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void addListener() {
        mRecyclerView.setOnItemClickListener(new MyOnItemClickListener());
        mRecyclerView.setOnLoadingListener(new MyOnLoadingListener());
    }

    private class MyOnItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            CommonUtil.showToast(getBaseContext(),"点击了第" + position + "个");
        }
    }

    private class MyOnLoadingListener implements OnLoadingListener {
        @Override
        public void onRefresh() {
            refreshTime ++;
            times = 0;
            new Handler().postDelayed(new Runnable(){
                public void run() {

                    listData.clear();
                    for(int i = 0; i < 15 ;i++){
                        listData.add("item" + i + "after " + refreshTime + " times of refresh");
                    }
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            if(times < 2){
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        for(int i = 0; i < 15 ;i++){
                            listData.add("item" + (i + listData.size()) );
                        }
                        mRecyclerView.loadMoreComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for(int i = 0; i < 9 ;i++){
                            listData.add("item" + (1 + listData.size() ) );
                        }
                        mRecyclerView.setNoMore(true);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
            times ++;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
