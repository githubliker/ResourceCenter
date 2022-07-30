package com.surface.resourcecenter.ui.usrManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseActivity;


public class UsrManagerActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, UsrManagerActivity.class);
        context.startActivity(intent);
    }

    private UsrManagerFragment usrManagerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_activity_manager_group_control);
        usrManagerFragment = UsrManagerFragment.newInstance(getIntent().getStringExtra("channel"));
        getSupportFragmentManager().beginTransaction().add(R.id.channelContent,
                usrManagerFragment).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
