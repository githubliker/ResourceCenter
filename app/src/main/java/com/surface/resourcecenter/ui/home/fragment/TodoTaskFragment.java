package com.surface.resourcecenter.ui.home.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.dispatch.DispatchTaskActivity;
import com.surface.resourcecenter.ui.home.adapter.HomePageTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.HomePageToDoTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TodoTaskFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<TaskItem> itemList = new ArrayList<>();
    private int Index = 0;
    private int current = 1;
    private int size = 5;

    View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (Index){
                case 0:
                    DispatchTaskActivity.launch(getContext());
                    break;
                case 1:
                    DoTaskActivity.launch(getContext());
                    break;
                case 2:

                    break;
                default:
                    DispatchTaskActivity.launch(getContext());
                    break;
            }
        }
    };


    void initHomeData(){
        HashMap params = new HashMap();
        params.put("current",current+"");
        params.put("size",size+"");
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_SAMPLE_LIST, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
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
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
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
