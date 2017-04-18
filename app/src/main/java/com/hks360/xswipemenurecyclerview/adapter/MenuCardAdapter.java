package com.hks360.xswipemenurecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hks360.library.mtech.adapter.BaseListSwipeMenuAdapter;
import com.hks360.library.mtech.holder.RecyclerHolder;
import com.hks360.library.mtech.listener.CardItemClickListener;
import com.hks360.library.mtech.listener.OnItemClickListener;
import com.hks360.library.mtech.swipe.SwipeMenuLayout;
import com.hks360.xswipemenurecyclerview.R;
import com.hks360.xswipemenurecyclerview.util.CommonUtil;
import com.hks360.xswipemenurecyclerview.util.UIUtil;

import java.util.List;

/**
 * Created by xiaote on 2017/4/17.
 */

public class MenuCardAdapter extends BaseListSwipeMenuAdapter<String, MenuCardAdapter.DefaultViewHolder> {
    private CardItemClickListener onItemClickListener;

    public void setOnItemClickListener(CardItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MenuCardAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_menu_card;
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
        viewHolder.setContext(realContentView.getContext());
        Log.d("test","onItemClickListener-->" + onItemClickListener);
        viewHolder.setOnItemClickListener(onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onCompatBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    static class DefaultViewHolder extends RecyclerHolder implements View.OnClickListener {
        private SwipeMenuLayout swipeMenuLayout;
        private TextView tvTitle;
        private Button leftBtn;
        private Button rightBtn;
        private Context context;
        private CardItemClickListener onItemClickListener;

        public void setOnItemClickListener(CardItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public DefaultViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            leftBtn = UIUtil.findViewById(itemView, R.id.left_view);
            rightBtn = UIUtil.findViewById(itemView, R.id.right_view);
            swipeMenuLayout = UIUtil.findViewById(itemView,R.id.swipe_layout);
            swipeMenuLayout.setOnClickListener(this);
            leftBtn.setOnClickListener(this);
            rightBtn.setOnClickListener(this);
        }

        public void setData(String title) {
            this.tvTitle.setText(title);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.swipe_layout:
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                    break;
                case R.id.left_view:
                    swipeMenuLayout.smoothCloseMenu();
                    CommonUtil.showToast(context, "我是左侧Button,点击Item位置为" + getAdapterPosition());
                    break;
                case R.id.right_view:
                    swipeMenuLayout.smoothCloseMenu();
                    CommonUtil.showToast(context, "我是右侧Button,点击Item位置为" + getAdapterPosition());
                    break;
            }
        }
    }

}
