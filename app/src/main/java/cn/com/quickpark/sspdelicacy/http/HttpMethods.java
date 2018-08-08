package cn.com.quickpark.sspdelicacy.http;

import java.util.concurrent.TimeUnit;

import cn.com.quickpark.sspdelicacy.constants.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *
 * Created by y on 2018/7/9.
 */

public class HttpMethods {
    //设置超时时间
    private static final long DEFAULT_TIMEOUT = 10_000L;

    private Retrofit retrofit;
    private OkHttpClient client;

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }
    //私有化构造方法
    private HttpMethods() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(5 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addNetworkInterceptor(httpLoggingInterceptor);


//        //创建一个OkHttpClient并设置超时时间
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }
    //这里返回一个泛型类，主要返回的是定义的接口类
    public <T> T createService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
