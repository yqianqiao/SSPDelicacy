package cn.com.quickpark.sspdelicacy.adapter.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by songdongliang on 2016/11/15.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public BindingViewHolder(T viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.mBinding = viewDataBinding;
    }

    public T getBinding() {
        return mBinding;
    }


}
