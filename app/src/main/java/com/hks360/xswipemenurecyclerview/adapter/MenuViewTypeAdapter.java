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

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hks360.library.mtech.adapter.BaseListSwipeMenuAdapter;
import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.library.mtech.swipe.SwipeMenuAdapter;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.entity.ViewTypeBean;

import java.util.List;

public class MenuViewTypeAdapter extends BaseListSwipeMenuAdapter<ViewTypeBean, MenuViewTypeAdapter.DefaultViewHolder> {

    public static final int VIEW_TYPE_MENU_NONE = 1;
    public static final int VIEW_TYPE_MENU_SINGLE = 2;
    public static final int VIEW_TYPE_MENU_MULTI = 3;
    public static final int VIEW_TYPE_MENU_LEFT = 4;
    public static final int VIEW_TYPE_MENU_RIGHT = 5;

    public MenuViewTypeAdapter(List<ViewTypeBean> dataList) {
        super(dataList);
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getViewType();
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_xrecyclerview;
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onCompatBindViewHolder(DefaultViewHolder holder, int position) {
        holder.tvTitle.setText(dataList.get(position).getContent());
    }

    static class DefaultViewHolder extends RecyclerHolder {
        TextView tvTitle;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setData(ViewTypeBean viewTypeBean) {
            this.tvTitle.setText(viewTypeBean.getContent());
        }

    }

}
