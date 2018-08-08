package cn.com.quickpark.sspdelicacy.http.api;

import java.util.Map;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.my.CarBrandBean;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by y on 2018/7/17.
 */

public interface IMy {
    /**
     * 获取个人信息
     */
    @FormUrlEncoded
    @POST("user/getUserAndCarById")
    Observable<BaseResult<MyInfoBean>> getMyInfo(@Field("id") String id, @Field("token") String token);

    /**
     * 修改车辆颜色
     */
    @FormUrlEncoded
    @POST("car/updateColre")
    Observable<BaseResult> upCarColor(@Field("license") String license, @Field("token") String token,
                                      @Field("colorCard") String colorCard);

    /**
     * 设置车辆默认值
     */
    @FormUrlEncoded
    @POST("car/setupdefaultcar")
    Observable<BaseResult> setDefaultCar(@Field("license") String license, @Field("token") String token,
                                         @Field("sspid") String sspid, @Field("boo") boolean boo);

    /**
     * 删除车辆
     */
    @FormUrlEncoded
    @POST("car/delete")
    Observable<BaseResult> deleteCar(@Field("license") String license, @Field("token") String token);

    /**
     * 新增车辆
     */
    @FormUrlEncoded
    @POST("car/add")
    Observable<BaseResult> addCar(@FieldMap Map<String, Object> map);


    /**
     * 获取车辆品牌型号
     * carinfo/queryCarBrandType
     */
    @GET
    Observable<BaseResult<CarBrandBean>> getCarBrandType(@Url String url);

    /**
     * 上传头像
     */
    @Multipart
    @POST("uploadOne")
    Observable<BaseResult<String>> upAvatarPic(@Part MultipartBody.Part img);

}
