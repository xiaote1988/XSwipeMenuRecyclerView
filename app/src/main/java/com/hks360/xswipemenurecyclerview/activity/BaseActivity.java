package com.hks360.xswipemenurecyclerview.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xiaote on 2016/10/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getContentViewId();

    public abstract void setupView();

    public abstract void initData(Intent getIntent);

    public abstract void addListener();

    public void initView() {
        setContentView(getContentViewId());
        setupView();
        initData(getIntent());
        addListener();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

}
