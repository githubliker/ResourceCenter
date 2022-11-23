package com.surface.resourcecenter.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lxj.statelayout.StateLayout;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.utils.ToastUtils;
import com.surface.resourcecenter.ui.sample.SampleRecvActivity;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Description:
 * Create by dance, at 2018/12/9
 */
public abstract class BaseFragment extends Fragment {
    View view;
    boolean isInit = false;
    StateLayout stateLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            stateLayout = new StateLayout(getContext()).wrap(view).showLoading(false);
        }
        return stateLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        safeInit();
    }

    private void safeInit() {
        if (getUserVisibleHint() && view!=null) {
            if (!isInit) {
                isInit = true;
                init(view);
                stateLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stateLayout.showContent();
                    }
                },10);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        safeInit();
    }

    protected abstract int getLayoutId();
    public abstract void init(View view);

    protected TestItemsBean getTestItems(String id){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        for(int i =0;i< activity.mStandardList.size();i++){
            if(activity.mStandardList.get(i).getId().equals(id)){
                return activity.mStandardList.get(i);
            }
        }
        return new TestItemsBean();
    }
    protected void saveShiyanData(String params){
        NetworkService service = new NetworkService();
        service.setRequestForJson(0, params, ApiUrl.URL_SAVE_TEST_DATA, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String msg = json.getString("msg");
                    ToastUtils.ShowCenterToast(getContext(),msg);
                }catch (Exception e){}
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    protected void getShiyanData(String sampleId,String experimentId){
        HashMap params = new HashMap();
        params.put("sample",sampleId);
        params.put("experiment",experimentId);
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_GET_TEST_DATA, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String msg = json.getString("msg");
                    ToastUtils.ShowCenterToast(getContext(),msg);
                }catch (Exception e){}
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

}
