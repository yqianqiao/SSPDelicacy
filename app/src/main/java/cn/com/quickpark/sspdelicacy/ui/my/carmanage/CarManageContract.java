package cn.com.quickpark.sspdelicacy.ui.my.carmanage;

import android.support.annotation.NonNull;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/12.
 */

public interface CarManageContract {
    interface View extends IBaseView {

        /**
         * 获取当前中车牌
         */
        @NonNull
        String getLicense();

        /**
         * 更新界面数据 回调
         *
         * @param type 0 更新颜色，1 默认车辆 ，2 删除车辆
         */
        void setUpDataCar(int type);


    }

    interface Model extends BaseModel {
        /**
         * 修改车辆信息
         */
        Observable<BaseResult> upCarColor(String license, String token, String colorCard);

        /**
         * 设置主驾车辆
         */
        Observable<BaseResult> setDefaultCar(String license, String token, String sspid, boolean boo);

        /**
         * 删除车辆
         */
        Observable<BaseResult> deleteCar(String license, String token);

    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 修改车辆信息
         */
        abstract void upCarColor(String color);

        /**
         * 设置主驾车辆
         */
        abstract void setDefaultCar(boolean boo);

        /**
         * 删除车辆
         */
        abstract void deleteCar();
    }
}
