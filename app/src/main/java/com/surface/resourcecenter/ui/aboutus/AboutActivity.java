package com.surface.resourcecenter.ui.aboutus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.utils.ApplicationUtils;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.ui.BaseActivity;


public class AboutActivity extends BaseActivity {
    private TextView versionName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_about_layout);
        initTitle();
        versionName = findViewById(R.id.versionName);
        String versionNameStr = ApplicationUtils.getVersionName(this);
        versionName.setText("V" + versionNameStr);
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
        toolbar.setTitle("关于我们");

    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }
}
