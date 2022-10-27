package com.surface.resourcecenter.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.device.fragment.GongQuPersonFragment;
import com.surface.resourcecenter.ui.device.fragment.GongQuTestFragment;


public class GongQuDetailActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, GongQuDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_fragment_gongqu_detail_layout);
        getSupportFragmentManager().beginTransaction().add(R.id.person_content,
                GongQuPersonFragment.newInstance()).commitAllowingStateLoss();
        getSupportFragmentManager().beginTransaction().add(R.id.test_content,
                GongQuTestFragment.newInstance()).commitAllowingStateLoss();
        initTitle();
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
        toolbar.setTitle("高压开关检测工位");

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
