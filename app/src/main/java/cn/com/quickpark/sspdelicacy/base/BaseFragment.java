package cn.com.quickpark.sspdelicacy.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.databinding.FragmentBaseBinding;
import cn.com.quickpark.sspdelicacy.utils.ToastUtil;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<SV extends ViewDataBinding> extends RxFragment implements IBaseView {

    private FragmentBaseBinding mBinding;
    // 布局view
    protected SV bindingView;
    private CompositeDisposable mCompositeDisposable;
    protected Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, null, false);
        bindingView = DataBindingUtil.inflate(mActivity.getLayoutInflater(), getLayoutId(), null, false);
        mBinding.baseContainer.addView(bindingView.getRoot());
        initPresenter();


        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initRxBus();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    public abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected void initRxBus() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void removeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }

    }

    @Override
    public void showToast(String text) {
        ToastUtil.showShort(mActivity, text);
    }

    @Override
    public Bundle getBundle() {
        return getArguments();
    }

    @Override
    public void finishActivity() {
        if (mActivity != null)
            mActivity.finish();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(new Intent(mActivity, clz));

    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

//    public void addSubscription(Subscription s) {
//        if (this.mCompositeSubscription == null) {
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//        this.mCompositeSubscription.add(s);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        removeSubscription();
//    }
//


}
