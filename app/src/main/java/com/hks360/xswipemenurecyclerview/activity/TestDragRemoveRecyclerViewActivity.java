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
import com.hks360.library.mtech.listener.OnItemMovementListener;
import com.hks360.library.mtech.listener.OnItemStateChangedListener;
import com.hks360.library.mtech.recycler.MRecyclerView;
import com.hks360.library.mtech.widget.ListViewDecoration;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.adapter.MenuAdapter;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiaote on 2017/4/17.
 */

public class TestDragRemoveRecyclerViewActivity extends BaseActivity {
    private Activity mContext;
    private ActionBar mActionBar;
    private List<String> mDataList;
    private MenuAdapter mMenuAdapter;
    private MRecyclerView mRecyclerView;
    private Toolbar toolbar;

    @Override
    public int getContentViewId() {
        return R.layout.activity_test_dragtouchrecyclerview;
    }

    @Override
    public void setupView() {
        toolbar = UIUtil.findViewById(this,R.id.toolbar);
        mRecyclerView = UIUtil.findViewById(this,R.id.drag_recycler_view);
    }

    @Override
    public void initData(Intent getIntent) {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mDataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i == 0) {
                mDataList.add("我不能被拖拽，也不能滑动删除。");
            } else {
                mDataList.add("我是第" + i + "个。");
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new ListViewDecoration(this));
        mMenuAdapter = new MenuAdapter(mDataList);
        mRecyclerView.setAdapter(mMenuAdapter);
    }

    @Override
    public void addListener() {
        mRecyclerView.setOnItemClickListener(new MyOnItemClickListener());
        mRecyclerView.setOnItemMovementListener(new MyOnItemMovementListener());
        mRecyclerView.setOnItemMoveListener(new MyOnItemMoveListener());
        mRecyclerView.setOnItemStateChangedListener(new MyOnItemStateChangedListener());
    }

    private class MyOnItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            CommonUtil.showToast(getBaseContext(),"点击了第" + position + "个");
        }
    }

    private class MyOnItemMovementListener implements OnItemMovementListener {
        /**
         * 当Item在移动之前，获取拖拽的方向。
         * @param recyclerView     {@link RecyclerView}.
         * @param targetViewHolder target ViewHolder.
         * @return
         */
        @Override
        public int onDragFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
            // 我们让第一个不能拖拽。
            if (targetViewHolder.getAdapterPosition() == 0) {
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {// 如果是LinearLayoutManager。
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {// 横向的List。
                    return (OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT); // 只能左右拖拽。
                } else {// 竖向的List。
                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN; // 只能上下拖拽。
                }
            }
            return OnItemMovementListener.INVALID;// 返回无效的方向。
        }

        @Override
        public int onSwipeFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
            // 我们让第一个不能滑动删除。
            if (targetViewHolder.getAdapterPosition() == 0) {
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {// 如果是LinearLayoutManager
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {// 横向的List。
                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN; // 只能上下滑动删除。
                } else {// 竖向的List。
                    return OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT; // 只能左右滑动删除。
                }
            }
            return OnItemMovementListener.INVALID;// 其它均返回无效的方向。
        }
    }

    private class MyOnItemMoveListener implements OnItemMoveListener {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (toPosition == 0) {// 保证第一个不被挤走。
                return false;
            }
            if (fromPosition < toPosition)
                for (int i = fromPosition; i < toPosition; i++)
                    Collections.swap(mDataList, i, i + 1);
            else
                for (int i = fromPosition; i > toPosition; i--)
                    Collections.swap(mDataList, i, i - 1);

            mMenuAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            mDataList.remove(position);
            mMenuAdapter.notifyItemRemoved(position);
            CommonUtil.showToast(mContext, "现在的第" + position + "条被删除。");
        }
    }

    private class MyOnItemStateChangedListener implements OnItemStateChangedListener {

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
                mActionBar.setSubtitle("状态：拖拽");
                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white_pressed));
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
                mActionBar.setSubtitle("状态：滑动删除");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
                mActionBar.setSubtitle("状态：手指松开");
                // 在手松开的时候还原背景。
                ViewCompat.setBackground(viewHolder.itemView, ContextCompat.getDrawable(mContext, R.drawable.select_white));
            }
        }
    }
}
