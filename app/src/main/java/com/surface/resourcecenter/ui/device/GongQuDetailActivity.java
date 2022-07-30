package com.surface.resourcecenter.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.surface.resourcecenter.R;
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
        ImageView leftButton = findViewById(R.id.leftButton);
        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageResource(R.mipmap.common_back);
        TextView title = findViewById(R.id.title);
        title.setText("高压开关检测工位");
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
