package com.surface.resourcecenter.ui.login.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

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
        loginView.loginSuccessForUI();
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
        Map<String, String> map = new HashMap<>();
        map.put("loginName", phone);

    }
    private void queryUserRole() {

    }

}
