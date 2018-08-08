package cn.com.quickpark.sspdelicacy.ui.my.cersonalcenter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.io.File;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.MVPBaseActivity;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.databinding.ActivityPersonalCenterBinding;
import cn.com.quickpark.sspdelicacy.utils.LQRPhotoSelectUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 个人信息
 */
public class PersonalCenterActivity extends MVPBaseActivity<ActivityPersonalCenterBinding, PersonalCenterContract.Presenter>
        implements PersonalCenterContract.View, View.OnClickListener {


    private MyInfoBean.UserBean userInfo;
    private LQRPhotoSelectUtils lqrPhotoSelectUtils;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void initDate() {
        Glide.with(this)
                .load(userInfo.getAvatarPic())
                .error(R.mipmap.ic_launcher_round)
                .into(bindingView.civAvatar);
        bindingView.tvNickname.setText(userInfo.getNickName());
        bindingView.tvPhone.setText(userInfo.getMobile());
        bindingView.tvBirthday.setText(userInfo.getBirthDay());
        bindingView.tvSex.setText(userInfo.getSex());
        bindingView.tvSignature.setText(userInfo.getSignature());

        bindingView.civAvatar.setOnClickListener(this);
        bindingView.viewNickname.setOnClickListener(this);
        bindingView.viewPhone.setOnClickListener(this);
        bindingView.viewBirthday.setOnClickListener(this);
        bindingView.viewSex.setOnClickListener(this);
        bindingView.viewSignature.setOnClickListener(this);
        bindingView.tvExit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        userInfo = (MyInfoBean.UserBean) getBundle().getSerializable(Constants.USER_INFO);
        lqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, (outputFile, outputUri) -> {
            Glide.with(this).load(outputUri).into(bindingView.civAvatar);
            mPresenter.upAvatar(outputFile);
        }, true);

//        rxPermissions.requestEach(Manifest.permission.CAMERA,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .subscribe(permission ->{
//                    if (permission.granted) {
//                        // 用户已经同意该权限
//                        Log.d("aaa", permission.name + " is granted.");
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                        Log.d("aaa", permission.name + " is denied. More info should be provided.");
//                    } else {
//                        // 用户拒绝了该权限，并且选中『不再询问』
//                        Log.d("aaa", permission.name + " is denied.");
//                    }
//                });
    }


    @Override
    protected PersonalCenterContract.Presenter createPresenter() {
        return new PersonalCenterPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_avatar:
                new MaterialDialog.Builder(this)
                        .title("选择方式")
                        .items("拍照", "相册")
                        .itemsCallback((dialog, itemView, position, text) -> {
                            if (position == 0) {
                                lqrPhotoSelectUtils.isPermissions();
                            } else {
                                lqrPhotoSelectUtils.selectPhoto();
                            }
                        }).show();
                break;
            case R.id.view_nickname:
                break;
            case R.id.view_phone:
                break;
            case R.id.view_sex:

                break;
            case R.id.view_signature:
                break;
            case R.id.tv_exit:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }
}
