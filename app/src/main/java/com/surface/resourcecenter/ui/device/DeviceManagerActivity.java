package com.surface.resourcecenter.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.device.fragment.DeviceFragment;
import com.surface.resourcecenter.ui.device.fragment.GongQuFragment;


public class DeviceManagerActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, DeviceManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_activity_manager_group_control);
        getSupportFragmentManager().beginTransaction().add(R.id.channelContent,
                DeviceFragment.newInstance()).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
