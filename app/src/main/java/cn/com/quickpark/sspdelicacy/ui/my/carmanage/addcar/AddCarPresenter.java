package cn.com.quickpark.sspdelicacy.ui.my.carmanage.addcar;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;
import cn.com.quickpark.sspdelicacy.httpvo.AddCarVo;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxBusBaseMessage;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;

/**
 * Created by y on 2018/7/23.
 */

public class AddCarPresenter extends AddCarContract.Presenter {

    private AddCarContract.View view;

    @Override
    public AddCarContract.Model getModel() {
        return new AddCarModel();
    }

    @Override
    void addCar() {
        view = getView();
        if (view == null || mModel == null) return;
        AddCarVo addCarVo = view.getAddCarVo();
        mModel.addCar(addCarVo)
                .compose(view.bindToLife())
                .subscribe(new MyObserver<BaseResult>() {
                    @Override
                    public void onSuccess(BaseResult baseResult) {
                        view.showToast("添加成功");
                        MyInfoBean.CarListBean bean = new MyInfoBean.CarListBean();
                        bean.setCarseriespic(addCarVo.getCarseriespic());
                        bean.setCarseries(addCarVo.getCarseries());
                        bean.setColourCard(addCarVo.getColor());
                        bean.setLicense(addCarVo.getLicense());
                        RxBus.getInstance().post(RxCodeConstants.CAR_MANAGE, new RxBusBaseMessage(RxCodeConstants.CAR_ADD, bean));
                        view.finishActivity();
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
    }
}
