package cn.com.quickpark.sspdelicacy.ui.my.carmanage.addcar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.adapter.my.CarBrandAdapter;
import cn.com.quickpark.sspdelicacy.base.BaseActivity;
import cn.com.quickpark.sspdelicacy.bean.BaseResult;
import cn.com.quickpark.sspdelicacy.bean.my.CarBrandBean;
import cn.com.quickpark.sspdelicacy.bean.my.CarBrandModel;
import cn.com.quickpark.sspdelicacy.databinding.ActivityCarBrandBinding;
import cn.com.quickpark.sspdelicacy.http.HttpMethods;
import cn.com.quickpark.sspdelicacy.http.RxHelper;
import cn.com.quickpark.sspdelicacy.http.api.IMy;
import cn.com.quickpark.sspdelicacy.http.todo.MyObserver;
import cn.com.quickpark.sspdelicacy.view.DividerItemDecoration;
import cn.com.quickpark.sspdelicacy.view.indexlib.IndexBar.widget.IndexBar;
import cn.com.quickpark.sspdelicacy.view.indexlib.suspension.SuspensionDecoration;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class CarBrandActivity extends BaseActivity<ActivityCarBrandBinding> {


    private RecyclerView mRecyclerView;
    private IndexBar indexBar;
    private TextView tvSideBarHint;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<CarBrandModel> mCarBrandModels;
    private SuspensionDecoration mDecoration;
    private CarBrandAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_brand;
    }

    @Override
    protected void initDate() {
        HttpMethods.getInstance().createService(IMy.class)
                .getCarBrandType("http://web.quickpark.com.cn:8030/qp-platform/qpapi/carbrandtype")
                .compose(RxHelper.rxSchedulerHelper())
                .compose(bindToLife())
                .subscribe(new MyObserver<CarBrandBean>() {
                    @Override
                    public void onSuccess(CarBrandBean carBrandBean) {
                        mCarBrandModels.clear();
                        mCarBrandModels.addAll(carBrandBean.getCarBrandModels());
                        adapter.notifyDataSetChanged();
                        indexBar.setmSourceDatas(mCarBrandModels)//设置数据
                                .invalidate();
                        mDecoration.setmDatas(mCarBrandModels);
                    }

                    @Override
                    public void onFail(String msg) {
                        showToast(msg);
                    }
                });

    }

    @Override
    protected void initView() {
        setToolbarTitle("选择品牌");
        mRecyclerView = bindingView.mRecyclerView;
        indexBar = bindingView.indexBar;
        tvSideBarHint = bindingView.tvSideBarHint;
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCarBrandModels = new ArrayList<>();
        mDecoration = new SuspensionDecoration(this, mCarBrandModels);
        mRecyclerView.addItemDecoration(mDecoration);
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //indexbar初始化
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(linearLayoutManager);//设置RecyclerView的LayoutManager
        adapter = new CarBrandAdapter(this, mCarBrandModels);
        mRecyclerView.setAdapter(adapter);


    }
}
