package com.hks360.xswipemenurecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hks360.library.mtech.adapter.BaseListRecyclerAdapter;
import com.hks360.library.mtech.adapter.BaseListSwipeMenuAdapter;
import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.List;

/**
 * Created by xiaote on 2017/4/17.
 */

public class MenuVerticalAdapter extends BaseListSwipeMenuAdapter<String, MenuVerticalAdapter.ViewHolder> {

    public MenuVerticalAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_menu_vertical;
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new ViewHolder(realContentView);
    }

    @Override
    public void onCompatBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(dataList.get(position));
    }

    static class ViewHolder extends RecyclerHolder {
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = UIUtil.findViewById(itemView, R.id.tv_title);
        }
    }
}
