package com.surface.resourcecenter.ui.report.fragment;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onRecycleViewItemClickListener;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.report.ReportDetailActivity;
import com.surface.resourcecenter.ui.report.adapter.ReportListAdapter;
import com.surface.resourcecenter.ui.report.bean.ReportBean;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReportListFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    RefreshLayout refreshLayout;
    private ArrayList<ReportBean> reportList = new ArrayList<>();
    ReportListAdapter adapter;
    private int current = 1;
    private int size = 5;

    onRecycleViewItemClickListener mItemClickListener = new onRecycleViewItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            String path = reportList.get(position).getPath();
            ReportDetailActivity.launch(getContext(), reportList.get(position));
        }
    };


    void initHomeData(){
        HashMap params = new HashMap();
//        params.put("current",current+"");
//        params.put("size",size+"");
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_REPORT_LIST, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    reportList.clear();
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    JSONObject j = new JSONObject(datas);
                    String records = j.getString("records");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<ReportBean>>(){}.getType();
                    reportList = gson.fromJson(records, userListType);
                    adapter.setAdapterData(reportList);
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
        return R.layout.rc_fragment_home_layout;
    }

    @Override
    public void init(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new ReportListAdapter(reportList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
        initHomeData();
        refreshLayout.setOnMultiListener(new SimpleMultiListener(){
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
                super.onStateChanged(refreshLayout, oldState, newState);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
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
