package com.hks360.xswipemenurecyclerview.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.hks360.library.mtech.Mode;
import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.listener.OnLoadingListener;
import com.hks360.library.mtech.recycler.XSwipeMenuRecyclerView;
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
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaote on 2017/4/14.
 */

public class TestGridXSwipeMenuRecyclerViewActivity extends BaseActivity {
    private Activity mContext;
    private List<String> listData;
    private MenuAdapter mMenuAdapter;
    private int refreshTime = 0;
    private int times = 0;
    private XSwipeMenuRecyclerView mMenuRecyclerView;

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
                        .setImage(R.drawable.ic_action_add) // 图标。
                        .setWidth(width) // 宽度。
                        .setHeight(height); // 高度。
                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.drawable.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.drawable.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.drawable.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。
            }
        }
    };


    @Override
    public int getContentViewId() {
        return R.layout.activity_test_xswipemenurecyclerview;
    }

    @Override
    public void setupView() {
        mMenuRecyclerView = UIUtil.findViewById(this,R.id.test_xswipe_rv);
    }

    @Override
    public void initData(Intent getIntent) {
        mContext = this;
        listData = new ArrayList<>();
        mMenuRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));// 布局管理器。
        mMenuRecyclerView.addItemDecoration(new ListViewDecoration(this));// 添加分割线。
        mMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mMenuRecyclerView.setMode(Mode.BOTH);
        mMenuAdapter = new MenuAdapter(listData);
        mMenuRecyclerView.setAdapter(mMenuAdapter);
    }

    @Override
    public void addListener() {
        mMenuRecyclerView.setOnItemClickListener(new MyOnItemClickListener());
        mMenuRecyclerView.setOnSwipeMenuItemClickListener(new MyOnSwipeMenuItemClickListener());
        mMenuRecyclerView.setOnLoadingListener(new MyOnLoadingListener());
        mMenuRecyclerView.refresh();
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

    private class MyOnLoadingListener implements OnLoadingListener {

        public void onRefresh() {
            refreshTime++;
            times = 0;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    listData.clear();
                    for (int i = 0; i < 40; i++) {
                        listData.add("item" + i + "after " + refreshTime + " times of refresh");
                    }
                    mMenuAdapter.notifyDataSetChanged();
                    mMenuRecyclerView.refreshComplete();
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            if (times < 2) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 40; i++) {
                            listData.add("item" + (1 + listData.size()));
                        }
                        mMenuRecyclerView.loadMoreComplete();
                        mMenuAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 30; i++) {
                            listData.add("item" + (1 + listData.size()));
                        }
                        mMenuRecyclerView.setNoMore(true);
                        mMenuAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
            times++;
        }
    }
}
