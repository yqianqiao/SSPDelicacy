package cn.com.quickpark.sspdelicacy.ui.my.cersonalcenter;

import com.orhanobut.logger.Logger;

import java.io.File;

import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;

/**
 * Created by y on 2018/7/24.
 */

public class PersonalCenterPresenter extends PersonalCenterContract.Presenter {
    @Override
    public PersonalCenterContract.Model getModel() {
        return new PersonalCenterModel();
    }

    @Override
    void upAvatar(File file) {
        if (getView() == null || mModel == null) return;
        mModel.upAvatar(file)
                .compose(getView().bindToLife())
                .subscribe(new MyObserver<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Logger.e(s);
                    }

                    @Override
                    public void onFail(String msg) {
                        getView().showToast(msg);
                    }
                });
    }
}
