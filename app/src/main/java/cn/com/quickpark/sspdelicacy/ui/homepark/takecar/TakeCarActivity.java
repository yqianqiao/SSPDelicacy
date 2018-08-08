package cn.com.quickpark.sspdelicacy.ui.homepark.takecar;

import android.content.Intent;
import android.view.View;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.BaseActivity;
import cn.com.quickpark.sspdelicacy.databinding.ActivityTakeCarBinding;
import cn.com.quickpark.sspdelicacy.ui.homepark.waitcardetails.CarWaitActivity;

/**
 * 取车
 */
public class TakeCarActivity extends BaseActivity<ActivityTakeCarBinding> {


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_take_car);
//    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        baseToolbar.setTitle("取车");
        bindingView.tvAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakeCarActivity.this, CarWaitActivity.class));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_take_car;
    }


}
