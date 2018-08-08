package cn.com.quickpark.sspdelicacy.ui.homepark;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.MVPBaseFragment;
import cn.com.quickpark.sspdelicacy.databinding.FragmentHomePageBinding;
import cn.com.quickpark.sspdelicacy.rxbus.RxBus;
import cn.com.quickpark.sspdelicacy.rxbus.RxCodeConstants;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 首页fragment
 */
public class HomePageFragment extends MVPBaseFragment<FragmentHomePageBinding, HomePageContract.Presenter>
        implements HomePageContract.View, View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        // 查询车辆信息
        mPresenter.queryCarState();
        bindingView.tvSceneCar.setOnClickListener(this);
        bindingView.tvSaveCar.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initRxBus() {
        RxBus.getInstance().toObservable(RxCodeConstants.NOTICE, Integer.class)
                .compose(bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mPresenter.queryCarState());

    }
    //
//           @Override
//        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//            super.onActivityCreated(savedInstanceState);
//
//
////        bindingView.viewTakeCar.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(getActivity(), TakeCarActivity.class));
////            }
////        });
////        bindingView.tvSaveCar.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(getActivity(), LoginActivity.class));
////            }
////        });
//
//            // bindingView.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//
//
////        bindingView.mRecyclerView.setAdapter(new HomeGarageAdapter(getContext(), list));
//
//
////        ArrayList<HomeGarageBean> list = new ArrayList<>();
////        HomeGarageBean bean;
////        for (int i = 0; i < 20; i++) {
////            bean = new HomeGarageBean();
////            bean.setQuantity(i);
////            bean.setTotal(20);
////            list.add(bean);
////        }
////
////        View view;
////
////        ViewTreeObserver vto = bindingView.mRecyclerView.getViewTreeObserver();
////        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
////            @Override
////            public void onGlobalLayout() {
////                bindingView.mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
////                height = bindingView.mRecyclerView.getHeight();
////
////            }
////        });
////        for (int i = 0; i < list.size(); i++) {
////            view = LayoutInflater.from(getContext()).inflate(R.layout.item_home_garage, null);
////            final View tv = view.findViewById(R.id.tv_garage);
////            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
////            params.height = (int) (list.get(i).getQuantity() / (float) list.get(i).getTotal() * height);
////            Logger.e("params.height  " + params.height);
////            tv.setLayoutParams(params);
////            bindingView.mRecyclerView.addView(view);
////        }
//
//
//        }

    @Override
    protected HomePageContract.Presenter createPresenter() {
        return new HomePagePresenter();
    }

    @Override
    public void setSceneCar(String license) {
        bindingView.tvSceneCar.setText(license);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void setBeBeingCar(String license) {
        if (!TextUtils.isEmpty(license)) {
            bindingView.tvSaveCar.setCompoundDrawables(null, null, null, null);
            bindingView.tvSaveCar.setText(license);
        } else {
            bindingView.tvSaveCar.setCompoundDrawablesRelativeWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.ic_scan), null, null);
            bindingView.tvSaveCar.setText("扫码存车");
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_SceneCar:
                //startNewActivity(DemoActivity.class);
                //startNewActivity();
                break;
            case R.id.tv_save_car:
                mPresenter.judgeActivity();
                break;
        }
    }
}
