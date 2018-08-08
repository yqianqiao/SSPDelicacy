package cn.com.quickpark.sspdelicacy.adapter.my;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.adapter.base.BindingViewHolder;
import cn.com.quickpark.sspdelicacy.bean.my.CarBrandModel;
import cn.com.quickpark.sspdelicacy.bean.my.CarModelType;
import cn.com.quickpark.sspdelicacy.databinding.ItemCarBrandBinding;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxBusBaseMessage;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;
import cn.com.quickpark.sspdelicacy.ui.my.carmanage.addcar.CarBrandActivity;
import cn.com.quickpark.sspdelicacy.utils.ToastUtil;

/**
 * Created by y on 2018/7/23.
 */

public class CarBrandAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemCarBrandBinding>> {
    private CarBrandActivity activity;
    private ArrayList<CarBrandModel> mCarBrandModels;
    private ArrayList<String> mList;

    public CarBrandAdapter(CarBrandActivity activity, ArrayList<CarBrandModel> mCarBrandModels) {
        this.activity = activity;
        this.mCarBrandModels = mCarBrandModels;
    }

    @NonNull
    @Override
    public BindingViewHolder<ItemCarBrandBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_car_brand,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemCarBrandBinding> holder, int position) {
        ItemCarBrandBinding binding = holder.getBinding();
        CarBrandModel carBrandModel = mCarBrandModels.get(position);
        binding.tvBrand.setText(carBrandModel.getCartypename());
        Glide.with(activity).load(carBrandModel.getBrandlogourl()).into(binding.ivLogo);
        binding.clItem.setOnClickListener(v -> {
            mList = new ArrayList<>();
            mList.clear();
            for (CarModelType bean : carBrandModel.getBtiList()) {
                mList.add(bean.getBrandtype());
            }
            new MaterialDialog.Builder(activity)
                    .icon(binding.ivLogo.getDrawable())
                    .title(carBrandModel.getCartypename())
                    .items(mList)
                    .itemsCallback((dialog, itemView, position1, text) -> {
                        RxBus.getInstance().post(new RxBusBaseMessage(RxCodeConstants.CAR_BRAND, carBrandModel.getCartypename() + "-" + text + "," + carBrandModel.getBrandlogourl()));
                        activity.finishActivity();
                    }).show();
        });

    }

    @Override
    public int getItemCount() {
        return mCarBrandModels.size();
    }
}
