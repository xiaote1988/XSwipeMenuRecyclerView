package com.hks360.library.mtech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hks360.library.mtech.holder.RecyclerHolder;

public abstract class BaseArrayRecyclerAdapter<T, VH extends RecyclerHolder> extends RecyclerView.Adapter<VH> {
    private Context context;
    private T[] dataArray;

    public BaseArrayRecyclerAdapter(Context context) {
        this.context = context;
    }

    public BaseArrayRecyclerAdapter(Context context, T[] dataArray) {
        this.context = context;
        this.dataArray = dataArray;
    }

    public abstract int getViewLayoutId(int viewType);

    public abstract VH createViewHolder(View view);

    public abstract void convert(VH holder, T t, int position);

    public void setDataArray(T[] dataArray) {
        this.dataArray = dataArray;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getViewLayoutId(viewType), parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, dataArray[position], position);
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.length;
    }

    public T getItem(int position) {
        return dataArray[position];
    }

    public Context getContext() {
        return context;
    }

    public T[] getDataArray() {
        return dataArray;
    }
}
