package cn.com.quickpark.sspdelicacy.ui.homepark.savecar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.MVPBaseActivity;
import cn.com.quickpark.sspdelicacy.constants.LoginSession;
import cn.com.quickpark.sspdelicacy.databinding.ActivitySaveCarBinding;
import cn.com.quickpark.sspdelicacy.httpvo.SaveCarVo;

public class SaveCarActivity extends MVPBaseActivity<ActivitySaveCarBinding, SaveCarContract.Presenter>
        implements SaveCarContract.View, View.OnClickListener {
    //是否随机密码
    private boolean isRandom = false;
    private TextView oneselfPassword;
    private TextView randomPassword;
    //是否显示密码
    private boolean isShowPassword = true;

    @Override
    protected void initDate() {
        bindingView.viewChooseCar.setOnClickListener(this);
        bindingView.tvOneselfPassword.setOnClickListener(this);
        bindingView.tvRandomPassword.setOnClickListener(this);
        bindingView.ivShowPassword.setOnClickListener(this);
        bindingView.tvStartSave.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        oneselfPassword = bindingView.tvOneselfPassword;
        randomPassword = bindingView.tvRandomPassword;
        setToolbarTitle(getString(R.string.proceed_save_car));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_save_car;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_choose_car:
                mPresenter.getCarCount();
                break;
            case R.id.tv_oneself_password:
                if (isRandom) {
                    isRandom = false;
                    setPasswordType(false);
                }
                break;
            case R.id.tv_random_password:
                if (!isRandom) {
                    isRandom = true;
                    setPasswordType(true);
                    //hiddenKeyboard();
                }
                break;
            case R.id.iv_showPassword:
                if (isShowPassword) {
                    isShowPassword = false;
                    bindingView.etInputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    bindingView.etInputPassword.setSelection(bindingView.etInputPassword.getText().toString().length());
                } else {
                    isShowPassword = true;
                    bindingView.etInputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    bindingView.etInputPassword.setSelection(bindingView.etInputPassword.getText().toString().length());
                }

                break;
            case R.id.tv_start_save:
                mPresenter.startSaveCar();
                break;

        }
    }

    @Override
    protected SaveCarContract.Presenter createPresenter() {
        return new SaveCarPresenter();
    }


    @Override
    public void showChooseCar(final List<String> list) {
        //  String[] array = new String[list.size()];
        new AlertDialog.Builder(this)
                .setTitle("请选择")
                .setItems(list.toArray(new String[list.size()]), (dialog, which) -> bindingView.tvLicense.setText(list.get(which)))
                .show();
    }

    @NonNull
    @Override
    public SaveCarVo getSaveCarVo() {
        SaveCarVo saveCarVo = new SaveCarVo();
        saveCarVo.setUserId(LoginSession.getSession().getUser().getLoginName());
        saveCarVo.setArea(bindingView.tvCarBarn.getText().toString());
        saveCarVo.setLicense(bindingView.tvLicense.getText().toString());
        saveCarVo.setPwdType(isRandom ? 1 : 0);
        saveCarVo.setPwd(bindingView.etInputPassword.getText().toString().trim());
        saveCarVo.setParkingNo("3");
        saveCarVo.setType("轿车");
        saveCarVo.setColorCard("#FFFFA200");
        saveCarVo.setParkingName(bindingView.tvParkName.getText().toString());
        return saveCarVo;
    }

    private void setPasswordType(boolean isRandom) {
        bindingView.clVoucher.setVisibility(isRandom ? View.INVISIBLE : View.VISIBLE);
        randomPassword.setTextColor(ContextCompat.getColor(this, isRandom ? R.color.colorTheme : R.color.colorTextPrimary));
        randomPassword.setBackgroundResource(isRandom ? R.drawable.bg_round_main : R.drawable.bg_round_title);
        oneselfPassword.setTextColor(ContextCompat.getColor(this, isRandom ? R.color.colorTextPrimary : R.color.colorTheme));
        oneselfPassword.setBackgroundResource(isRandom ? R.drawable.bg_round_title : R.drawable.bg_round_main);
    }
}
