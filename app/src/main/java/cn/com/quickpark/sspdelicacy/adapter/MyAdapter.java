package cn.com.quickpark.sspdelicacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.adapter.base.BindingViewHolder;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.databinding.ItemMyCarBinding;
import cn.com.quickpark.sspdelicacy.ui.my.carmanage.CarManageActivity;

/**
 * Created by y on 2018/7/10.
 */

public class MyAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemMyCarBinding>> {
    private Context context;
    private ArrayList<MyInfoBean.CarListBean> list;

    public MyAdapter(Context context, ArrayList<MyInfoBean.CarListBean> list) {
        this.context = context;
        this.list = list;
    }

//    public void setDataList(ArrayList<MyInfoBean.CarListBean> list) {
//        this.list = list;
//
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public BindingViewHolder<ItemMyCarBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_my_car, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemMyCarBinding> holder, int position) {
        ItemMyCarBinding binding = holder.getBinding();
        binding.tvLicense.setText(list.get(position).getLicense());
        if (TextUtils.isEmpty(list.get(position).getColorName())) {
            binding.ivSign.setVisibility(View.GONE);
        } else {
            binding.ivSign.setVisibility(View.VISIBLE);
            binding.ivSign.setColorFilter(Color.parseColor(list.get(position).getColorName()));
        }
        binding.clItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarManageActivity.class);
            Bundle bundle = new Bundle();
           // bundle.putParcelableArrayList(Constants.USER_CAR_LIST, list);
            bundle.putSerializable(Constants.USER_CAR_LIST, list);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
