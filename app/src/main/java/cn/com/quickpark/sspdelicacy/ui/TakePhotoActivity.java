package cn.com.quickpark.sspdelicacy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.BaseActivity;
import cn.com.quickpark.sspdelicacy.databinding.ActivityTakePhotoBinding;
import cn.com.quickpark.sspdelicacy.utils.LQRPhotoSelectUtils;

public class TakePhotoActivity extends BaseActivity<ActivityTakePhotoBinding> {



    @Override
    public int getLayoutId() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void initDate() {
        bindingView.tvPhotograph.setOnClickListener(v -> {

        });
    }

    @Override
    protected void initView() {
        baseToolbar.setVisibility(View.GONE);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.6);   //高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.7);    //宽度设置为屏幕的0.7
        p.alpha = 1.0f;      //设置本身透明度
        p.dimAmount = 0.5f;      //设置窗口外黑暗度
        getWindow().setAttributes(p);

    }

}
