package cn.com.quickpark.sspdelicacy.ui.my;

import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IMy;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/17.
 */

public class MyModel implements MyContract.Model {
    @Override
    public Observable<MyInfoBean> getMyInfo(String id, String token) {
        return HttpMethods.getInstance().createService(IMy.class)
                .getMyInfo(id, token)
                .compose(RxHelper.rxSchedulerHelper());
    }
}
