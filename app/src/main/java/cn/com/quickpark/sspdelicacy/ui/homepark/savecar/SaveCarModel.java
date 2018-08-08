package cn.com.quickpark.sspdelicacy.ui.homepark.savecar;


import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IHomePageApi;
import cn.com.quickpark.sspdelicacy.httpvo.SaveCarVo;
import cn.com.quickpark.sspdelicacy.utils.HttpUtil;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/13.
 */

public class SaveCarModel implements SaveCarContract.Model {

    @Override
    public Observable<BaseResult> startSaveCar(SaveCarVo saveCarVo) {
        return HttpMethods.getInstance().createService(IHomePageApi.class)
                .startSaveCar(HttpUtil.objectToMap(saveCarVo))
                .compose(RxHelper.rxBaseSchedulerHelper());
    }
}
