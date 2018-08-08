package cn.com.quickpark.sspdelicacy.ui.my;

import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.constants.LoginSession;
import cn.com.quickpark.sspdelicacy.constants.User;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;

/**
 * Created by y on 2018/7/17.
 */

public class MyPresenter extends MyContract.Presenter {


    private MyContract.View view;

    @Override
    public MyContract.Model getModel() {
        return new MyModel();
    }

    @Override
    void getMyInfo() {
        view = getView();
        if (view == null || mModel == null) return;
        User user = LoginSession.getSession().getUser();
        mModel.getMyInfo(user.getLoginName(), user.getToken())
                .compose(view.bindToLife())
                .subscribe(new MyObserver<MyInfoBean>() {
                    @Override
                    public void onSuccess(MyInfoBean myInfoBean) {
                        view.showUserInfo(myInfoBean.getUser());
                        view.showCarListInfo(myInfoBean.getCarList());
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });


    }
}
