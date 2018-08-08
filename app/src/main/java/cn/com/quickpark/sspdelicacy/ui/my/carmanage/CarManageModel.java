package cn.com.quickpark.sspdelicacy.ui.my.carmanage;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IMy;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/12.
 */

public class CarManageModel implements CarManageContract.Model {

    @Override
    public Observable<BaseResult> upCarColor(String license, String token, String colorCard) {
        return HttpMethods.getInstance().createService(IMy.class)
                .upCarColor(license, token, colorCard)
                .compose(RxHelper.rxBaseSchedulerHelper());
    }

    @Override
    public Observable<BaseResult> setDefaultCar(String license, String token, String sspid, boolean boo) {
        return HttpMethods.getInstance().createService(IMy.class)
                .setDefaultCar(license, token, sspid, boo)
                .compose(RxHelper.rxBaseSchedulerHelper());
    }

    @Override
    public Observable<BaseResult> deleteCar(String license, String token) {
        return HttpMethods.getInstance().createService(IMy.class)
                .deleteCar(license, token)
                .compose(RxHelper.rxBaseSchedulerHelper());
    }
}
