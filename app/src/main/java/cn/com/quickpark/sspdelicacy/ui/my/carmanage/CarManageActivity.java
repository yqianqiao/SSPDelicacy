package cn.com.quickpark.sspdelicacy.ui.my.carmanage;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.adapter.MyViewPagerAdapter;
import cn.com.quickpark.sspdelicacy.base.MVPBaseActivity;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.databinding.ActivityCarManageBinding;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxBusBaseMessage;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;
import cn.com.quickpark.sspdelicacy.ui.my.carmanage.addcar.AddCarActivity;
import cn.com.quickpark.sspdelicacy.utils.VerifyUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class CarManageActivity extends MVPBaseActivity<ActivityCarManageBinding, CarManageContract.Presenter>
        implements CarManageContract.View, View.OnClickListener {

    private ArrayList<MyInfoBean.CarListBean> carList;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private View view;
    private LinearLayout layoutIndicator;
    private int mNum;
    private MyInfoBean.CarListBean car;
    private int indexSwitch; //上一次点击的switch 的下标
    private boolean isDefault;//是否默认车
    private String color; //车身颜色


    @Override
    protected void initDate() {
        carList = (ArrayList<MyInfoBean.CarListBean>) getBundle().getSerializable(Constants.USER_CAR_LIST);
        if (carList == null) {
            startNewActivity(AddCarActivity.class);
            return;
        }
        showCarColor(carList.get(0).getColourCard());
        bindingView.carSwitch.setChecked(carList.get(0).getIsDefaultCar() != 0);


        adapter = new MyViewPagerAdapter(this, carList);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getIsDefaultCar() == 1) {
                indexSwitch = i;
            }

            //创建底部指示器(小圆点)
            view = new View(this);
            view.setBackgroundResource(R.drawable.img_dot_select);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            //设置间隔
//            if (pic != mPics[0]) {
            layoutParams.leftMargin = 10;
            //  }
            //添加到LinearLayout
            layoutIndicator.addView(view, layoutParams);
        }
        layoutIndicator.getChildAt(0).setEnabled(true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                layoutIndicator.getChildAt(mNum).setEnabled(false);
                layoutIndicator.getChildAt(position).setEnabled(true);
                mNum = position;
                car = carList.get(position);
                showCarColor(car.getColourCard());
                bindingView.carSwitch.setChecked(car.getIsDefaultCar() != 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bindingView.carSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isPressed()) {
                isDefault = isChecked;
                mPresenter.setDefaultCar(isChecked);
            }
        });

        bindingView.viewCarColor.setOnClickListener(this);
        bindingView.tvDelete.setOnClickListener(this);

    }


    @Override
    protected void initView() {
        setToolbarTitle("车辆管理");
        addToolbarMenu(R.menu.car_manage_add);
        setToolBarMenuOnclick(item -> {
            startNewActivity(AddCarActivity.class);
            return false;
        });

        viewPager = bindingView.viewPager;
        layoutIndicator = bindingView.layoutIndicator;

    }

    @Override
    protected void initRxBus() {
        RxBus.getInstance().toObservable(RxCodeConstants.CAR_MANAGE, RxBusBaseMessage.class)
                .compose(bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rxBusBaseMessage -> {
                    if (rxBusBaseMessage.getCode() == RxCodeConstants.CAR_COLOR) {
                        color = (String) rxBusBaseMessage.getObject();
                        mPresenter.upCarColor(color);
                    } else if (rxBusBaseMessage.getCode() == RxCodeConstants.CAR_ADD) {
                        carList.add((MyInfoBean.CarListBean) rxBusBaseMessage.getObject());
                        // adapter.addList((MyInfoBean.CarListBean) rxBusBaseMessage.getObject());
                        adapter.notifyDataSetChanged();
                        //添加小圆点
                        view = new View(this);
                        view.setBackgroundResource(R.drawable.img_dot_select);
                        view.setEnabled(false);
                        //设置宽高
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
                        //设置间隔
                        layoutParams.leftMargin = 10;
                        //添加到LinearLayout
                        layoutIndicator.addView(view, layoutParams);
                    }

                });
    }

    @Override
    protected CarManageContract.Presenter createPresenter() {
        return new CarManagePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_manage;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_car_color:
                Bundle bundle = new Bundle();
                bundle.putString(Constants.CAR_COLOR, carList.get(0).getColorName());
                startNewActivity(CarColorActivity.class, bundle);
                break;
            case R.id.tv_delete:
                new MaterialDialog.Builder(this)
                        .title(getString(R.string.dialog_title))
                        .content("是否删除车辆")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> mPresenter.deleteCar())
                        .show();
                break;

        }
    }


    public void showCarColor(String color) {

        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        int width = (int) getResources().getDimension(R.dimen.dp24);
        shapeDrawable.setIntrinsicWidth(width);
        shapeDrawable.setIntrinsicHeight(width);
        if (VerifyUtil.isColor(color)) {
            shapeDrawable.getPaint().setColor(Color.parseColor(color));
        } else {
            shapeDrawable.getPaint().setColor(Color.parseColor("#FF000000"));
        }
        bindingView.ivCarColor.setImageDrawable(shapeDrawable);


    }

    @NonNull
    @Override
    public String getLicense() {
        return carList.get(mNum).getLicense();
    }

    @Override
    public void setUpDataCar(int type) {
        switch (type) {
            case 0:
                carList.get(mNum).setColourCard(color);
                showCarColor(color);
                break;
            case 1:
                setUpDefaultCar();
                break;
            case 2:
                carList.remove(mNum);
                adapter.notifyDataSetChanged();
                Logger.e(layoutIndicator.getChildCount() + "");
                layoutIndicator.removeViewAt(layoutIndicator.getChildCount() - 1);

                break;
        }
    }


    public void setUpDefaultCar() {
        carList.get(mNum).setIsDefaultCar(isDefault ? 1 : 0);
        if (mNum != indexSwitch) {
            carList.get(indexSwitch).setIsDefaultCar(0);
        }
        viewPager.setCurrentItem(mNum);
        adapter.notifyDataSetChanged();

        indexSwitch = mNum;
    }

}
