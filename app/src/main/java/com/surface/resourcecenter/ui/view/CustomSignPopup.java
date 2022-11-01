package com.surface.resourcecenter.ui.view;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.core.CenterPopupView;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.sp.SpManager;
import com.surface.resourcecenter.data.utils.FileHelper;
import com.surface.resourcecenter.data.utils.ToastUtils;
import com.surface.resourcecenter.ui.sample.bean.SampleImageBean;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class CustomSignPopup extends CenterPopupView {
    public CustomSignPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_custom_sign;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        SignatureView signatureView = findViewById(R.id.signature);
        findViewById(R.id.sign_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
                dismiss();
            }
        });
        findViewById(R.id.save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String path = FileHelper.getPicDir(getContext())+System.currentTimeMillis()+".png";
                    if (signatureView.save(path)) {
                        uploadSign(path);
                    } else {
                        ToastUtils.ShowCenterToast(getContext(),"存储失败，请重试...");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        findViewById(R.id.clear).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
            }
        });
    }
    protected void onShow() {
        super.onShow();
    }

    private void uploadSign(String path){
        File file = new File(path);
        NetworkService service = new NetworkService();
        service.upload(0, ApiUrl.URL_USRINFO_SIGN_UPDATE, file, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+what+" / "+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String path = json.getString("data");
                    if(TextUtils.isEmpty(path)){
                        ToastUtils.ShowCenterToast(getContext(),"上传失败，请重试...");
                    } else {
                        fixUserInfo(path);
                    }
                }catch (Exception e){
                    ToastUtils.ShowCenterToast(getContext(),"上传失败，请重试...");
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                ToastUtils.ShowCenterToast(getContext(),"上传失败，请重试...");

            }
        });
    }

    private void fixUserInfo(String path){
        HashMap params = new HashMap();
        params.put("id", SpManager.getInstance().get(Consts.USRID));
        params.put("sign",path);

        NetworkService service = new NetworkService();
        service.setRequestForJson(0, params.toString(), ApiUrl.URL_USRINFO_UPDATE, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                dismiss();
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
