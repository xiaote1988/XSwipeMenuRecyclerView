package com.hks360.xswipemenurecyclerview.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hks360.library.mtech.swipe.SwipeSwitch;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

/**
 * Created by xiaote on 2017/4/17.
 */

public class TestSwipeMenuLayoutActivity extends BaseActivity {
    private SwipeSwitch mSwipeSwitch;
    private TextView leftTv;
    private TextView rightTv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_test_swipemenulayout;
    }

    @Override
    public void setupView() {
        mSwipeSwitch = UIUtil.findViewById(this,R.id.swipe_layout);
        leftTv = UIUtil.findViewById(this, R.id.left_view);
        rightTv = UIUtil.findViewById(this, R.id.right_view);
    }

    @Override
    public void initData(Intent getIntent) {

    }

    @Override
    public void addListener() {
        MyOnClickListener listener = new MyOnClickListener();
        leftTv.setOnClickListener(listener);
        rightTv.setOnClickListener(listener);
    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.left_view:
                    mSwipeSwitch.smoothCloseMenu();
                    CommonUtil.showToast(getBaseContext(),"点击的是左侧Button");
                    break;
                case R.id.right_view:
                    mSwipeSwitch.smoothCloseMenu();
                    CommonUtil.showToast(getBaseContext(),"点击的是右侧Button");
                    break;
            }
        }
    }
}
