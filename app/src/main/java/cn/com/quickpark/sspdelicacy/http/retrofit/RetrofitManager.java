package cn.com.quickpark.sspdelicacy.http.retrofit;

import android.support.v4.util.ArrayMap;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by y on 2018/7/4.
 */

public class RetrofitManager  {

    private static ArrayMap<String, CompositeDisposable> netManager = new ArrayMap<>();

    public static Retrofit getInstance() {
        return Instance.retrofit;
    }

    private static class Instance {
        private static String baseUrl = "http://120.76.154.100:5120/";

        private static Retrofit retrofit = getRetrofit();

        private static Retrofit getRetrofit() {
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            //debug模式添加log信息拦截
           // if (BaseApp.getAppContext().isDebug()) {
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                okHttpBuilder.addInterceptor(interceptor);
//            }
//            okHttpBuilder.addInterceptor(new HeaderInterceptor());
//            okHttpBuilder.addInterceptor(new ParamsInterceptor());
            //设置网络连接失败时自动重试
            okHttpBuilder.retryOnConnectionFailure(true);
            //设置连接超时
            okHttpBuilder.connectTimeout(5, TimeUnit.SECONDS);
            //设置写超时
            okHttpBuilder.writeTimeout(10, TimeUnit.SECONDS);
            //设置读超时
            okHttpBuilder.readTimeout(10, TimeUnit.SECONDS);


            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(baseUrl);
            retrofitBuilder.client(okHttpBuilder.build());
            retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
            return retrofitBuilder.build();
        }
    }

    //为了避免错误的取消了，key建议使用packagename + calssName
    public static void add(String key, Disposable disposable) {
        if (netManager.containsKey(key)) {
            netManager.get(key).add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            netManager.put(key, compositeDisposable);
        }
    }

    public static void remove(String key) {
        if (netManager.containsKey(key)) {
            CompositeDisposable compositeDisposable = netManager.get(key);
            compositeDisposable.clear();
        }
    }
}
