package com.surface.resourcecenter.ui.device.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.device.GongQuDetailActivity;
import com.surface.resourcecenter.ui.device.adapter.GongQuAdapter;
import com.surface.resourcecenter.ui.dispatch.bean.TestArea;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;
import com.surface.resourcecenter.ui.sample.SampleRecvActivity;
import com.surface.resourcecenter.ui.usrManager.UsrManagerFragment;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GongQuFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<TestArea> mAreaList = new ArrayList<>();
    GongQuAdapter adapter;
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

    private void getTestAreas(){
        HashMap params = new HashMap();
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_TEST_AREAS, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<TestArea>>(){}.getType();
                    mAreaList.clear();
                    mAreaList = gson.fromJson(datas, userListType);
                    adapter.setData(mAreaList);
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
        getTestAreas();

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new GongQuAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
        adapter.setOnSwipeListener(onSwipeListener);
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
        toolbar.setTitle("工区管理");

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
