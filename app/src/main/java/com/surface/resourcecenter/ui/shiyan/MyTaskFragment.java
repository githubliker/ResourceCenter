package com.surface.resourcecenter.ui.shiyan;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onRecycleViewItemClickListener;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.dispatch.DispatchTaskActivity;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyTaskFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    RefreshLayout refreshLayout;
    private ArrayList<DispatchBean> dispatchList = new ArrayList<>();
    HomePageToDoTaskAdapter adapter;
    private int current = 1;
    private int size = 10;

    onRecycleViewItemClickListener mItemClickListener = new onRecycleViewItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            switch (dispatchList.get(position).getState()){
                case 0:
                    DispatchTaskActivity.launch(getContext(),dispatchList.get(position));
                    break;
                case 1:
//                    final LoadingPopupView loadingPopup = (LoadingPopupView) new XPopup.Builder(getContext())
//                            .dismissOnBackPressed(false)
//                            .asLoading("正在加载中...")
//                            .show();
//                    loadingPopup.delayDismissWith(400, null);
                    DoTaskActivity.launch(getContext(),dispatchList.get(position));
                    break;
                case 2:

                    break;
                default:
                    DispatchTaskActivity.launch(getContext(),dispatchList.get(position));
                    break;
            }
        }
    };


    void initHomeData(boolean clearFront){
        HashMap params = new HashMap();
        params.put("current",current+"");
        params.put("size",size+"");
        params.put("state","1");
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_DISPATCH_LIST, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    refreshLayout.finishLoadMore();
                    refreshLayout.finishRefresh();
                    if(clearFront){
                        dispatchList.clear();
                    }
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    JSONObject j = new JSONObject(datas);
                    String records = j.getString("records");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<DispatchBean>>(){}.getType();
                    ArrayList<DispatchBean> result = gson.fromJson(records, userListType);
                    dispatchList.addAll(result);
                    adapter.setAdapterData(dispatchList);
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
        adapter = new HomePageToDoTaskAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
        initHomeData(true);
        refreshLayout.setOnMultiListener(new SimpleMultiListener(){
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
                super.onStateChanged(refreshLayout, oldState, newState);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                current = 1;
                initHomeData(true);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                current++;
                initHomeData(false);
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
