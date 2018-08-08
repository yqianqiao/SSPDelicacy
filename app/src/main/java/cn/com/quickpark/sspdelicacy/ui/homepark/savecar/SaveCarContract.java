package cn.com.quickpark.sspdelicacy.ui.homepark.savecar;


import android.support.annotation.NonNull;

import java.util.List;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.httpvo.SaveCarVo;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/13.
 */

public interface SaveCarContract {
    interface View extends IBaseView {
        /**
         * 选择车辆
         */
        void showChooseCar(List<String> list);

        /**
         * 请求参数
         */
        @NonNull
        SaveCarVo getSaveCarVo();
    }

    interface Model extends BaseModel {
        /**
         * 存车
         */
        Observable<BaseResult> startSaveCar(SaveCarVo saveCarVo);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 获取可预约车
         */
        abstract void getCarCount();

        /**
         * 开始存车
         */
        abstract void startSaveCar();
    }
}
