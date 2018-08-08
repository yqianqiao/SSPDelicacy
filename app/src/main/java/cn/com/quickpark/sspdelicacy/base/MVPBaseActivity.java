package cn.com.quickpark.sspdelicacy.base;

import android.databinding.ViewDataBinding;

/**
 * Created by y on 2018/7/9.
 */

public abstract class MVPBaseActivity<SV extends ViewDataBinding, P extends BasePresenter> extends BaseActivity<SV> {
    protected P mPresenter;


    @Override
    protected void initPresenter() {
        mPresenter = createPresenter();
        mPresenter.attaView(this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
