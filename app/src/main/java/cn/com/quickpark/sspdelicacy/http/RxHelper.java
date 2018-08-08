package cn.com.quickpark.sspdelicacy.http;

import java.util.List;

import cn.com.quickpark.sspdelicacy.bean.BaseListResult;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by y on 2018/7/9.
 */

public class RxHelper {
//    /**
//     * 统一线程处理
//     * <p>
//     * 发布事件io线程，接收事件主线程
//     */
//    public static <T> ObservableTransformer<T, T> rxBusScheduler() {//compose处理线程
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                return upstream.map(new Function<T, T>() {
//                    @Override
//                    public T apply(T t) throws Exception {
//                        return null;
//                    }
//                }).subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
////                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }

    /**
     * 统一线程处理
     * <p>
     * 发布事件io线程，接收事件主线程
     */
    public static <T extends BaseResult> ObservableTransformer<T, T> rxBaseSchedulerHelper() {//compose处理线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.flatMap(new Function<T, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(T tBaseResult) throws Exception {

                        if (tBaseResult.getRet() == 0) {
                            return createData(tBaseResult);
                        } else {
                            return Observable.error(new ApiException(tBaseResult.getRet(), tBaseResult.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public static <T> ObservableTransformer<BaseResult<T>, T> rxSchedulerHelper() {//compose处理线程
        return upstream -> upstream
                .flatMap(tBaseResult -> {
                    if (tBaseResult.getRet() == 0) {
                        return createData(tBaseResult.getResult());
                    } else {
                        return Observable.error(new ApiException(tBaseResult.getRet(), tBaseResult.getMsg()));
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 统一线程处理
     * <p>
     * 发布事件io线程，接收事件主线程
     */
    public static <T> ObservableTransformer<BaseListResult<T>, List<T>> rxListSchedulerHelper() {//compose处理线程
        return upstream -> upstream
                .flatMap(tBaseListResult -> {
                    List<T> result = tBaseListResult.getResult();
                    if (tBaseListResult.getRet() == 0) {
                        return createData(result);
                    } else {
                        return Observable.error(new ApiException(tBaseListResult.getRet(), tBaseListResult.getMsg()));
                    }

                }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static <T> ObservableSource<List<T>> createData(List<T> result) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(result);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }


    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(data);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
//
    }

    /**
     * 生成Flowable
     *
     * @param t
     * @return Flowable
     */
    public static <T> Flowable<T> createFlowable(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 生成Observable
     *
     * @param t
     * @return Flowable
     */
    public static <T> Observable<T> createObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }
}
