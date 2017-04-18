package com.hks360.xswipemenurecyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hks360.library.mtech.adapter.BaseListRecyclerAdapter;
import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

/**
 * Created by xiaote on 2017/4/14.
 */

public class MyAdapter extends BaseListRecyclerAdapter<String,MyAdapter.ViewHolder> {
    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.item_xrecyclerview;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
        holder.mTextView.setText(s);
    }

    public static class ViewHolder extends RecyclerHolder {
        public TextView mTextView;

        public ViewHolder(View view){
            super(view);
            mTextView = UIUtil.findViewById(view,R.id.tv_title);
        }
    }
}
