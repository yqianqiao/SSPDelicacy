package cn.com.quickpark.sspdelicacy.ui.my.carmanage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.SeekBar;

import com.orhanobut.logger.Logger;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.BaseActivity;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.databinding.ActivityCarColorBinding;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxBusBaseMessage;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;
import cn.com.quickpark.sspdelicacy.utils.ImageUtil;
import cn.com.quickpark.sspdelicacy.utils.VerifyUtil;


public class CarColorActivity extends BaseActivity<ActivityCarColorBinding> implements View.OnClickListener {

    private static final String TAG = "CarColorActivity";

    private String[] colors = new String[]{
            "#FFD5D5D5",
            "#FF111111",
            "#FF0082CC",
            "#FFFFEA00",
            "#FFFFA200",
            "#FFCC0033",
            "#FF2BA742",
            "#FFC5CC00",
            "#FFBE43C3"};

    /**
     * 车辆颜色 默认为红色
     */
    private StringBuilder color = new StringBuilder("#FFCC0033");
    private int pixel;

    private Intent mIntent;

    @Override
    protected void initDate() {

        Drawable drawable = ContextCompat.getDrawable(CarColorActivity.this, R.mipmap.car_transparent);
        bindingView.ivCar.setImageDrawable(
                ImageUtil.tintDrawable(drawable, Color.parseColor(this.color.toString())));

        bindingView.viewColor1.setOnClickListener(this);
        bindingView.viewColor2.setOnClickListener(this);
        bindingView.viewColor3.setOnClickListener(this);
        bindingView.viewColor4.setOnClickListener(this);
        bindingView.viewColor5.setOnClickListener(this);
        bindingView.viewColor6.setOnClickListener(this);
        bindingView.viewColor7.setOnClickListener(this);
        bindingView.viewColor8.setOnClickListener(this);
        bindingView.viewColor9.setOnClickListener(this);
        bindingView.viewColor10.setOnClickListener(this);
        bindingView.tvSave.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        setToolbarTitle("车辆颜色");
        mIntent = getIntent();
        String color = mIntent.getStringExtra(Constants.CAR_COLOR);
        if (VerifyUtil.isColor(color)) {
            this.color.delete(0, this.color.length());
            this.color.append(color);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sliding_block_circle);
        bindingView.seekBar.setPadding(bitmap.getWidth() / 2, 0, bitmap.getWidth() / 2, 0);
        bindingView.seekBarAlpha.setPadding(bitmap.getWidth() / 2, 0, bitmap.getWidth() / 2, 0);

        addListener();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_car_color;
    }

    private void addListener() {

        bindingView.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                float rate = progress / 10000.0f;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.color_card);
                rate = rate >= 1 ? 0.999f : rate;
                int pixel = bitmap.getPixel((int) (bitmap.getWidth() * rate), 5);
                Drawable drawable = ContextCompat.getDrawable(CarColorActivity.this, R.mipmap.car_transparent);
                bindingView.ivCar.setImageDrawable(
                        ImageUtil.tintDrawable(drawable, pixel));

                color.delete(3, color.length());
                color.append(Integer.toHexString(pixel).substring(2, 8));

//                RxBus.getInstance().post(RxCodeConstants.CAR_COLOR, "#" + color);
//                finishActivity();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bindingView.seekBarAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float rate = progress / 10000.0f;
                bindingView.ivCar.setImageAlpha(255 - (int) (255 * rate));
                color.delete(1, 3);
                color.append(Integer.toHexString(255 - (int) (255 * rate)).substring(0, 2));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_color_1:
                changeColor(0);
                break;
            case R.id.view_color_2:
                changeColor(1);
                break;
            case R.id.view_color_3:
                changeColor(2);
                break;
            case R.id.view_color_4:
                changeColor(3);
                break;
            case R.id.view_color_5:
                changeColor(4);
                break;
            case R.id.view_color_6:
                changeColor(5);
                break;
            case R.id.view_color_7:
                changeColor(6);
                break;
            case R.id.view_color_8:
                changeColor(7);
                break;
            case R.id.view_color_9:
                changeColor(8);
                break;
            case R.id.view_color_10:
                if (bindingView.clCustomColor.getVisibility() == View.GONE) {
                    bindingView.clCustomColor.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.tv_save:
                Logger.e(color.toString());
                RxBus.getInstance().post(RxCodeConstants.CAR_MANAGE,new RxBusBaseMessage(RxCodeConstants.CAR_COLOR, color.toString()));
                finishActivity();
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changeColor(int position) {
        bindingView.ivCar.setImageAlpha(255);
        Drawable drawable = ContextCompat.getDrawable(CarColorActivity.this, R.mipmap.car_transparent);
        bindingView.ivCar.setImageDrawable(
                ImageUtil.tintDrawable(drawable, Color.parseColor(colors[position])));
        if (bindingView.clCustomColor.getVisibility() == View.VISIBLE) {
            bindingView.clCustomColor.setVisibility(View.GONE);
        }
        color.delete(0, color.length());
        color.append(colors[position]);
    }

}
