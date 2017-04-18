package com.hks360.xswipemenurecyclerview.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.recycler.MRecyclerView;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.adapter.XSwipeRecyclerAdapter;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

public class MainActivity extends BaseActivity {

    private MRecyclerView mRecyclerView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupView() {
        mRecyclerView = UIUtil.findViewById(this, R.id.mrecyclerview);
    }

    @Override
    public void initData(Intent getIntent) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] array = getResources().getStringArray(R.array.xswipemenurecycler_list);
        XSwipeRecyclerAdapter adapter = new XSwipeRecyclerAdapter(this);
        adapter.setDataArray(array);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void addListener() {
        mRecyclerView.setOnItemClickListener(new MyOnItemClickListener(this));
    }

    private class MyOnItemClickListener implements OnItemClickListener {
        private Activity activity;

        public MyOnItemClickListener(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent();
            switch (position) {
                case 0:
                    intent.setClass(activity, TestListXRecyclerViewActivity.class);
                    break;
                case 1:
                    intent.setClass(activity, TestGridXRecyclerViewActivity.class);
                    break;
                case 2:
                    intent.setClass(activity, TestListSwipeMenuRecyclerViewActivity.class);
                    break;
                case 3:
                    intent.setClass(activity, TestGridSwipeMenuRecyclerViewActivity.class);
                    break;
                case 4:
                    intent.setClass(activity, TestListXRecyclerSwipeMenuViewActivity.class);
                    break;
                case 5:
                    intent.setClass(activity, TestGridXSwipeMenuRecyclerViewActivity.class);
                    break;
                case 6:
                    intent.setClass(activity, TestListDragRecyclerViewActivity.class);
                    break;
                case 7:
                    intent.setClass(activity, TestGridDrageRecyclerViewActivity.class);
                    break;
                case 8:
                    intent.setClass(activity, TestListDragTouchRecyclerViewActivity.class);
                    break;
                case 9:
                    intent.setClass(activity, CollapsingToolbarLayoutActivity.class);
                    break;
                case 10:
                    intent.setClass(activity, TestListVerticalSwipeMenuRecyclerViewActivity.class);
                case 11:
                    intent.setClass(activity, TestDragRemoveRecyclerViewActivity.class);
                    break;
                case 12:
                    intent.setClass(activity, TestCardSwipeMenuRecyclerActivity.class);
                    break;
                case 13:
                    intent.setClass(activity, TestSwipeMenuLayoutActivity.class);
                    break;
                case 14:
                    intent.setClass(activity,TestViewTypeSwipeMenuRecyclerActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
