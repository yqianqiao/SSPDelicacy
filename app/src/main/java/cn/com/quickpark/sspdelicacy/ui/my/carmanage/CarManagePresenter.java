package cn.com.quickpark.sspdelicacy.ui.my.carmanage;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.constants.LoginSession;
import cn.com.quickpark.sspdelicacy.constants.User;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;

/**
 * Created by y on 2018/7/12.
 */

public class CarManagePresenter extends CarManageContract.Presenter {

    private CarManageContract.View view;
    private User user;

    @Override
    public CarManageContract.Model getModel() {
        return new CarManageModel();
    }

    public CarManagePresenter() {
        user = LoginSession.getSession().getUser();
    }

    @Override
    void upCarColor(String color) {
        view = getView();
        if (view == null || mModel == null) return;

        mModel.upCarColor(view.getLicense(), user.getToken(), color)
                .compose(view.bindToLife())
                .subscribe(new MyObserver<BaseResult>() {
                    @Override
                    public void onSuccess(BaseResult baseResult) {
                        view.showToast("设置成功");
                        view.setUpDataCar(0);

                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
    }

    @Override
    void setDefaultCar(boolean boo) {
        view = getView();
        if (view == null || mModel == null) return;
        mModel.setDefaultCar(view.getLicense(), user.getToken(), user.getLoginName(), boo)
                .compose(view.bindToLife())
                .subscribe(new MyObserver<BaseResult>() {
                    @Override
                    public void onSuccess(BaseResult baseResult) {
                        view.showToast("设置成功");
                        view.setUpDataCar(1);
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
    }

    @Override
    void deleteCar() {
        view = getView();
        if (view == null || mModel == null) return;
        mModel.deleteCar(view.getLicense(), user.getToken())
                .compose(view.bindToLife())
                .subscribe(new MyObserver<BaseResult>() {
                    @Override
                    public void onSuccess(BaseResult baseResult) {
                        view.showToast("删除成功");
                        view.setUpDataCar(2);
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
    }
}
