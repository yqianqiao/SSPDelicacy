package cn.com.quickpark.sspdelicacy.ui.my.carmanage.addcar;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.CompoundButton;

import java.util.concurrent.TimeUnit;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.BaseActivity;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.MVPBaseActivity;
import cn.com.quickpark.sspdelicacy.constants.LoginSession;
import cn.com.quickpark.sspdelicacy.constants.User;
import cn.com.quickpark.sspdelicacy.databinding.ActivityAddCarBinding;
import cn.com.quickpark.sspdelicacy.httpvo.AddCarVo;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;
import cn.com.quickpark.sspdelicacy.utils.ToastUtil;
import cn.com.quickpark.sspdelicacy.view.CustomPlateView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class AddCarActivity extends MVPBaseActivity<ActivityAddCarBinding, AddCarContract.Presenter>
        implements AddCarContract.View, View.OnClickListener {


    private CustomPlateView viewPlate;
    private String carBrand;
    private String carBrandUrl;

    @Override
    protected void initDate() {
        bindingView.tvConfirm.setOnClickListener(this);
        bindingView.viewBg.setOnClickListener(this);
        viewPlate = bindingView.viewPlate;
    }

    @Override
    protected void initView() {
        setToolbarTitle("新增车辆");
        bindingView.cbNev.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewPlate.showKeyboard();
            viewPlate.changeNewEnergyCar(isChecked);
        });
        Observable.timer(600, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> viewPlate.showKeyboard());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_car;
    }

    @Override
    protected AddCarContract.Presenter createPresenter() {
        return new AddCarPresenter();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initRxBus() {
        RxBus.getInstance().toObservable(RxCodeConstants.CAR_BRAND, String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLife())
                .subscribe(s -> {
                    String[] split = s.split(",");
                    carBrand = split[0];
                    carBrandUrl = split[1];
                    bindingView.tvCarBrand.setText(carBrand);

                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bindingView.viewPlate.hideKeyboard();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_bg:
                startNewActivity(CarBrandActivity.class);
                break;
            case R.id.tv_confirm:
                mPresenter.addCar();
                break;
        }
    }

    @Override
    public AddCarVo getAddCarVo() {
        User user = LoginSession.getSession().getUser();
        AddCarVo carVo = new AddCarVo();
        carVo.setCarseries(carBrand);
        carVo.setCarseriespic(carBrandUrl);
        carVo.setColor("#FF000000");
        carVo.setLicense(viewPlate.getLicense());
        carVo.setToken(user.getToken());
        carVo.setUsername(user.getLoginName());
        //carVo.setCarseries();
        return carVo;
    }
}
