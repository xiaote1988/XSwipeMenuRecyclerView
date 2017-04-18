package com.hks360.library.mtech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.library.mtech.swipe.SwipeMenuAdapter;

import java.util.Collections;
import java.util.List;

public abstract class BaseListSwipeMenuAdapter<T, VH extends RecyclerHolder> extends SwipeMenuAdapter<VH> {

    protected List<T> dataList;

    public BaseListSwipeMenuAdapter() {

    }

    public BaseListSwipeMenuAdapter(List<T> dataList) {
        this.dataList = dataList;
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

    public abstract int getLayoutResId(int viewType);

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(viewType), parent, false);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public T getItem(int position) {
        return dataList.get(position);
    }
}
