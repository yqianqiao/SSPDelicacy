package cn.com.quickpark.sspdelicacy.ui.login;

import android.view.View;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.MVPBaseActivity;
import cn.com.quickpark.sspdelicacy.databinding.ActivityLoginBinding;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends MVPBaseActivity<ActivityLoginBinding, LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {


    @Override
    protected void initView() {
        baseToolbar.setVisibility(View.GONE);
        bindingView.tvGetCode.setOnClickListener(this);
        bindingView.tvLogin.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initDate() {

    }

    @Override
    public String getPhoneNumber() {
        return bindingView.etInputPhone.getText().toString();
    }

    @Override
    public String getCodeText() {
        return bindingView.etInputCode.getText().toString();
    }

    @Override
    public void setTextCode(String code) {
        bindingView.tvGetCode.setText(code);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getCode:
                mPresenter.getCode();
                break;
            case R.id.tv_login:
                mPresenter.login();
                break;
        }
    }

    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

}

