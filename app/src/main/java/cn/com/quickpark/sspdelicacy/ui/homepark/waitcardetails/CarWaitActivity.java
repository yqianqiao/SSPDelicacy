package cn.com.quickpark.sspdelicacy.ui.homepark.waitcardetails;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.MVPBaseActivity;
import cn.com.quickpark.sspdelicacy.bean.homepark.ParkCarWaitBean;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.databinding.ActivityTakeCarWaitBinding;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;
import cn.com.quickpark.sspdelicacy.utils.TimeUtil;

/**
 * 取车等待
 */
public class CarWaitActivity extends MVPBaseActivity<ActivityTakeCarWaitBinding, CarWaitContract.Presenter>
        implements CarWaitContract.View, View.OnClickListener {

    private StringBuilder license;

    @Override
    protected void initDate() {
        //获取网络数据
        mPresenter.parkCarWait();
        bindingView.tvRevoke.setOnClickListener(this);
        mBaseBinding.toolbarTitle.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void initView() {
        setToolbarTitle(getLicense());
        mBaseBinding.toolbarTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                ContextCompat.getDrawable(this, R.mipmap.ic_drop_down), null);
//        mBaseBinding.toolbarTitle.setCompoundDrawablePadding(20);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_take_car_wait;
    }

    @Override
    protected CarWaitContract.Presenter createPresenter() {
        return new CarWaitPresenter();
    }

    @Override
    public String getLicense() {
        if (license != null && !TextUtils.isEmpty(license.toString())) {
            return license.toString();
        }
        return getBundle().getString(Constants.LICENSE);
    }


    @Override
    public void setShowViewData(ParkCarWaitBean bean) {
        bindingView.mScheduleLine.setTextList(bean.getState());
        bindingView.mScheduleLine.setPosition(bean.getParkStatus());
        bindingView.tvLicense.setText(bean.getLicense());
        bindingView.tvExpectedTime.setText(TimeUtil.formatHourAndMinute(bean.getExpectedTime()));
        bindingView.tvEntrance.setText(bean.getEntrance());
        bindingView.tvRevoke.setVisibility(bean.getParkStatus() >= 1 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void switchCar(List<String> carList) {
        new MaterialDialog.Builder(this)
                .title("请选择")
                .titleGravity(GravityEnum.CENTER)
                .items(carList)
                .itemsCallback((dialog, itemView, position, text) -> {
                    setToolbarTitle(text.toString());
                    if (license == null) {
                        license = new StringBuilder();
                    }
                    license.delete(0, license.length());
                    license.append(text);

                    mPresenter.parkCarWait();
                })
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_revoke:
                new MaterialDialog.Builder(this)
                        .title(getString(R.string.dialog_title))
                        .content("是否撤销存车？")
                        .negativeText("再想想")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> mPresenter.revoke())
                        .show();
                break;

            case R.id.toolbar_title:
                mPresenter.switchCar();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RxBus.getInstance().post(RxCodeConstants.NOTICE, 1);
    }


}
