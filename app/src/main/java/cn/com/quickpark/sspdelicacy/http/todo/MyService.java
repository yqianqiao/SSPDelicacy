package cn.com.quickpark.sspdelicacy.http.todo;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.constants.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by y on 2018/7/4.
 */

public interface MyService {

    /**
     * 获取验证码
     *
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("user/getMsgCode")
    Observable<BaseResult> getHotMovie(@Field("mobile") String mobile);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResult<User>> login(@Field("mobile") String mobile, @Field("code") String code);

}
