package com.surface.resourcecenter.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.device.fragment.GongQuFragment;
import com.surface.resourcecenter.ui.usrManager.UsrManagerFragment;


public class GongQuManagerActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, GongQuManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_activity_manager_group_control);
        getSupportFragmentManager().beginTransaction().add(R.id.channelContent,
                GongQuFragment.newInstance()).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
