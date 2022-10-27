package com.surface.resourcecenter.ui.device.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.device.GongQuDetailActivity;
import com.surface.resourcecenter.ui.device.adapter.DeviceAdapter;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.dispatch.bean.InstrumentBean;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DeviceFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<InstrumentBean> mInstrumentList = new ArrayList<>();
    DeviceAdapter adapter;
    private int current = 0,size = 20;
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

    private void getInstruments(){
        HashMap params = new HashMap();
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_INSTRUMENTS, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<InstrumentBean>>(){}.getType();
                    mInstrumentList.clear();
                    mInstrumentList = gson.fromJson(datas, userListType);
                    adapter.setData(mInstrumentList);
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
        return R.layout.rc_fragment_device_layout;
    }

    @Override
    public void init(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        getInstruments();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new DeviceAdapter();
        mRecyclerView.setAdapter(adapter);

        adapter.setOnClickListener(mItemClickListener);
        initTitle(view);
    }

    private void initTitle(View view) {
        StatusBarUtil.setPaddingSmart(getContext(),view.findViewById(R.id.toolbar));
        final Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        toolbar.setTitle("仪器管理");

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
