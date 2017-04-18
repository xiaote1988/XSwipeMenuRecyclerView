package com.hks360.library.mtech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hks360.library.mtech.holder.RecyclerHolder;

import java.util.List;

/**
 * Created by xiaote on 2017/4/18.
 */

public abstract class ArrayRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    private Context context;
    private T[] dataArray;

    public ArrayRecyclerAdapter(Context context) {
        this.context = context;
    }

    public ArrayRecyclerAdapter(Context context, T[] dataArray) {
        this(context);
        this.dataArray = dataArray;
    }

    public void setDataList(T[] dataArray) {
        this.dataArray = dataArray;
    }

    public void refreshData(T[] dataArray) {
        this.dataArray = dataArray;
        this.notifyDataSetChanged();
    }

    public abstract int getLayoutId(int viewType);

    public abstract void convert(RecyclerHolder holder, T t, int position);

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(getLayoutId(viewType), parent, false);
        RecyclerHolder holder = new RecyclerHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        convert(holder, dataArray[position], position);
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.length;
    }

    public T getItem(int position) {
        return dataArray[position];
    }

    public T[] getDataArray() {
        return dataArray;
    }

    public Context getContext() {
        return context;
    }
}
