package com.surface.resourcecenter.ui.login;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.login.fragment.LoginFragment;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity {

    public static final int FLAG_FINISH_SELF = 0x10000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_activity_login_layout);
        verifyStoragePermissions(this);
//        if (IService.getService(IUserData.class).isLogined()) {
//            Router.launchMainActivity(this);
//            finish();
//        }
        if (getIntent().getIntExtra("finish", 0) == FLAG_FINISH_SELF) {
            finish();
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content,
                    LoginFragment.newInstance()).commit();
        }
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_PHONE_STATE",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CAMERA",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.RECORD_AUDIO"};

    public void verifyStoragePermissions(Activity activity) {

        try {
            ArrayList<String> unApplyPermission = new ArrayList<>();
            for (int i = 0; i < PERMISSIONS_STORAGE.length; i++) {
                // 过滤出已申请的权限
                if (ActivityCompat.checkSelfPermission(activity, PERMISSIONS_STORAGE[i]) != PackageManager.PERMISSION_GRANTED) {
                    unApplyPermission.add(PERMISSIONS_STORAGE[i]);
                }
            }
            if (unApplyPermission.size() > 0) {
                // 未申请的权限再次申请
                ActivityCompat.requestPermissions(activity, unApplyPermission.toArray(new String[unApplyPermission.size()]), REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}