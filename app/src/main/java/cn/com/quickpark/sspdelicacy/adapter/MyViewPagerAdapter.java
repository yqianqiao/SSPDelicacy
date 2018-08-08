package cn.com.quickpark.sspdelicacy.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.databinding.ItemCarInfoViewpagerBinding;

/**
 * Created by y on 2018/7/18.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<MyInfoBean.CarListBean> list;
    private LayoutInflater mInflater;

    public MyViewPagerAdapter(Context context, List<MyInfoBean.CarListBean> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 得到数据的总条数
     *
     * @return 总条数
     */
    @Override
    public int getCount() {

        return list.size();
    }

    /**
     * 表示当前ViewPager展示的view对象是否是instantiateItem创建的对象（即是否是同一对象）
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 根据指定下标创建ViewPager展示的item视图
     *
     * @param container 管理当前ViewPager的视图组
     * @param position  指定的下标
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //将指定view对象存储到视图组中
        MyInfoBean.CarListBean car = list.get(position);
        ItemCarInfoViewpagerBinding dataBinding = DataBindingUtil.inflate(mInflater, R.layout.item_car_info_viewpager, null, false);
        Glide.with(context)
                .load(car.getCarbgurl())
                .error(R.mipmap.bg_car_detail)
                .into(dataBinding.ivCarBg);
        Glide.with(context)
                .load(car.getCarseriespic())
                .error(R.mipmap.ic_launcher_round)
                .into(dataBinding.ivCarLogo);
        dataBinding.tvLicense.setText(car.getLicense());
        dataBinding.tvModel.setText(car.getCarseries());

        container.addView(dataBinding.getRoot());
        return dataBinding.getRoot();
    }

    /**
     * 根据指定下标移除视图组中view对象
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //要使滑到最后一张程序不崩，需注释掉此行
        //  super.destroyItem(container, position, object);
        //从视图组中移除指定下标的view对象
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        // 最简单解决 notifyDataSetChanged() 页面不刷新问题的方法
        return POSITION_NONE;
    }
}
