package cn.com.quickpark.sspdelicacy.interfaces;

/**
 * Created by y on 2018/7/9.
 */

public interface RequestListener<T> {
    /**
     * 成功的时候回调
     */
    void onSuccess(T obj);

    /**
     * 失败的时候回调
     */
    void onError(String error);
}
