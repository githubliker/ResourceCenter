package com.surface.resourcecenter.ui.dispatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.XPopup;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onItemCheckChangeListener;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.dispatch.adapter.StandardAdapter;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.dispatch.bean.InstrumentBean;
import com.surface.resourcecenter.ui.dispatch.bean.SystemRole;
import com.surface.resourcecenter.ui.dispatch.bean.TestArea;
import com.surface.resourcecenter.ui.dispatch.fragment.TestStandardFragment;
import com.surface.resourcecenter.ui.home.adapter.bean.PageInfo;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.view.CustomDispatchPopup;
import com.surface.resourcecenter.ui.view.CustomSignPopup;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DispatchTaskActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, onItemCheckChangeListener {
    private final static String TAG = DispatchTaskActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private List<TestItemsBean> mStandardList = new ArrayList<>();
    private List<InstrumentBean> mInstrumentList = new ArrayList<>();
    private List<SystemRole> mRoleList = new ArrayList<>();
    private List<TestArea> mAreaList = new ArrayList<>();
    private RadioGroup mStandardGrop;
    StandardAdapter adapter;

    private DispatchBean dispatchBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_dispatch_layout);
        dispatchBean = (DispatchBean) getIntent().getSerializableExtra("data");
        initTitle();

        mStandardGrop = findViewById(R.id.radioGroup_gender);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        adapter = new StandardAdapter(this);
        adapter.initData(mStandardList);
        adapter.setOnCheckChangeListener(this);
        mRecyclerView.setAdapter(adapter);
        mStandardGrop.setOnCheckedChangeListener(this);

        getTestItems(dispatchBean.getSampleType());
        getSystemRoles();
        getTestAreas();
        getInstruments();
    }

    private void getTestItems(String sampleTypeId){
        HashMap params = new HashMap();
        params.put("type",sampleTypeId);
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_TEST_ITEMS, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<TestItemsBean>>(){}.getType();
                    mStandardList.clear();
                    mStandardList = gson.fromJson(datas, userListType);
                    adapter.initData(mStandardList);
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

    private void getSystemRoles(){
        HashMap params = new HashMap();
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_SYSTEM_ROLES, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<SystemRole>>(){}.getType();
                    mRoleList.clear();
                    mRoleList = gson.fromJson(datas, userListType);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
    private void initTitle() {
        ImageView leftButton = findViewById(R.id.leftButton);
        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageResource(R.mipmap.common_back);
        TextView title = findViewById(R.id.title);
        title.setText("调度信息");
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb_temp = findViewById(group.getCheckedRadioButtonId());
        String position = (String)rb_temp.getTag();
        Log.d("tag","------------"+position);

        adapter.initData(mStandardList);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onItemClick(View view,int position, boolean isChecked) {
        if(view instanceof CheckBox){
            mStandardList.get(position).setChecked(isChecked);
            if(!isChecked){
                mStandardList.get(position).setArea("试验工区");
                mStandardList.get(position).setPerson("试验人员");
                mStandardList.get(position).setInstrument("试验仪器");
                adapter.notifyDataSetChanged();
            }
        } else if(view instanceof LinearLayout){
            Log.e(TAG,"点击弹出弹窗分配任务");
            CustomDispatchPopup customPopup = new CustomDispatchPopup(DispatchTaskActivity.this);
            new XPopup.Builder(DispatchTaskActivity.this)
                    .autoOpenSoftInput(false)
                    .asCustom(customPopup)
                    .show();
            customPopup.setData(mStandardList.get(position).getName(),position,mAreaList,mRoleList,mInstrumentList);
            customPopup.setOnDismisListener(popupDismisListener);
        }
    }

    CustomDispatchPopup.PopupDismisListener popupDismisListener = new CustomDispatchPopup.PopupDismisListener() {
        @Override
        public void onDismiss(int index) {
            Log.d(TAG,"popup dismiss");
            String area = "试验工区",per="试验人员",instru ="试验仪器";
            for(int i =0;i<mAreaList.size();i++){
                if(mAreaList.get(i).isSelect()){
                    area =mAreaList.get(i).getName();
                    break;
                }
            }
            for(int i =0;i<mRoleList.size();i++){
                if(mRoleList.get(i).isSelect()){
                    per = mRoleList.get(i).getName();
                    break;
                }
            }
            for(int i =0;i<mInstrumentList.size();i++){
                if(mInstrumentList.get(i).isSelect()){
                    instru = mInstrumentList.get(i).getName();
                    break;
                }
            }
            mStandardList.get(index).setArea(area);
            mStandardList.get(index).setPerson(per);
            mStandardList.get(index).setInstrument(instru);
            adapter.notifyDataSetChanged();
        }
    };
    public static void launch(Context context, DispatchBean bean) {
        Intent intent = new Intent(context, DispatchTaskActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",bean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
