package com.hks360.xswipemenurecyclerview.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hks360.library.mtech.listener.CardItemClickListener;
import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.recycler.SwipeMenuRecyclerView;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.adapter.MenuCardAdapter;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaote on 2017/4/17.
 */

public class TestCardSwipeMenuRecyclerActivity extends BaseActivity {
    private SwipeMenuRecyclerView mMenuRecyclerView;
    private MenuCardAdapter menuCardAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_test_swipemenurecyclerview;
    }

    @Override
    public void setupView() {
        mMenuRecyclerView = UIUtil.findViewById(this,R.id.test_swipe_rv);
    }

    @Override
    public void initData(Intent getIntent) {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("我是第" + i + "个菜单");
        }
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        mMenuRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        menuCardAdapter = new MenuCardAdapter(dataList);
        mMenuRecyclerView.setAdapter(menuCardAdapter);
    }

    @Override
    public void addListener() {
        menuCardAdapter.setOnItemClickListener(new MyOnItemClickListener());
    }

    private class MyOnItemClickListener implements CardItemClickListener {

        @Override
        public void onItemClick(int position) {
            CommonUtil.showToast(getBaseContext(),"点击了第" + position + "个");
        }
    }
}
