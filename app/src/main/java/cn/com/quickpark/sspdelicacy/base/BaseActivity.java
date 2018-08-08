package cn.com.quickpark.sspdelicacy.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.databinding.ActivityBaseBinding;
import cn.com.quickpark.sspdelicacy.http.retrofit.RetrofitManager;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.utils.ToastUtil;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BaseActivity<SV extends ViewDataBinding> extends RxAppCompatActivity implements IBaseView {

    // 布局view
    protected SV bindingView;
    protected ActivityBaseBinding mBaseBinding;
    protected Toolbar baseToolbar;
    private CompositeDisposable mCompositeDisposable;
    protected RxPermissions rxPermissions;

    private int menuResId = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_base);
        mBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        bindingView = DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutId(), null, false);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
//        RelativeLayout mContainer =  mBaseBinding.getRoot().findViewById(R.id.container);
//        mContainer.addView(bindingView.getRoot());

        mBaseBinding.baseContainer.addView(bindingView.getRoot());

        rxPermissions = new RxPermissions(this);
        initToolbar();
        initView();
        initPresenter();
        initDate();
        initRxBus();
    }

    public abstract int getLayoutId();

    protected abstract void initDate();

    protected abstract void initView();

    protected void initRxBus() {

    }

    private void initToolbar() {
        baseToolbar = mBaseBinding.baseToolbar;
        setSupportActionBar(baseToolbar);
        baseToolbar.setNavigationOnClickListener(v -> onBackPressed());
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
    }

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {

    }

    /**
     * 添加菜单
     *
     * @param resId menu Layout
     */
    protected void addToolbarMenu(int resId) {
        menuResId = resId;
    }

    /**
     * menu的点击事件
     *
     * @param onclick
     */
    public void setToolBarMenuOnclick(Toolbar.OnMenuItemClickListener onclick) {
        baseToolbar.setOnMenuItemClickListener(onclick);
    }

    /**
     * 设置toolbar标题
     */
    protected void setToolbarTitle(String title) {
        mBaseBinding.toolbarTitle.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0) {
            getMenuInflater().inflate(menuResId, menu);
        }
        return super.onCreateOptionsMenu(menu);
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
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 隐藏键盘
     *
     * @return 隐藏键盘结果
     * <p>
     * true:隐藏成功
     * <p>
     * false:隐藏失败
     */
    protected boolean hiddenKeyboard() {
        //点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);

        return mInputMethodManager.hideSoftInputFromWindow(this
                .getCurrentFocus().getWindowToken(), 0);
    }

    // 查看权限是否已申请

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static boolean isAppliedPermission(Context context, String permission) {
        return context.checkSelfPermission(permission) ==
                PackageManager.PERMISSION_GRANTED;
    }




    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String text) {
        ToastUtil.showShort(this, text);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(new Intent(this, clz));
        overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
                .activity_start_zoom_out);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
                .activity_start_zoom_out);
    }


    /**
     * 绑定生命周期
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

}
