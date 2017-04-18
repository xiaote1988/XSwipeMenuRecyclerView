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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hks360.library.mtech.adapter.BaseListSwipeMenuAdapter;
import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.xswipemenurecyclerview.R;

import java.util.List;

public class MenuAdapter extends BaseListSwipeMenuAdapter<String,MenuAdapter.DefaultViewHolder> {

    public MenuAdapter() {
        super();
    }

    @Override
    public void onCompatBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    public MenuAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_xrecyclerview;
    }

    @Override
    public MenuAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
        return viewHolder;
    }

    static class DefaultViewHolder extends RecyclerHolder {
        TextView tvTitle;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setData(String title) {
            this.tvTitle.setText(title);
        }

    }

}
