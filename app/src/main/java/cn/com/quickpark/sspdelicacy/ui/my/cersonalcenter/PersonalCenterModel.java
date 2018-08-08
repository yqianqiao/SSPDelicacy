package cn.com.quickpark.sspdelicacy.ui.my.cersonalcenter;

import java.io.File;

import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IMy;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by y on 2018/7/24.
 */

public class PersonalCenterModel implements PersonalCenterContract.Model {

    @Override
    public Observable<String> upAvatar(File file) {
        //设置Content-Type:application/octet-stream
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //设置Content-Disposition:form-data; name="photo"; filename="xuezhiqian.png"
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(), photoRequestBody);

        return HttpMethods.getInstance().createService(IMy.class)
                .upAvatarPic(photo)
                .compose(RxHelper.rxSchedulerHelper());
    }
}
