package cn.com.quickpark.sspdelicacy.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by y on 2018/7/5.
 */

public abstract class BasePresenter<M, V> {


    protected WeakReference<V> mViewRef;
    protected M mModel;


    protected void attaView(V mView) {
        mModel = getModel();
        mViewRef = new WeakReference<V>(mView);
    }

    public abstract M getModel();

    public V getView() {
        if (isAttach()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }


    protected boolean isAttach() {
        return null != mViewRef && null != mViewRef.get();
    }


    protected void onDetach() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


}
