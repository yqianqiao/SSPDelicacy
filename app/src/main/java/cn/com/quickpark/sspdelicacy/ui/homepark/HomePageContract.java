package cn.com.quickpark.sspdelicacy.ui.homepark;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.homepark.QuerySceneCarBean;
import io.reactivex.Observable;

/**
 *
 * Created by y on 2018/7/13.
 */

public interface HomePageContract {

    interface View extends IBaseView {
        /**
         * 显示在场车牌
         * @param license 车牌
         */
        void setSceneCar(String license);

        /**
         * 显示正在存车车牌
         * @param license 车牌
         */
        void setBeBeingCar(String license);
    }

    interface Model extends BaseModel {
        /**
         * 查询车辆状态
         */
        Observable<QuerySceneCarBean> queryCarState(String userId);

    }

    abstract class Presenter extends BasePresenter<Model, View> {

        abstract void queryCarState();

        /**
         * 判断跳转那个activity
         */
        abstract void judgeActivity();

    }
}
