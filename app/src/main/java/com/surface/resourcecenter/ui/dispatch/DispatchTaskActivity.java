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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.appcompat.widget.Toolbar;
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
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.data.utils.ToastUtils;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.dispatch.adapter.StandardAdapter;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.dispatch.bean.InstrumentBean;
import com.surface.resourcecenter.ui.dispatch.bean.SystemRole;
import com.surface.resourcecenter.ui.dispatch.bean.TestArea;
import com.surface.resourcecenter.ui.dispatch.fragment.TestStandardFragment;
import com.surface.resourcecenter.ui.home.adapter.bean.PageInfo;
import com.surface.resourcecenter.ui.sample.SampleRecvActivity;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.view.CustomDispatchPopup;
import com.surface.resourcecenter.ui.view.CustomSignPopup;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class DispatchTaskActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, onItemCheckChangeListener, View.OnClickListener {
    private final static String TAG = DispatchTaskActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private List<TestItemsBean> mStandardList = new ArrayList<>();
    private List<InstrumentBean> mInstrumentList = new ArrayList<>();
    private List<SystemRole> mRoleList = new ArrayList<>();
    private List<TestArea> mAreaList = new ArrayList<>();
    private Spinner ratifier,checker;
    private RadioGroup mStandardGrop;
    StandardAdapter adapter;
    private TextView mSave;

    private DispatchBean dispatchBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_dispatch_layout);
        dispatchBean = (DispatchBean) getIntent().getSerializableExtra("data");
        initTitle();

        mSave = findViewById(R.id.save);
        checker = findViewById(R.id.checker);
        ratifier = findViewById(R.id.ratifier);
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
        mSave.setOnClickListener(this);

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
        StatusBarUtil.setPaddingSmart(this,findViewById(R.id.toolbar));
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("调度信息");

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
                mStandardList.get(position).setArea(null);
                mStandardList.get(position).setPerson(null);
                mStandardList.get(position).setInstrument(null);
                adapter.notifyDataSetChanged();
            }
        } else if(view instanceof LinearLayout){
            Log.e(TAG,"点击弹出弹窗分配任务");
            if(mInstrumentList.size() == 0){
                getInstruments();
            }
            if(mAreaList.size() == 0){
                getTestAreas();
            }
            if(mRoleList.size() == 0){
                getSystemRoles();
            }
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
            ArrayList<TestArea> areas = new ArrayList<>() ;
            ArrayList<InstrumentBean> instrus = new ArrayList<>() ;
            ArrayList<SystemRole> pers = new ArrayList<>() ;
            for(int i =0;i<mAreaList.size();i++){
                if(mAreaList.get(i).isSelect()){
                    areas.add(mAreaList.get(i));
                }
            }
            for(int i =0;i<mRoleList.size();i++){
                if(mRoleList.get(i).isSelect()){
                    pers.add(mRoleList.get(i));
                }
            }
            for(int i =0;i<mInstrumentList.size();i++){
                if(mInstrumentList.get(i).isSelect()){
                    instrus.add(mInstrumentList.get(i));
                }
            }
            mStandardList.get(index).setArea(areas);
            mStandardList.get(index).setPerson(pers);
            mStandardList.get(index).setInstrument(instrus);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                dispatchTask();
                break;
        }
    }

    private void dispatchTask(){
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String myDate = format.format(new Date());
        JSONObject params = new JSONObject();
        try {
            params.put("dispatch_time",myDate);
            params.put("id",dispatchBean.getId());
            params.put("ratifier",ratifier.getSelectedItem().toString());
            params.put("checker",checker.getSelectedItem().toString());
            params.put("state",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetworkService service = new NetworkService();
        service.setRequestForJson(0, params.toString(), ApiUrl.URL_SAMPLE_DISPATCH, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String msg = json.getString("msg");
                    if("操作成功".equals(msg)){
                        dispatchTestItemsTask();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void dispatchTestItemsTask(){
        NetworkService service = new NetworkService();
        service.setRequestForJson(0, generDispatchData(), ApiUrl.URL_TEST_DISPATCH, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String msg = json.getString("msg");
                    ToastUtils.ShowCenterToast(DispatchTaskActivity.this,msg);
                }catch (Exception e){}
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
    private String generDispatchData(){
        JSONArray jsonArray = new JSONArray();
        try {
            for(int i = 0;i<mStandardList.size();i++){
                if(mStandardList.get(i).isChecked()){
                    JSONObject json = new JSONObject();
                    json.put("dispatchId",dispatchBean.getId());
                    json.put("experimentId",mStandardList.get(i).getId());
                    String areas = "";
                    for(int m = 0;m <mStandardList.get(i).getArea().size();m++){
//                        areas +=mStandardList.get(i).getArea().get(m).getId() +",";
                        areas +=mStandardList.get(i).getArea().get(m).getId();
                        break;
                    }
                    json.put("areaId",areas);
                    String insts = "";
                    for(int m = 0;m <mStandardList.get(i).getArea().size();m++){
//                        insts +=mStandardList.get(i).getInstrument().get(m).getId() +",";
                        insts +=mStandardList.get(i).getInstrument().get(m).getId();
                        break;
                    }
                    json.put("instrumentId",insts);
                    String operas = "";
                    for(int m = 0;m <mStandardList.get(i).getArea().size();m++){
//                        operas +=mStandardList.get(i).getPerson().get(m).getId() +",";
                        operas +=mStandardList.get(i).getPerson().get(m).getId();
                        break;
                    }
                    json.put("operater",operas);
//                    json.put("state",1);
                    jsonArray.put(json);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray.toString();
    }
}
