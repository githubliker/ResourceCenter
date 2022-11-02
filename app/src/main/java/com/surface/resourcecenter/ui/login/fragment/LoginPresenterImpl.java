package com.surface.resourcecenter.ui.login.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.surface.resourcecenter.data.Consts;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.sp.SpManager;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.login.bean.UserInfo;
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
            json.put("name", phone);
            json.put("password",password);
        }catch (JSONException e){}
        NetworkService service = new NetworkService();
        service.setRequestForJson(0, json.toString(), ApiUrl.URL_LOGIN, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    String msg = json.getString("msg");
                    if(!TextUtils.isEmpty(msg) && msg.equals("操作成功")){
                        Gson gson = new Gson();
                        Type userListType = new TypeToken<UserInfo>(){}.getType();
                        UserInfo info = gson.fromJson(datas, userListType);
                        SpManager.getInstance().set(Consts.USRID, info.getId());
                        SpManager.getInstance().set(Consts.USRNAME, info.getRealName());
                        SpManager.getInstance().set(Consts.LOGINROLES,info.getRoles());
                        loginView.loginSuccessForUI();
                    } else {
                        loginView.loginDataError(msg);
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onFailed(int what, Response<String> response) {
                loginView.loginDataError("网络连接异常");
            }
        });
    }
    private void queryUserRole() {

    }

}
