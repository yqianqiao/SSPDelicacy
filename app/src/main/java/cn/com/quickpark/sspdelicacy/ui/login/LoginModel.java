package cn.com.quickpark.sspdelicacy.ui.login;

import android.support.annotation.NonNull;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.constants.User;
import cn.com.quickpark.sspdelicacy.http.ApiService;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * Created by y on 2018/7/9.
 */

public class LoginModel implements LoginContract.Model {

    @NonNull
    public static LoginModel newInstance() {
        return new LoginModel();
    }

    @Override
    public Observable<BaseResult> getCode(String phone) {
        return HttpMethods.getInstance().createService(ApiService.class)
                .getHotMovie(phone)
                .compose(RxHelper.rxBaseSchedulerHelper());
    }

    @Override
    public Observable<User> login(String phone, String code) {
        return HttpMethods.getInstance().createService(ApiService.class)
                .login(phone, code)
                .compose(RxHelper.rxSchedulerHelper());
    }

}
