package cn.com.quickpark.sspdelicacy.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.disposables.Disposable;

/**
 * Created by Meiji on 2017/5/7.
 */

public interface IBaseView {


    /**
     * 显示等待dialog
     */
    void showLoading();

    /**
     * 隐藏等待dialog
     */
    void hideLoading();

    /**
     * 添加rxBus
     */
    void addDisposable(Disposable disposable);

    /**
     * 注销rxBus
     */
    void removeDisposable();

    /**
     * Toast 显示
     */
    void showToast(String text);

    /**
     * 获取界面跳转的bundle
     */
    Bundle getBundle();



    /**
     * 关闭页面
     */
    void finishActivity();

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 跳往新的Activity
     *
     * @param clz 要跳往的Activity
     */
    void startNewActivity(@NonNull Class<?> clz);

    /**
     * 跳往新的Activity
     *
     * @param clz    要跳往的Activity
     * @param bundle 携带的bundle数据
     */
    void startNewActivity(@NonNull Class<?> clz, Bundle bundle);


}
