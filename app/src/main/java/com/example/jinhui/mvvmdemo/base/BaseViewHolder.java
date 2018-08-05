package com.example.jinhui.mvvmdemo.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    /**
     * ViewDataBinding
     */
    private B mBinding;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }
}
