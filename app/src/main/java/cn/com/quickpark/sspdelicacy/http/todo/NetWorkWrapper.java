package cn.com.quickpark.sspdelicacy.http.todo;

import java.util.concurrent.TimeUnit;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.constants.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by songdongliang on 2016/11/16.
 */

public class NetWorkWrapper {
    private static final String TAG = "NetworkWrapper";
    private static final int DEFAULT_TIMEOUT = 30;
    private MyService myService;
    private Retrofit retrofit;


    private String baseUrl = Constants.BASE_URL;

    //构造方法私有
    private NetWorkWrapper() {
        //创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .baseUrl(baseUrl)
                .build();
        myService = retrofit.create(MyService.class);
    }


    //在访问NetworkWrapper时创建单例
    private static class SingletonHolder {
        private static final NetWorkWrapper INSTANCE = new NetWorkWrapper();
    }

    //获取单例
    public static NetWorkWrapper getInstance(String url) {
        NetWorkWrapper netWorkWrapper = SingletonHolder.INSTANCE;
        netWorkWrapper.baseUrl = url;
        return SingletonHolder.INSTANCE;
    }

    //获取单例
    public static NetWorkWrapper getInstance() {
        NetWorkWrapper netWorkWrapper = SingletonHolder.INSTANCE;
        netWorkWrapper.baseUrl = Constants.BASE_URL;
        return SingletonHolder.INSTANCE;
    }

    public MyService getMyService() {
        return myService;
    }

    private <T> void toSubscribe(Observable<T> o, Observer s) {
        o.subscribeOn(Schedulers.io())
                .debounce(1, TimeUnit.SECONDS)
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


//    public void getHotMovie(MyObserver<BaseResult> subscriber, String s) {
//        Observable<BaseResult> observable = myService.getHotMovie(s);
//        toSubscribe(observable, subscriber);
//    }
//
//    public void login(MyObserver<User> subscriber, String mobile, String code) {
//        Observable<BaseResult<User>> observable = myService.login(mobile, code);
//        toSubscribe(observable, subscriber);
//    }


}
