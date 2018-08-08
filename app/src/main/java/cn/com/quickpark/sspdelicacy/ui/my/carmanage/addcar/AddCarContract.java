package cn.com.quickpark.sspdelicacy.ui.my.carmanage.addcar;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.httpvo.AddCarVo;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/23.
 */

public interface AddCarContract {
    interface View extends IBaseView {
        AddCarVo getAddCarVo();
    }

    interface Model extends BaseModel {
        /**
         * 新增车辆
         */
        Observable<BaseResult> addCar(AddCarVo addCarVo);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addCar();
    }
}
