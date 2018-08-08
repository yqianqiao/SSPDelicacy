package cn.com.quickpark.sspdelicacy.ui.my;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.adapter.MyAdapter;
import cn.com.quickpark.sspdelicacy.base.MVPBaseFragment;
import cn.com.quickpark.sspdelicacy.bean.my.MyInfoBean;
import cn.com.quickpark.sspdelicacy.constants.Constants;
import cn.com.quickpark.sspdelicacy.databinding.FragmentMyBinding;
import cn.com.quickpark.sspdelicacy.ui.my.cersonalcenter.PersonalCenterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends MVPBaseFragment<FragmentMyBinding, MyContract.Presenter>
        implements MyContract.View, View.OnClickListener {


    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private MyInfoBean.UserBean userInfo;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }


    @Override
    protected void initView() {
        mRecyclerView = bindingView.mRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        //adapter = new MyAdapter(getContext());

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void initData() {
        //获取个人信息
        // mPresenter.getMyInfo();
        bindingView.viewBg.setOnClickListener(this);
    }

    @Override
    protected MyContract.Presenter createPresenter() {
        return new MyPresenter();
    }

    @Override
    public void showUserInfo(MyInfoBean.UserBean userBean) {
        userInfo = userBean;
        Glide.with(this)
                .load(userBean.getAvatarPic())
                .error(R.mipmap.ic_launcher_round)
                .into(bindingView.ivAvatarPic);
        bindingView.tvNickname.setText(userBean.getNickName());
        bindingView.tvSignature.setText(userBean.getSignature());
    }

    @Override
    public void showCarListInfo(ArrayList<MyInfoBean.CarListBean> carListBean) {
        mRecyclerView.setAdapter(new MyAdapter(mActivity, carListBean));
        //  adapter.setDataList(carListBean);
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取个人信息
        mPresenter.getMyInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_bg:
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.USER_INFO, userInfo);
                startNewActivity(PersonalCenterActivity.class, bundle);
                break;

        }
    }
}
