package cn.com.quickpark.sspdelicacy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.RxLifecycle;

import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.constants.LoginSession;
import cn.com.quickpark.sspdelicacy.constants.User;
import cn.com.quickpark.sspdelicacy.databinding.ActivityMainBinding;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.ui.homepark.HomePageFragment;
import cn.com.quickpark.sspdelicacy.ui.login.LoginActivity;
import cn.com.quickpark.sspdelicacy.ui.my.MyFragment;
import cn.com.quickpark.sspdelicacy.ui.parking.ParkingFragment;
import cn.com.quickpark.sspdelicacy.utils.SPUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private static final int FRAGMENT_HOMEPAGE = 0;
    private static final int FRAGMENT_PARKING = 1;
    private static final int FRAGMENT_MY = 2;
    private HomePageFragment homePageFragment;
    private ParkingFragment parkingFragment;
    private MyFragment myFragment;
    //退出时间
    private long exitTime = 0;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        isLogin();

        initView();
        if (savedInstanceState != null) {
            homePageFragment = (HomePageFragment) getSupportFragmentManager().findFragmentByTag(HomePageFragment.class.getName());
            parkingFragment = (ParkingFragment) getSupportFragmentManager().findFragmentByTag(ParkingFragment.class.getName());
            myFragment = (MyFragment) getSupportFragmentManager().findFragmentByTag(MyFragment.class.getName());
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt("position"));
            mBinding.mainBottomNavigation.setSelectedItemId(savedInstanceState.getInt("select_item"));
        } else {
            showFragment(FRAGMENT_HOMEPAGE);
        }

    }

    private void initView() {
        setSupportActionBar(mBinding.mainToolbar);
        mBinding.mainBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_homePage:
                    showFragment(FRAGMENT_HOMEPAGE);
                    break;
                case R.id.action_parking:
                    showFragment(FRAGMENT_PARKING);
                    break;
                case R.id.action_my:
                    showFragment(FRAGMENT_MY);
                    break;
            }
            return true;
        });
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        switch (index) {
            case FRAGMENT_HOMEPAGE:
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    ft.add(R.id.main_container, homePageFragment, HomePageFragment.class.getName());
                } else {
                    ft.show(homePageFragment);
                }
                break;
            case FRAGMENT_PARKING:
                if (parkingFragment == null) {
                    parkingFragment = new ParkingFragment();
                    ft.add(R.id.main_container, parkingFragment, ParkingFragment.class.getName());
                } else {
                    ft.show(parkingFragment);
                }
                break;
            case FRAGMENT_MY:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    ft.add(R.id.main_container, myFragment, MyFragment.class.getName());
                } else {
                    ft.show(myFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (homePageFragment != null) {
            ft.hide(homePageFragment);
        }
        if (parkingFragment != null) {
            ft.hide(parkingFragment);
        }
        if (myFragment != null) {
            ft.hide(myFragment);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        outState.putInt("position", position);
        outState.putInt("select_item", mBinding.mainBottomNavigation.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.double_click_exit, Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }

    public void isLogin() {
        if (!SPUtils.getBoolean(Constants.ISLOGIN, false)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            String userJson = SPUtils.getString(Constants.USER_INFO, "user is null");
            LoginSession.getSession().setUser(JSON.parseObject(userJson, User.class));
            Logger.json(userJson);
        }
    }
}
