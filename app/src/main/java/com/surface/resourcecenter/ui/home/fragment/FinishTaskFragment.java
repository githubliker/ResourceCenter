package com.surface.resourcecenter.ui.home.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.home.adapter.HomePageTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;
import com.surface.resourcecenter.ui.sample.SampleRecvActivity;

import java.util.ArrayList;
import java.util.List;


public class FinishTaskFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<TaskItem> itemList = new ArrayList<>();


    View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SampleRecvActivity.launch(getContext());
        }
    };


    void initHomeData(){
        itemList.add(new TaskItem("油浸式变压器","张劲松","李荣辉","2022.07.12","2022-07-15 12:56","待试验"));
        itemList.add(new TaskItem("油浸式变压器","张劲松","葛静波","2022.07.12","2022-07-15 15:56","待编制"));
        itemList.add(new TaskItem("油浸式变压器","张劲松","李荣辉","2022.07.12","2022-07-15 13:56","待审核"));
        itemList.add(new TaskItem("油浸式变压器","张劲松","李荣辉","2022.07.12","2022-07-15 17:56","已完成"));
        itemList.add(new TaskItem("油浸式变压器","张劲松","葛静波","2022.07.12","2022-07-16 14:56","被打回"));
        itemList.add(new TaskItem("油浸式变压器","张劲松","李荣辉","2022.07.12","2022-07-14 16:56","待调度"));
        itemList.add(new TaskItem("油浸式变压器","张劲松","张思德","2022.07.12","2022-07-13 18:56","已完成"));
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_home_layout;
    }

    @Override
    public void init(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerView);
        initHomeData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),5);
        mRecyclerView.setLayoutManager(layoutManager);
        HomePageToDoTaskAdapter adapter = new HomePageToDoTaskAdapter(itemList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
