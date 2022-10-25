package com.surface.resourcecenter.ui.device.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.device.adapter.GongQuTestAdapter;
import com.surface.resourcecenter.ui.dispatch.DispatchTaskActivity;
import com.surface.resourcecenter.ui.dispatch.bean.SystemRole;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GongQuTestFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private TextView mHeader;
    private List<TestItemsBean> itemList = new ArrayList<>();
    GongQuTestAdapter adapter;

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

    private void getTestAreaItems(){
        HashMap params = new HashMap();
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_TEST_AREAS_ITEMS, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<TestItemsBean>>(){}.getType();
                    itemList.clear();
                    itemList = gson.fromJson(datas, userListType);
                    adapter.setData(itemList);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
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
        getTestAreaItems();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new GongQuTestAdapter();
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
