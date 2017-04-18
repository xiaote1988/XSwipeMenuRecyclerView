package com.hks360.library.mtech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hks360.library.mtech.holder.RecyclerHolder;

import java.util.Collections;
import java.util.List;

public abstract class BaseListRecyclerAdapter<T, VH extends RecyclerHolder> extends RecyclerView.Adapter<VH> {
    private Context context;
    protected List<T> dataList;

    public BaseListRecyclerAdapter(Context context) {
        this.context = context;
    }

    public BaseListRecyclerAdapter(Context context, List<T> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public Context getContext() {
        return context;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public void refreshData(List<T> dataList) {
        this.dataList = dataList;
        this.notifyDataSetChanged();
    }

    public void removeData(int position) {
        this.dataList.remove(position);
        this.notifyItemRemoved(position);
    }

    public void moveData(int fromPosition, int toPosition) {
        Collections.swap(dataList, fromPosition, toPosition);
        this.notifyItemMoved(fromPosition, toPosition);
    }

    public void modifyItem(int position, T t) {
        this.dataList.set(position, t);
        this.notifyItemChanged(position);
    }

    public void addData(T data) {
        addData(dataList.size(), data);
    }

    public void addData(int position, T data) {
        dataList.add(position, data);
        this.notifyItemInserted(position);
    }

    public abstract int getViewLayoutId(int viewType);

    public abstract VH createViewHolder(View view);

    public abstract void convert(VH holder, T t, int position);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getViewLayoutId(viewType), parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public T getItem(int position) {
        return dataList.get(position);
    }
}
