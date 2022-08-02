package com.surface.resourcecenter.ui.device.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.device.adapter.GongQuTestAdapter;
import com.surface.resourcecenter.ui.dispatch.DispatchTaskActivity;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;

import java.util.ArrayList;
import java.util.List;


public class GongQuTestFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private TextView mHeader;
    private List<String> itemList = new ArrayList<>();
    private boolean isSwitch = false;

    public static GongQuTestFragment newInstance() {

        Bundle args = new Bundle();
        GongQuTestFragment fragment = new GongQuTestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new XPopup.Builder(getContext())
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asConfirm("试验仪器", "回路电阻测试仪", new OnConfirmListener() {
                        @Override
                        public void onConfirm() {
                            initAllDeviceList();
                        }
                    }, new OnCancelListener() {
                        @Override
                        public void onCancel() {
                        }
                    })
                    .setCancelText("确定")
                    .setConfirmText("修改")
                    .show();
        }
    };

    private void initAllDeviceList(){
        String[] gongQu = getResources().getStringArray(R.array.device_detail);
        new XPopup.Builder(getContext())
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .asBottomList("请选择所需要的的试验仪器", gongQu, null, 3, new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
                        Toast.makeText(getContext(),"选择了第"+position+"条数据",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
    void initHomeData(){

        String[] gongQu = getResources().getStringArray(R.array.device_test1);
        for(int i = 0;i< gongQu.length;i++){
            itemList.add(gongQu[i]);
        }
        itemList.add("<添加>");
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_gongqu_usr_control;
    }

    @Override
    public void init(View view) {
        mHeader = view.findViewById(R.id.group_header);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        initHomeData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(layoutManager);
        GongQuTestAdapter adapter = new GongQuTestAdapter(itemList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
        mHeader.setText("试验项目");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
