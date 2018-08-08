package cn.com.quickpark.sspdelicacy.base;

/**
 * Created by Meiji on 2017/5/7.
 */

public interface IBasePresenter<V extends IBaseView> {


    /**
     * 将View附着到Presenter上
     */
    void attachView(V view);

    /**
     * 在视图被摧毁时调用。典型场景是Activity.onDestroy()和Fragment.onDestroyView()方法
     */
    void detachView(boolean retainInstance);

    boolean isViewActive();
}
