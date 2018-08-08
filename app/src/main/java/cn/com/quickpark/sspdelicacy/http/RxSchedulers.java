package cn.com.quickpark.sspdelicacy.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.utils.NetWorkUtil;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by y on 2018/7/4.
 */

public class RxSchedulers {
    /**
     * 基本请求
     */
    public static <T> FlowableTransformer<T, T> io_main(final Context context) {
        return new FlowableTransformer<T, T>() {
            @Override public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(@NonNull Subscription subscription) throws Exception {
                                //如果无网络连接，则直接取消了
                                if (!NetWorkUtil.isNetworkConnected(context)) {

                                    subscription.cancel();
//                                    MsgEvent msgEvent = new MsgEvent(Constants.NET_CODE_CONNECT, Constants.CONNECT_EXCEPTION);
//                                    EventBus.getDefault().post(msgEvent);
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 带进度条的请求
     */
    public static <T> FlowableTransformer<T, T> io_main(final Context context, final ProgressDialog dialog) {
        return new FlowableTransformer<T, T>() {
            @Override public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream
                        //为了让进度条保持一会儿做了个延时
                        .delay(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(@NonNull final Subscription subscription) throws Exception {
                                if (!NetWorkUtil.isNetworkConnected(context)) {
                                    subscription.cancel();
//                                    MsgEvent msgEvent = new MsgEvent(Constants.NET_CODE_CONNECT, Constants.CONNECT_EXCEPTION);
//                                    EventBus.getDefault().post(msgEvent);
                                } else {
                                    //启动进度显示，取消进度时会取消请求
                                    if (dialog != null) {
                                        dialog.setCanceledOnTouchOutside(false);
                                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            @Override public void onCancel(DialogInterface dialog) {
                                                subscription.cancel();
                                            }
                                        });
                                        dialog.show();
                                    }
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
