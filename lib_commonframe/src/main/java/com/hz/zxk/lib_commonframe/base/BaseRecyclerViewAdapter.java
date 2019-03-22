package com.hz.zxk.lib_commonframe.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengxiaoke
 * @date 2019/3/22 4:26 PM
 */
public abstract class BaseRecyclerViewAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> datas;
    private OnItemClickListener<T> mOnItemClickListener;
    private LayoutInflater inflater;
    private Context context;

    public BaseRecyclerViewAdapter(Context context){
       this(context,new ArrayList<T>());
    }

    public BaseRecyclerViewAdapter(Context context,List<T> datas){
        this.datas=datas;
        inflater=LayoutInflater.from(context);
    }

    /**
     * 刷新数据
     * @param datas
     */
    public void refreshDatas(List<T> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

    /**
     * 批量加入数据
     * @param datas
     */
    public void addDatas(List<T> datas){
        if(this.datas!=null&&datas!=null) {
            int lastDatasSize=this.datas.size()-1;
            this.datas.addAll(datas);
            notifyItemRangeInserted(lastDatasSize,datas.size());
        }
    }

    /**
     * 加入数据
     * @param data
     */
    public void addData(T data){
        if(this.datas!=null && data!=null){
            int lastDatasSize=this.datas.size()-1;
            this.datas.add(data);
            notifyItemInserted(lastDatasSize);
        }
    }

    public void setmOnItemClickListener(OnItemClickListener<T> mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent,inflater,viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        final View view=holder.itemView;
        if(view!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(view,position,datas.get(position));
                    }
                }
            });
        }
        onBind(holder,position);
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    /**
     * 获取单个数据
     * @param position
     * @return
     */
    public T getItem(int position){
        return datas==null?null:datas.get(position);
    }

    protected abstract VH getViewHolder(ViewGroup parent,LayoutInflater inflater, int viewType);

    protected abstract void onBind(VH holder, int position);

    public static interface OnItemClickListener<T>{
        void onItemClick(View view,int position,T data);
    }
}
