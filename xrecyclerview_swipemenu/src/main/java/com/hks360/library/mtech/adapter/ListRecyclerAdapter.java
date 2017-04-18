package com.hks360.library.mtech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hks360.library.mtech.holder.RecyclerHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by xiaote on 2017/4/18.
 */

public abstract class ListRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    private Context context;
    private List<T> dataList;

    public ListRecyclerAdapter(Context context) {
        this.context = context;
    }

    public ListRecyclerAdapter(Context context, List<T> dataList) {
        this(context);
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
        convert(holder, dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public T getItem(int position) {
        return dataList.get(position);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public Context getContext() {
        return context;
    }
}
