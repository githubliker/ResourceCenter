package com.surface.resourcecenter.ui.device.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.device.GongQuDetailActivity;
import com.surface.resourcecenter.ui.device.adapter.GongQuAdapter;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;
import com.surface.resourcecenter.ui.sample.SampleRecvActivity;
import com.surface.resourcecenter.ui.usrManager.UsrManagerFragment;

import java.util.ArrayList;
import java.util.List;


public class GongQuFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<String> itemList = new ArrayList<>();
    public static GongQuFragment newInstance() {

        Bundle args = new Bundle();
        GongQuFragment fragment = new GongQuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GongQuDetailActivity.launch(getContext());
        }
    };

    GongQuAdapter.onSwipeListener onSwipeListener = new GongQuAdapter.onSwipeListener() {
        @Override
        public void onDel(int pos) {

        }

        @Override
        public void onEdit(int pos) {

        }
    };

    void initHomeData(){
        String[] gongQu = getResources().getStringArray(R.array.device_gongqu);
        for(int i = 0;i< gongQu.length;i++){
            itemList.add(gongQu[i]);
        }
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_device_layout;
    }

    @Override
    public void init(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerView);
        initHomeData();

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        GongQuAdapter adapter = new GongQuAdapter(itemList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
        adapter.setOnSwipeListener(onSwipeListener);
        initTitle(view);
    }

    private void initTitle(View view) {
        ImageView leftButton = view.findViewById(R.id.leftButton);
        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageResource(R.mipmap.common_back);
        TextView title = view.findViewById(R.id.title);

        title.setText("");
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
