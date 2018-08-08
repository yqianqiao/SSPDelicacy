package cn.com.quickpark.sspdelicacy.ui.homepark.savecar;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;
import cn.com.quickpark.sspdelicacy.httpvo.SaveCarVo;
import cn.com.quickpark.sspdelicacy.ui.homepark.waitcardetails.CarWaitActivity;

/**
 * Created by y on 2018/7/13.
 */

public class SaveCarPresenter extends SaveCarContract.Presenter {

    private SaveCarContract.View view;

    @Override
    public SaveCarContract.Model getModel() {
        return new SaveCarModel();
    }

    @Override
    void getCarCount() {
        view = getView();
        if (view == null || mModel == null) return;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("粤A00030");
        strings.add("粤A00031");
        strings.add("粤A00032");
        strings.add("粤A00033");
        view.showChooseCar(strings);
    }

    @Override
    void startSaveCar() {
        view = getView();
        if (view == null || mModel == null) return;
        final SaveCarVo saveCarVo = view.getSaveCarVo();

        if (saveCarVo.getPwdType() == 0 && TextUtils.isEmpty(saveCarVo.getPwd())) {
            view.showToast("请输入正确的密码");
            return;
        } else if (TextUtils.equals("请选择", saveCarVo.getLicense())) {
            view.showToast("请选择您要存的车");
            return;
        }
        mModel.startSaveCar(saveCarVo)
                .compose(view.bindToLife())
                .subscribe(new MyObserver<BaseResult>() {
                    @Override
                    public void onSuccess(BaseResult baseResult) {
                        view.showToast("存车成功");
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.LICENSE, saveCarVo.getLicense());
                        view.startNewActivity(CarWaitActivity.class, bundle);
                        view.finishActivity();
                    }

                    @Override
                    public void onFail(String msg) {
                        view.showToast(msg);
                    }
                });
    }
}
