package com.hz.zxk.lib_commonframe.widget.wrapRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhengxiaoke
 * @date 2019-06-10 11:39
 * 能添加头部/底部的RecylerView
 */
public class WrapRecyclerView extends RecyclerView {
    private WrapRecyclerAdapter mWrapRecyclerAdapter;

    private Adapter mAdapter;

    public WrapRecyclerView(@NonNull Context context) {
        super(context);
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if(mAdapter!=null){
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
            mAdapter=null;
        }

        this.mAdapter=adapter;
        if(adapter instanceof WrapRecyclerAdapter){
            mWrapRecyclerAdapter=(WrapRecyclerAdapter)adapter;
        }else{
            mWrapRecyclerAdapter=new WrapRecyclerAdapter(adapter);
        }
        super.setAdapter(mWrapRecyclerAdapter);

        mAdapter.registerAdapterDataObserver(mDataObserver);
    }

    /**
     * 添加头部
     * @param view
     */
    public void addHeaderView(View view){
        if(mWrapRecyclerAdapter!=null){
            mWrapRecyclerAdapter.addHeaderView(view);
        }
    }

    /**
     * 添加底部
     * @param view
     */
    public void addFooterView(View view){
        if(mWrapRecyclerAdapter!=null){
            mWrapRecyclerAdapter.addFooterView(view);
        }
    }

    /**
     * 删除头部
     * @param view
     */
    public void removeHeaderView(View view){
        if(mWrapRecyclerAdapter!=null){
            mWrapRecyclerAdapter.removeHeaderView(view);
        }
    }

    /**
     * 删除头部
     * @param view
     */
    public void removeFooterView(View view){
        if(mWrapRecyclerAdapter!=null){
            mWrapRecyclerAdapter.removeFooterView(view);
        }
    }

    private AdapterDataObserver mDataObserver=new AdapterDataObserver() {
        @Override
        public void onChanged() {
            if(mAdapter==null) return;
            if(mWrapRecyclerAdapter!=null){
                mWrapRecyclerAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            if(mAdapter==null) return;
            if(mWrapRecyclerAdapter!=null){
                mWrapRecyclerAdapter.notifyItemRangeChanged(positionStart,itemCount);
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            if(mAdapter==null) return;
            if(mWrapRecyclerAdapter!=null){
                mWrapRecyclerAdapter.notifyItemRangeChanged(positionStart,itemCount,payload);
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            if(mAdapter==null) return;
            if(mWrapRecyclerAdapter!=null){
                mWrapRecyclerAdapter.notifyItemRangeInserted(positionStart,itemCount);
            }
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if(mAdapter==null) return;
            if(mWrapRecyclerAdapter!=null){
                mWrapRecyclerAdapter.notifyItemRangeRemoved(positionStart,itemCount);
            }

        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if(mAdapter==null) return;
            if(mWrapRecyclerAdapter!=null){
                mWrapRecyclerAdapter.notifyItemMoved(fromPosition,toPosition);
            }
        }
    };
}
