package cn.com.quickpark.sspdelicacy.ui.login;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import cn.com.quickpark.sspdelicacy.MainActivity;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.constants.User;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;
import cn.com.quickpark.sspdelicacy.utils.SPUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by y on 2018/7/6.
 */

public class LoginPresenter extends LoginContract.Presenter {
    private LoginContract.View view;
    private Disposable subscribe;


    @Override
    public void getCode() {
        view = getView();
        if (view == null || mModel == null) return;
        String phoneNumber = view.getPhoneNumber();

        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() != 11) {
            view.showToast("请输入正确的手机号码");
            return;
        }
        if (subscribe != null && !subscribe.isDisposed()) {
            view.showToast("验证码已发送");
        } else {
            Logger.e("错误请求验证码");
            mModel.getCode(phoneNumber)
                    .compose(view.bindToLife())
                    .subscribe(new MyObserver<BaseResult>() {
                        @Override
                        public void onSuccess(BaseResult baseResult) {
                            Logger.e("JSON = BaseResult");
                            view.showToast("发送成功");
                            timer();
                        }

                        @Override
                        public void onFail(String msg) {
                            view.showToast(msg);
                        }
                    });
        }

    }

    @Override
    public void login() {
        view = getView();
        if (view == null || mModel == null) return;
        String phoneNumber = view.getPhoneNumber();
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() != 11) {
            view.showToast("请输入正确的手机号码");
            return;
        }
        String code = view.getCodeText();
        if (TextUtils.isEmpty(code) || code.length() != 4) {
            view.showToast("请输入正确的验证码");
            return;
        }

        mModel.login(phoneNumber, code)
                .compose(view.bindToLife())
                .subscribe(new MyObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        view.showToast("登录成功");
                        SPUtils.putBoolean(Constants.ISLOGIN, true);
                        SPUtils.putString(Constants.USER_INFO, JSON.toJSONString(user));
                        view.startNewActivity(MainActivity.class);
                        view.finishActivity();
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
//                .subscribe(new MyObserver<BaseResult<User>>() {
//                    @Override
//                    public void onSuccess(BaseResult<User> userBaseResult) {
//                        view.showToast("登录成功");
//                        SPUtils.putBoolean(Constants.ISLOGIN, true);
//                        SPUtils.putString(Constants.USER_INFO, JSON.toJSONString(userBaseResult.getResult()));
//                        Logger.json(JSON.toJSONString(userBaseResult.getResult()));
//                        view.startNewActivity(MainActivity.class);
//                        view.finishActivity();
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//                        view.showToast(msg);
//                    }
//                });


    }

    @Override
    public void timer() {
        subscribe = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> view.setTextCode((60 - aLong) + " S"))
                .doOnComplete(() -> {
                    view.setTextCode("获取验证码");
                    unbind();
                })
                .subscribe();
    }

    @Override
    public void unbind() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }

    @Override
    public LoginContract.Model getModel() {
        return LoginModel.newInstance();
    }


//    @Override
//    public void isLogin() {
////        if (SPUtils.getBoolean(Constants.ISLOGIN, false)) {
////            User user = JSON.parseObject(SPUtils.getString(Constants.USER_INFO, "user"), User.class);
////            LoginSession.getSession().setUser(user);
////        } else {
////            view.startNewActivity(LoginActivity.class);
////        }
//    }

}
