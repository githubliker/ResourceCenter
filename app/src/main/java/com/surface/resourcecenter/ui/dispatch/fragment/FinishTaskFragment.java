package com.surface.resourcecenter.ui.dispatch.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onRecycleViewItemClickListener;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.home.adapter.HomePageTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;
import com.surface.resourcecenter.ui.sample.SampleRecvActivity;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FinishTaskFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ArrayList<DispatchBean> dispatchList = new ArrayList<>();
    HomePageToDoTaskAdapter adapter;
    private int Index = 0;
    private int current = 1;
    private int size = 5;


    onRecycleViewItemClickListener mItemClickListener = new onRecycleViewItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            SampleRecvActivity.launch(getContext());
        }
    };


    void initHomeData(){
        HashMap params = new HashMap();
        params.put("current",current+"");
        params.put("size",size+"");
        params.put("code","0");
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_DISPATCH_LIST, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    dispatchList.clear();
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    JSONObject j = new JSONObject(datas);
                    String records = j.getString("records");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<DispatchBean>>(){}.getType();
                    dispatchList = gson.fromJson(records, userListType);
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

        mRecyclerView = view.findViewById(R.id.recyclerView);
        initHomeData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),5);
        mRecyclerView.setLayoutManager(layoutManager);
        HomePageToDoTaskAdapter adapter = new HomePageToDoTaskAdapter();
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
