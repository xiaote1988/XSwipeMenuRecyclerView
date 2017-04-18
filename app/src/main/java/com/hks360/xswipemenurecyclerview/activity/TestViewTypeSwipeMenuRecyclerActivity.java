package com.hks360.xswipemenurecyclerview.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.recycler.SwipeMenuRecyclerView;
import com.hks360.library.mtech.swipe.Closeable;
import com.hks360.library.mtech.swipe.Direction;
import com.hks360.library.mtech.swipe.OnSwipeMenuItemClickListener;
import com.hks360.library.mtech.swipe.SwipeMenu;
import com.hks360.library.mtech.swipe.SwipeMenuCreator;
import com.hks360.library.mtech.swipe.SwipeMenuItem;
import com.hks360.library.mtech.widget.ListViewDecoration;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.adapter.MenuViewTypeAdapter;
import com.hks360.xswipemenurecyclerview.entity.ViewTypeBean;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaote on 2017/4/17.
 */

public class TestViewTypeSwipeMenuRecyclerActivity extends BaseActivity {
    private Activity mContext;

    private List<ViewTypeBean> mViewTypeBeanList;
    private SwipeMenuRecyclerView menuRecyclerView;
    private Toolbar toolbar;

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);
            Log.d("test", "viewType==" + viewType);
            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_NONE) {// 根据Adapter的ViewType来决定菜单的样式、颜色等属性、或者是否添加菜单。
                // Do nothing.
            } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_SINGLE) {// 需要添加单个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.drawable.ic_action_wechat)
                        .setText("微信")
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(wechatItem);
                swipeRightMenu.addMenuItem(wechatItem);

            } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_MULTI) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.drawable.ic_action_wechat)
                        .setText("微信")
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(wechatItem);
                swipeRightMenu.addMenuItem(wechatItem);

                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setImage(R.drawable.ic_action_add)
                        .setText("添加")
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(addItem);
                swipeRightMenu.addMenuItem(addItem);
            } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_LEFT) {
                SwipeMenuItem wechatItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.drawable.ic_action_wechat)
                        .setText("嘻嘻")
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(wechatItem);
            } else if(viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_RIGHT) {
                SwipeMenuItem wechatItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.drawable.ic_action_wechat)
                        .setText("嘻嘻")
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);
            }
        }
    };


    @Override
    public int getContentViewId() {
        return R.layout.activity_test_dragrecyclerview;
    }

    @Override
    public void setupView() {
        menuRecyclerView = UIUtil.findViewById(this, R.id.drag_recycler_view);
        toolbar = UIUtil.findViewById(this, R.id.toolbar);
    }

    @Override
    public void initData(Intent getIntent) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        mContext = this;
        mViewTypeBeanList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ViewTypeBean viewTypeBean = new ViewTypeBean();
            switch (i % 5) {
                case 0:
                    viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_NONE);
                    viewTypeBean.setContent("我没有菜单");
                    break;
                case 1:
                    viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_SINGLE);
                    viewTypeBean.setContent("我有1个菜单");
                    break;
                case 2:
                    viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_MULTI);
                    viewTypeBean.setContent("我有2个菜单");
                    break;
                case 3:
                    viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_LEFT);
                    viewTypeBean.setContent("我的左边有菜单，右边没有");
                    break;
                case 4:
                    viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_RIGHT);
                    viewTypeBean.setContent("我的右边有菜单，左边没有");
                    break;
            }
            mViewTypeBeanList.add(viewTypeBean);
        }
        Log.d("test", mViewTypeBeanList.toString());
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.addItemDecoration(new ListViewDecoration(this));
        MenuViewTypeAdapter menuAdapter = new MenuViewTypeAdapter(mViewTypeBeanList);
        menuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        menuRecyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void addListener() {
        menuRecyclerView.setOnItemClickListener(new MyOnItemClickListener());
        menuRecyclerView.setOnSwipeMenuItemClickListener(new MyOnSwipeMenuItemClickListener());
    }

    private class MyOnItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(mContext, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
        }
    }

    ;

    /**
     * 菜单点击监听。
     */
    private class MyOnSwipeMenuItemClickListener implements OnSwipeMenuItemClickListener {

        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            switch (direction) {
                case Direction.LEFT:
                    CommonUtil.showToast(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition);
                    break;
                case Direction.RIGHT:
                    CommonUtil.showToast(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition);
                    break;
            }
        }
    }

    ;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
