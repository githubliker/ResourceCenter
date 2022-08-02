package com.surface.resourcecenter.ui.device.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.device.GongQuDetailActivity;
import com.surface.resourcecenter.ui.device.adapter.DeviceAdapter;

import java.util.ArrayList;
import java.util.List;


public class DeviceFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<String> itemList = new ArrayList<>();
    public static DeviceFragment newInstance() {

        Bundle args = new Bundle();
        DeviceFragment fragment = new DeviceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            initAllActionList();
        }
    };

    private void initAllActionList(){
        String[] gongQu = {"删除","维修","封存/启封","报废","检定"};
        new XPopup.Builder(getContext())
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .asCenterList("请选择所需要的操作", gongQu,new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
                        Toast.makeText(getContext(),"选择了第"+position+"条数据",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    void initHomeData(){
        String[] gongQu = getResources().getStringArray(R.array.device_detail);
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        DeviceAdapter adapter = new DeviceAdapter(itemList);
        mRecyclerView.setAdapter(adapter);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.leftMargin = 20;
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 80;

        mRecyclerView.setLayoutParams(layoutParams);
        adapter.setOnClickListener(mItemClickListener);
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
