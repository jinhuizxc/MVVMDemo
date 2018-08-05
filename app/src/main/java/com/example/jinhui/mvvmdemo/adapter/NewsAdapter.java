package com.example.jinhui.mvvmdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.example.jinhui.mvvmdemo.BR;
import com.example.jinhui.mvvmdemo.R;
import com.example.jinhui.mvvmdemo.base.BaseAdapter;
import com.example.jinhui.mvvmdemo.base.BaseViewHolder;
import com.example.jinhui.mvvmdemo.bean.SimpleNewsBean;
import com.example.jinhui.mvvmdemo.utils.ToastUtils;

/**
 * 新闻适配器列表
 */
public class NewsAdapter extends BaseAdapter<SimpleNewsBean, BaseViewHolder>{

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup viewGroup, int i) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_news, viewGroup, false);
        return new BaseViewHolder<>(dataBinding);
    }

    @Override
    protected void onBindVH(BaseViewHolder baseViewHolder, int i) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.simpleNewsBean, mList.get(i));
        binding.setVariable(BR.position, i);
        binding.setVariable(BR.adapter, this);
        // 防止闪烁
        binding.executePendingBindings();
    }

    /**
     * 点赞
     *
     * @param simpleNewsBean
     * @param position
     */
    public void clickDianZan(SimpleNewsBean simpleNewsBean, int position){
        if (simpleNewsBean.isGood.get()){
            simpleNewsBean.isGood.set(false);
            ToastUtils.show(mContext, "取消点赞 position=" + position);
        } else {
            simpleNewsBean.isGood.set(true);
            ToastUtils.show(mContext, "点赞成功 position=" + position);
        }
    }
}
