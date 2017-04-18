package com.hks360.xswipemenurecyclerview.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.listener.OnItemMoveListener;
import com.hks360.library.mtech.listener.OnItemStateChangedListener;
import com.hks360.library.mtech.recycler.MRecyclerView;
import com.hks360.library.mtech.widget.ListViewDecoration;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.adapter.DragTouchAdapter;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiaote on 2017/4/17.
 */

public class TestListDragTouchRecyclerViewActivity extends BaseActivity {
    private Activity mContext;
    private List<String> mDataList;
    private DragTouchAdapter mDragAdapter;
    private ActionBar mActionBar;
    private MRecyclerView mRecyclerView;
    private Toolbar toolbar;

    @Override
    public int getContentViewId() {
        return R.layout.activity_test_dragtouchrecyclerview;
    }

    @Override
    public void setupView() {
        toolbar = UIUtil.findViewById(this, R.id.toolbar);
        mRecyclerView = UIUtil.findViewById(this, R.id.drag_recycler_view);
    }

    @Override
    public void initData(Intent getIntent) {
        mContext = this;
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mDataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDataList.add("我是第" + i + "个。");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new ListViewDecoration(this));
        mDragAdapter = new DragTouchAdapter(mRecyclerView,mDataList);
        mRecyclerView.setAdapter(mDragAdapter);
        mRecyclerView.setLongPressDragEnabled(true); // 开启拖拽。
        mRecyclerView.setItemViewSwipeEnabled(true); // 开启滑动删除。
    }

    @Override
    public void addListener() {
        mRecyclerView.setOnItemClickListener(new MyOnItemClickListener());
        mRecyclerView.setOnItemMoveListener(new MyOnItemMovedListener());
        mRecyclerView.setOnItemStateChangedListener(new MyOnItemStateChangedListener());
    }

    private class MyOnItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            CommonUtil.showToast(mContext, "点击了第" + position + "个");
        }
    }

    private class MyOnItemMovedListener implements OnItemMoveListener {

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Collections.swap(mDataList, fromPosition, toPosition);
            mDragAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;// 返回true表示处理了，返回false表示你没有处理。
        }

        @Override
        public void onItemDismiss(int position) {
            mDataList.remove(position);
            mDragAdapter.notifyItemRemoved(position);
            Toast.makeText(mContext, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyOnItemStateChangedListener implements OnItemStateChangedListener {

        public MyOnItemStateChangedListener() {

        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
                mActionBar.setSubtitle("状态：拖拽");

                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.white_pressed));
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
                mActionBar.setSubtitle("状态：滑动删除");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
                mActionBar.setSubtitle("状态：手指松开");
                // 在手松开的时候还原背景。
                ViewCompat.setBackground(viewHolder.itemView, ContextCompat.getDrawable(getBaseContext(), R.drawable.select_white));
            }
        }
    }
}
