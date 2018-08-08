package cn.com.quickpark.sspdelicacy.http.api;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by y on 2018/7/4.
 */

public interface ILoginApi {

    /**
     *
     */
    @POST("sspdelicacy/user/getMsgCode")
    Flowable<BaseResult> getHotMovie(@Query("mobile") String mobile);
}
