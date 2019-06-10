package com.hz.zxk.lib_commonframe.widget.wrapRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author zhengxiaoke
 * @date 2019-06-09 22:44
 */
public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter mRealAdapter;

    private SparseArray<View> mHeaderViews;
    private SparseArray<View> mFooterViews;

    private static int BASE_ITEM_TYPE_HEADER=1000000;
    private static int BASE_ITEM_TYPE_FOOTER=2000000;

    public WrapRecyclerAdapter(RecyclerView.Adapter realAdapter){
        this.mRealAdapter=realAdapter;
        mHeaderViews=new SparseArray<>();
        mFooterViews=new SparseArray<>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //判断是否是头部
        if(isHeaderViewType(viewType)){
            View headerView=mHeaderViews.get(viewType);
            return createHeaderFooterHolder(headerView);
        }
        //判断是否是底部
        if(isFooterViewType(viewType)){
            View footerView=mFooterViews.get(viewType);
            return createHeaderFooterHolder(footerView);
        }

        //返回Adapter的onCreateViewHolder
        return mRealAdapter.onCreateViewHolder(viewGroup,viewType);
    }

    private RecyclerView.ViewHolder createHeaderFooterHolder(View view){
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
       if(isHeaderPosition(position)||isFooterPosition(position)){
           return;
       }
       position=position-mHeaderViews.size();
       mRealAdapter.onBindViewHolder(viewHolder,position);
    }


    @Override
    public int getItemViewType(int position) {
        if(isHeaderPosition(position)){
            //头部 直接返回position
            return mHeaderViews.keyAt(position);
        }else if(isFooterPosition(position)){
            //底部 position减去头部的数量和列表Adapter的数量
            position=position-mHeaderViews.size()-mRealAdapter.getItemCount();
            return mFooterViews.keyAt(position);
        }
        //返回Adpater的getItemViewType
        return mRealAdapter.getItemViewType(position-mHeaderViews.size());
    }

    @Override
    public int getItemCount() {
        return mRealAdapter.getItemCount()+mHeaderViews.size()+mFooterViews.size();
    }

    /**
     * 判断是否是头部位置
     * @param position
     * @return
     */
    private boolean isHeaderPosition(int position){
        return position<mHeaderViews.size();
    }

    /**
     * 判断是否是底部位置
     * @param position
     * @return
     */
    private boolean isFooterPosition(int position){
        return position>=(mHeaderViews.size()+mRealAdapter.getItemCount());
    }

    /**
     * 是否是头部类型
     * @param viewType
     * @return
     */
    private boolean isHeaderViewType(int viewType){
        int position=mHeaderViews.indexOfKey(viewType);
        return position>=0;
    }

    /**
     * 判断是否是底部类型
     * @param viewType
     * @return
     */
    private boolean isFooterViewType(int viewType){
        int position=mFooterViews.indexOfKey(viewType);
        return position>=0;
    }

    /**
     * 返回headerView的数量
     * @return
     */
    public int getHeaderCount(){
        return mHeaderViews.size();
    }

    /**
     * 返回footerView的数量
     * @return
     */
    public int getFooterCount(){
        return mFooterViews.size();
    }

    /**
     * 添加头部
     * @param view
     */
    public void addHeaderView(View view){
        int position=mHeaderViews.indexOfValue(view);
        if(position<0){
            mHeaderViews.put(BASE_ITEM_TYPE_HEADER++,view);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除头部
     * @param view
     */
    public void removeHeaderView(View view){
       int index=mHeaderViews.indexOfValue(view);
       if(index<0) return;
       mHeaderViews.removeAt(index);
       notifyDataSetChanged();
    }

    /**
     * 添加底部
     * @param view
     */
    public void addFooterView(View view){
        int position=mFooterViews.indexOfValue(view);
        if(position<0){
            mFooterViews.put(BASE_ITEM_TYPE_FOOTER++,view);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除底部
     * @param view
     */
    public void removeFooterView(View view){
        int index=mFooterViews.indexOfValue(view);
        if(index<0) return;
        mFooterViews.removeAt(index);
        notifyDataSetChanged();
    }

    /**
     * 解决Header和Footer没有占一行的问题
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        mRealAdapter.onAttachedToRecyclerView(recyclerView);

        final RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();

        if(layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager=(GridLayoutManager)layoutManager;
            GridLayoutManager.SpanSizeLookup spanSizeLookup=gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    boolean isHeaderOrFooter=isHeaderPosition(position)|| isFooterPosition(position);
                    return isHeaderOrFooter?gridLayoutManager.getSpanCount():1;
                }
            });
        }

    }
}
