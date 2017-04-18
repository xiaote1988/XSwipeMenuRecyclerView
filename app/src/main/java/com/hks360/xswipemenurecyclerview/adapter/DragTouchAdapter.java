/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hks360.xswipemenurecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hks360.library.mtech.adapter.BaseListSwipeMenuAdapter;
import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.recycler.MRecyclerView;
import com.hks360.xswipemenurecyclerview.R;

import java.util.List;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class DragTouchAdapter extends BaseListSwipeMenuAdapter<String,DragTouchAdapter.DefaultViewHolder> {

    private MRecyclerView mMenuRecyclerView;
    public OnItemClickListener onItemClickListener;

    public DragTouchAdapter(MRecyclerView menuRecyclerView, List<String> titles) {
        super(titles);
        this.mMenuRecyclerView = menuRecyclerView;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_drag_touch;
    }

    @Override
    public DragTouchAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
        viewHolder.mMenuRecyclerView = mMenuRecyclerView;
        return viewHolder;
    }

    @Override
    public void onCompatBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    static class DefaultViewHolder extends RecyclerHolder implements View.OnTouchListener {
        TextView tvTitle;
        MRecyclerView mMenuRecyclerView;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.iv_touch).setOnTouchListener(this);
        }

        public void setData(String title) {
            this.tvTitle.setText(title);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    mMenuRecyclerView.startDrag(this);
                    break;
                }
            }
            return false;
        }

    }

}
