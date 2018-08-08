package cn.com.quickpark.sspdelicacy.ui.login;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.constants.User;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/6.
 */

public interface LoginContract {

    interface Model extends BaseModel {
        /**
         * 获取验证码
         *
         * @param phone 手机号码
         */
        //  void getCode(String phone, RequestListener listener);
        Observable<BaseResult> getCode(String phone);

        /**
         * 登录操作
         * @param phone 手机号码
         * @param code  验证码
         */
        //   void login(String phone, String code, RequestListener listener);
        Observable<User> login(String phone, String code);


    }

    interface View extends IBaseView {
        /**
         * 获取手机号码
         *
         * @return phone
         */
        String getPhoneNumber();

        /**
         * 获取验证码
         */
        String getCodeText();

        /**
         * 获取验证码控件
         */
        void setTextCode(String code);

    }

    abstract class Presenter extends BasePresenter<Model,View> {

        public abstract void getCode();

        /**
         * 登入
         */
        public abstract void login();

        /**
         * 倒计时
         */
        public abstract void timer();

        /**
         * 取消订阅
         */
        public abstract void unbind();

        //   public abstract void isLogin();

    }

}
