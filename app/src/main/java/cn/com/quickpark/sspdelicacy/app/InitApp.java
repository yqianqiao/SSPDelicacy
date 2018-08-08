package cn.com.quickpark.sspdelicacy.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by y on 2018/7/4.
 */

public class InitApp extends Application {
    private static InitApp initApplication;

    public static InitApp getInstance() {
        return initApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication = this;
        Logger.addLogAdapter(new AndroidLogAdapter());

    }
}
