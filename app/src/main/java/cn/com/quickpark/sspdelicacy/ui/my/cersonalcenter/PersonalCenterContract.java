package cn.com.quickpark.sspdelicacy.ui.my.cersonalcenter;

import java.io.File;

import cn.com.quickpark.sspdelicacy.base.BaseModel;
import cn.com.quickpark.sspdelicacy.base.BasePresenter;
import cn.com.quickpark.sspdelicacy.base.IBaseView;
import io.reactivex.Observable;

/**
 * Created by y on 2018/7/24.
 */

public interface PersonalCenterContract {
    interface View extends IBaseView {

    }

    interface Model extends BaseModel {
        //上传头像
        Observable<String> upAvatar(File file);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
       abstract void upAvatar(File file);
    }
}
