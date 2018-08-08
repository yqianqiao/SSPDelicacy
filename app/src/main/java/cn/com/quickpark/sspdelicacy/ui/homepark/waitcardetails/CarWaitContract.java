package cn.com.quickpark.sspdelicacy.ui.homepark.waitcardetails;

import java.util.List;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import cn.com.quickpark.sspdelicacy.bean.BaseListResult;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.homepark.ParkCarWaitBean;
import cn.com.quickpark.sspdelicacy.bean.homepark.QueryAllCarBean;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/13.
 */

public interface CarWaitContract {
    interface View extends IBaseView {
        /**
         * 获取车牌
         */
        String getLicense();

        /**
         * 设置界面显示属性
         */
        void setShowViewData(ParkCarWaitBean bean);

        /**
         * 切换库中车的详情
         */
        void switchCar(List<String> carList);
    }

    interface Model extends BaseModel {
        /**
         * 根据车牌查询存车信息
         */
        Observable<ParkCarWaitBean> parkCarWait(String license);

        /**
         * 撤销存车
         */
        Observable<BaseResult> revoke(String license);

        /**
         * 获取在库中车辆（已存加未存）
         */
        Observable<List<QueryAllCarBean>> queryAllCar(String id, String token, boolean boo);

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 根据车牌查询存车信息
         */
        abstract void parkCarWait();

        /**
         * 撤销
         */
        abstract void revoke();

        /**
         * 切换库中车辆
         */
        abstract void switchCar();
    }
}
