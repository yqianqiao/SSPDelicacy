package cn.com.quickpark.sspdelicacy.ui.my.carmanage.addcar;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IMy;
import cn.com.quickpark.sspdelicacy.httpvo.AddCarVo;
import cn.com.quickpark.sspdelicacy.utils.HttpUtil;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/23.
 */

public class AddCarModel implements AddCarContract.Model {
    @Override
    public Observable<BaseResult> addCar(AddCarVo addCarVo) {
        return HttpMethods.getInstance().createService(IMy.class)
                .addCar(HttpUtil.objectToMap(addCarVo))
                .compose(RxHelper.rxBaseSchedulerHelper());
    }
}
