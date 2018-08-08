package cn.com.quickpark.sspdelicacy.http.todo;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by y on 2018/7/4.
 */

public abstract class MyObserver<T> implements Observer<T> {
    private static final String TAG = "MyObserver";
    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        Logger.json( JSON.toJSONString(t));
        onSuccess(t);
    }


    @Override
    public void onError(Throwable e) {
        onFail(e.getMessage());
        mDisposable.dispose();
        e.printStackTrace();
        // Log.d(TAG, "onError--" + type + ":" + e.toString());
        if (e.toString().contains("java.net.SocketTimeoutException")) {// 请求超时
            Logger.d(TAG + " 请求超时");
        } else if (e.toString().contains("retrofit2.adapter.rxjava.HttpException")) { // 没有该接口
            Logger.d(TAG + " 没有该接口");
        } else if (e.toString().contains("com.google.gson.JsonSyntaxException")) {
            Logger.d(TAG + " 类型转换错误");
        }
    }

    @Override
    public void onComplete() {
        mDisposable.dispose();
    }


    //请求成功，返回结果
    public abstract void onSuccess(T t);

    //请求失败，显示错误信息
    public abstract void onFail(String msg);

}
