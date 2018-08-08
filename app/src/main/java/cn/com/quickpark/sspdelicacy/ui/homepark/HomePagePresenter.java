package cn.com.quickpark.sspdelicacy.ui.homepark;

import android.os.Bundle;
import android.text.TextUtils;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.homepark.QuerySceneCarBean;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.constants.LoginSession;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;
import cn.com.quickpark.sspdelicacy.ui.homepark.savecar.SaveCarActivity;
import cn.com.quickpark.sspdelicacy.ui.homepark.waitcardetails.CarWaitActivity;

/**
 * Created by y on 2018/7/13.
 */

public class HomePagePresenter extends HomePageContract.Presenter {

    private HomePageContract.View view;
    private String license;

    @Override
    public HomePageContract.Model getModel() {
        return new HomePageModel();
    }

    @Override
    void queryCarState() {
        view = getView();
        if (getView() == null || mModel == null) return;
        mModel.queryCarState(LoginSession.getSession().getUser().getLoginName())
                .compose(view.bindToLife())
                .subscribe(new MyObserver<QuerySceneCarBean>() {
                    @Override
                    public void onSuccess(QuerySceneCarBean querySceneCarBean) {

                        String order = querySceneCarBean.getSUCCESS();
                        view.setSceneCar(TextUtils.isEmpty(order) ? "暂无车辆信息" : order);
                        view.setBeBeingCar(querySceneCarBean.getORDER());
                        license = querySceneCarBean.getORDER();
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
    }

    @Override
    void judgeActivity() {
        view = getView();
        if (getView() == null || mModel == null) return;
        if (TextUtils.isEmpty(license)) {
            view.startNewActivity(SaveCarActivity.class);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.LICENSE, license);

            view.startNewActivity(CarWaitActivity.class, bundle);
        }
    }
}
