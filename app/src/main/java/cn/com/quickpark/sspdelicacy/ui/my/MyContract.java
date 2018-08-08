package cn.com.quickpark.sspdelicacy.ui.my;

import java.util.ArrayList;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/17.
 */

public interface MyContract {
    interface View extends IBaseView {
        /**
         * 显示个人信息
         */
        void showUserInfo(MyInfoBean.UserBean userBean);

        /**
         * 显示车辆信息
         */
        void showCarListInfo(ArrayList<MyInfoBean.CarListBean> carListBean);

    }

    interface Model extends BaseModel {
        Observable<MyInfoBean> getMyInfo(String id, String token);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getMyInfo();
    }
}
