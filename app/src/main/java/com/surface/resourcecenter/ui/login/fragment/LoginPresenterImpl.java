package com.surface.resourcecenter.ui.login.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pll
 * @date 2018/7/10
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private Context context;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    public void loginCommit(final String phone, final String password) {
        LoginToService(phone,password);

//        if(!CheckUtil.checkPhoneNumber(phone)){
//            loginView.loginDataError("请输入正确的手机号");
//            return;
//        }else

        //// 测试账号
//        phone = "8373A9180718190909/likedong";
//        phone = "liyonghui";
//        phone = "18734139467";
//        password = "123456";


//        if (!CheckUtil.checkPassWord(password)) {
//            loginView.loginDataError("密码不符合规范");
//        } else {

//        }

//        DefaultHttpManager.getInstance().callForStringData(HttpConsts.HTTP_GET, Api.registToIhi(phone), null, new DefaultHttpCallback<String>() {
//            @Override
//            public void handleResponse(String data) {
//                Log.e("registToIhi","注册至清流迅结果："+data);
//                LoginToService(phone,password);
//            }
//
//            @Override
//            public void onResponseError(Throwable t) {
//
//            }
//        });
    }


    private void LoginToService(String phone, String password){
        JSONObject json = new JSONObject();

//        map.put("name", phone);
//        map.put("password",password);
        try {
            json.put("name", "hcAdmin");
            json.put("password","hcdq@123");
        }catch (JSONException e){}
        NetworkService service = new NetworkService();
        service.setRequestForJson(0, json.toString(), ApiUrl.URL_LOGIN, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                loginView.loginSuccessForUI();
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
    private void queryUserRole() {

    }

}
