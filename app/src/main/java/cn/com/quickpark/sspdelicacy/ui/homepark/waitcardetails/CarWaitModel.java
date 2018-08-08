package cn.com.quickpark.sspdelicacy.ui.homepark.waitcardetails;

import java.util.List;

import cn.com.quickpark.sspdelicacy.bean.BaseListResult;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.homepark.ParkCarWaitBean;
import cn.com.quickpark.sspdelicacy.bean.homepark.QueryAllCarBean;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IHomePageApi;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/13.
 */

public class CarWaitModel implements CarWaitContract.Model {

    @Override
    public Observable<ParkCarWaitBean> parkCarWait(String license) {
        return HttpMethods.getInstance().createService(IHomePageApi.class)
                .parkCarWait(license)
                .compose(RxHelper.rxSchedulerHelper());
    }

    @Override
    public Observable<BaseResult> revoke(String license) {
        return HttpMethods.getInstance().createService(IHomePageApi.class)
                .revoke(license)
                .compose(RxHelper.rxBaseSchedulerHelper());
    }

    @Override
    public Observable<List<QueryAllCarBean>> queryAllCar(String id, String token, boolean boo) {
        return HttpMethods.getInstance().createService(IHomePageApi.class)
                .queryAllCar(id, token, boo)
                .compose(RxHelper.rxListSchedulerHelper());
    }
}
