package cn.com.quickpark.sspdelicacy.base;

import android.databinding.ViewDataBinding;

/**
 * Created by y on 2018/7/13.
 */

public abstract class MVPBaseFragment<SV extends ViewDataBinding, P extends BasePresenter> extends BaseFragment<SV> {

    protected P mPresenter;


    @Override
    protected void initPresenter() {
        mPresenter = createPresenter();
        mPresenter.attaView(this);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        mPresenter.onDetach();
    }
}
