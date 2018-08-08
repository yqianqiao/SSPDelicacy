package cn.com.quickpark.sspdelicacy.ui.homepark.waitcardetails;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.com.quickpark.sspdelicacy.bean.BaseListResult;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.homepark.ParkCarWaitBean;
import cn.com.quickpark.sspdelicacy.bean.homepark.QueryAllCarBean;
import cn.com.quickpark.sspdelicacy.constants.LoginSession;
import cn.com.quickpark.sspdelicacy.constants.User;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * Created by y on 2018/7/13.
 */

public class CarWaitPresenter extends CarWaitContract.Presenter {

    private CarWaitContract.View view;
    private int parkStatus;
    private List<String> list;

    @Override
    public CarWaitContract.Model getModel() {
        return new CarWaitModel();
    }

    @Override
    void parkCarWait() {
        view = getView();
        if (getView() == null || mModel == null) return;
        mModel.parkCarWait(view.getLicense())
                .compose(view.bindToLife())
                .subscribe(new MyObserver<ParkCarWaitBean>() {
                    @Override
                    public void onSuccess(ParkCarWaitBean parkCarWaitBean) {
                        view.setShowViewData(parkCarWaitBean);
                        parkStatus = parkCarWaitBean.getParkStatus();
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
//                .compose(view.<BaseResult<ParkCarWaitBean>>bindToLife())
//                .subscribe(new MyObserver<BaseResult<ParkCarWaitBean>>() {
//                    @Override
//                    public void onSuccess(BaseResult<ParkCarWaitBean> parkCarWaitBeanBaseResult) {
//                        view.setShowViewData(parkCarWaitBeanBaseResult.getResult());
//                        parkStatus = parkCarWaitBeanBaseResult.getResult().getParkStatus();
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//                        view.showToast(msg);
//                    }
//                });
    }

    @Override
    void revoke() {
        view = getView();
        if (getView() == null || mModel == null) return;
        if (parkStatus >= 1) {
            view.showToast("车辆已入库，不可撤销");
            return;
        }
        mModel.revoke(view.getLicense())
                .compose(view.bindToLife())
                .subscribe(new MyObserver<BaseResult>() {
                    @Override
                    public void onSuccess(BaseResult baseResult) {
                        view.showToast("撤销成功");
                        RxBus.getInstance().post(RxCodeConstants.NOTICE, 1);
                        view.finishActivity();
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
    }

    @Override
    void switchCar() {
        view = getView();
        if (getView() == null || mModel == null) return;
        User user = LoginSession.getSession().getUser();
        mModel.queryAllCar(user.getLoginName(), user.getToken(), true)
                .compose(view.bindToLife())
                .flatMap(queryAllCarBeans -> {
                    list = new ArrayList<>();
                    for (int i = 0; i < queryAllCarBeans.size(); i++) {
                        list.add(queryAllCarBeans.get(i).getLicense());
                    }
                    return Observable.just(list);
                })
                .subscribe(new MyObserver<List<String>>() {
                    @Override
                    public void onSuccess(List<String> list) {
                        view.switchCar(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
//                .flatMap(queryAllCarBeanBaseListResult -> {
//                    result = queryAllCarBeanBaseListResult.getResult();
//                    list = new ArrayList<>();
//                    for (int i = 0; i < result.size(); i++) {
//                        list.add(result.get(i).getLicense());
//                    }
//                    return Observable.just(list);
//                })
//                .subscribe(new MyObserver<List<String>>() {
//                    @Override
//                    public void onSuccess(List<String> list) {
//                        view.switchCar(list);
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//                        view.showToast(msg);
//                    }
//                });
    }
}
