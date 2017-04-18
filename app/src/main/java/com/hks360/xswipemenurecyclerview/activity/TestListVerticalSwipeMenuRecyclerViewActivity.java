package com.hks360.xswipemenurecyclerview.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.recycler.SwipeMenuRecyclerView;
import com.hks360.library.mtech.swipe.Closeable;
import com.hks360.library.mtech.swipe.Direction;
import com.hks360.library.mtech.swipe.DirectionMode;
import com.hks360.library.mtech.swipe.OnSwipeMenuItemClickListener;
import com.hks360.library.mtech.swipe.SwipeMenu;
import com.hks360.library.mtech.swipe.SwipeMenuCreator;
import com.hks360.library.mtech.swipe.SwipeMenuItem;
import com.hks360.library.mtech.widget.ListViewDecoration;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.adapter.MenuAdapter;
import com.hks360.xswipemenurecyclerview.adapter.MenuVerticalAdapter;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaote on 2017/4/17.
 */

public class TestListVerticalSwipeMenuRecyclerViewActivity extends BaseActivity {
    private Activity mContext;
    private MenuVerticalAdapter mMenuAdapter;
    private List<String> mDataList;
    private SwipeMenuRecyclerView mMenuRecyclerView;

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);
            // 设置菜单方向为竖型的。
            swipeRightMenu.setOrientation(SwipeMenu.VERTICAL);
            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.drawable.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(0)
                        .setWeight(1);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.drawable.ic_action_close)
                        .setWidth(width)
                        .setHeight(0)
                        .setWeight(1);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。
            }
        }
    };


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
        mContext = this;
        mDataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDataList.add("我是第" + i + "个。");
        }
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        mMenuRecyclerView.addItemDecoration(new ListViewDecoration(this));// 添加分割线。
        mMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mMenuAdapter = new MenuVerticalAdapter(mDataList);
        mMenuRecyclerView.setAdapter(mMenuAdapter);
    }

    @Override
    public void addListener() {
        mMenuRecyclerView.setOnItemClickListener(new MyOnItemClickListener());
        mMenuRecyclerView.setOnSwipeMenuItemClickListener(new MyOnSwipeMenuItemClickListener());
    }

    private class MyOnItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            CommonUtil.showToast(getBaseContext(), "点击了第" + position + "个");
        }
    }

    private class MyOnSwipeMenuItemClickListener implements OnSwipeMenuItemClickListener {

        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition,
                                @DirectionMode int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            switch (direction) {
                case Direction.LEFT:
                    CommonUtil.showToast(getBaseContext(),"list第" + adapterPosition + "; 左侧菜单第" + menuPosition);
                    break;
                case Direction.RIGHT:
                    CommonUtil.showToast(getBaseContext(),"list第" + adapterPosition + "; 右侧菜单第" + menuPosition);
                    break;
            }
        }
    }
}
