package cn.com.quickpark.sspdelicacy.http.api;

import java.util.Map;

import cn.com.quickpark.sspdelicacy.bean.BaseListResult;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.homepark.ParkCarWaitBean;
import cn.com.quickpark.sspdelicacy.bean.homepark.QueryAllCarBean;
import cn.com.quickpark.sspdelicacy.bean.homepark.QuerySceneCarBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by y on 2018/7/13.
 */

public interface IHomePageApi {

    /**
     * 查询在场车辆
     *
     * @param userId userId
     * @return
     */
    @FormUrlEncoded
    @POST("park/getLicensesByUserId")
    Observable<BaseResult<QuerySceneCarBean>> querySceneCar(@Field("userId") String userId);

    /**
     * 开始存车
     *
     * @return
     */
    @FormUrlEncoded
    @POST("park/order")
    Observable<BaseResult> startSaveCar(@FieldMap Map<String, Object> map);

    /**
     * 预约停车
     */
    @FormUrlEncoded
    @POST("park/getParkByLicense")
    Observable<BaseResult<ParkCarWaitBean>> parkCarWait(@Field("license") String license);

    /**
     * 撤回存车
     */
    @FormUrlEncoded
    @POST("park/cancel")
    Observable<BaseResult> revoke(@Field("license") String license);

    /**
     * 查询所有车辆
     *
     * @param id    userId
     * @param token token
     * @param boo   boo不穿查询全部 boo=true查询已存的车辆 boo==false查询未存
     * @return
     */

    @FormUrlEncoded
    @POST("car/getLicenseById")
    Observable<BaseListResult<QueryAllCarBean>> queryAllCar(@Field("id") String id, @Field("token") String token,
                                                            @Field("boo") boolean boo);

}
