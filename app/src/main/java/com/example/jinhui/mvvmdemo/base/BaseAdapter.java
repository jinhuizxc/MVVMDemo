package com.example.jinhui.mvvmdemo.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jinhui.mvvmdemo.bean.SimpleNewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 基类BaseAdapter
 * 数据T、 VH :viewHolder
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    protected Context mContext;
    protected List<T> mList; // 数据源
    protected LayoutInflater inflater;

    public BaseAdapter(Context context){
        this.mContext = context;
        this.mList = new ArrayList<>();
        inflater = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return onCreateVH(viewGroup, i);
    }

    /**
     * 创建 View Holder, (抽象方法不能私有private, 会报错)
     * @param viewGroup
     * @param i
     * @return
     */
    public abstract VH onCreateVH(ViewGroup viewGroup, int i);

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        onBindVH(vh, i);
    }

    /**
     * 绑定 View Holder
     * @param vh
     * @param i
     */
    protected abstract void onBindVH(VH vh, int i);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 加载更多
     *
     * @param data 加载的新数据
     */
    public void loadMoreData(List<T> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     *
     * @param data 数据源
     */
    public void refreshData(List<T> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }
}
