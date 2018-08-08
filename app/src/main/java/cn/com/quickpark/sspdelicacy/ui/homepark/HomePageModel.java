package cn.com.quickpark.sspdelicacy.ui.homepark;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.homepark.QuerySceneCarBean;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IHomePageApi;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/13.
 */

public class HomePageModel implements HomePageContract.Model {


    @Override
    public Observable<QuerySceneCarBean> queryCarState(String userId) {
        return HttpMethods.getInstance().createService(IHomePageApi.class)
                .querySceneCar(userId)
                .compose(RxHelper.rxSchedulerHelper());
    }
}
