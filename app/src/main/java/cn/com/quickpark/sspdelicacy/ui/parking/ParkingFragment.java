package cn.com.quickpark.sspdelicacy.ui.parking;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.view.Demo;
import cn.com.quickpark.sspdelicacy.view.ScheduleLine;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParkingFragment extends Fragment {


    public ParkingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parking, container, false);
//        ScheduleLine schedule = view.findViewById(R.id.schedule);
//        List<String> mlist = new ArrayList<>();
//        mlist.add("开始");
//        mlist.add("开始132");
//        mlist.add("开测试");
//        mlist.add("开始啊实");
//
//        schedule.setTextList(mlist);

        Demo dome = view.findViewById(R.id.demo);
        dome.setBackgroundResource(R.color.colorRed);
        return view;
    }


}
