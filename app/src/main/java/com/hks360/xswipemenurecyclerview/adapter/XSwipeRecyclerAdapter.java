package com.hks360.xswipemenurecyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hks360.library.mtech.adapter.BaseArrayRecyclerAdapter;
import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

/**
 * Created by xiaote on 2017/4/14.
 */

public class XSwipeRecyclerAdapter extends BaseArrayRecyclerAdapter<String,XSwipeRecyclerAdapter.ViewHolder> {


    public XSwipeRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.item_xswiperecycler;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
        holder.tv.setText(s);
    }

    public static class ViewHolder extends RecyclerHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = UIUtil.findViewById(itemView, R.id.xswiperecycler_tv);
        }
    }
}
