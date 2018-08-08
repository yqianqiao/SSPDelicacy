package cn.com.quickpark.sspdelicacy.adapter.homepark;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.orhanobut.logger.Logger;

import java.util.List;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.adapter.base.BindingViewHolder;
import cn.com.quickpark.sspdelicacy.bean.homepark.HomeGarageBean;
import cn.com.quickpark.sspdelicacy.databinding.ItemHomeGarageBinding;

/**
 * Created by y on 2018/7/6.
 */

public class HomeGarageAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemHomeGarageBinding>> {

    private Context context;
    private List<HomeGarageBean> mList;
    private int height;

    public HomeGarageAdapter(Context context, List<HomeGarageBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BindingViewHolder<ItemHomeGarageBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingViewHolder<>((ItemHomeGarageBinding) DataBindingUtil.inflate(LayoutInflater.from(context)
                , R.layout.item_home_garage, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemHomeGarageBinding> holder, int position) {
        ItemHomeGarageBinding binding = holder.getBinding();
        final View tvGarage = binding.tvGarage;

        ViewTreeObserver vto = tvGarage.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvGarage.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = tvGarage.getHeight();

            }
        });
//        int width = tvGarage.getMeasuredWidth();

        Logger.e("height  " + height);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        params.height = (int) (mList.get(position).getQuantity() / (float)mList.get(position).getTotal() * height);
        Logger.e("params.height  " + params.height);
        tvGarage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
