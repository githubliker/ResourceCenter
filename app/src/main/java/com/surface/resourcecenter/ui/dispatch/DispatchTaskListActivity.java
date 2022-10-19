package com.surface.resourcecenter.ui.dispatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.dispatch.fragment.TodoTaskFragment;


/**
 * Created by yangzhipeng on 2018/6/4.
 */

public class DispatchTaskListActivity extends BaseActivity {

    private String TAG = "HomeActivity";
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    public static void launch(Context context){
        Intent intent = new Intent(context, DispatchTaskListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_sample_list_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                new TodoTaskFragment()).commitAllowingStateLoss();
        initTitle();
    }

    private void initTitle() {
        ImageView leftButton = findViewById(R.id.leftButton);
        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageResource(R.mipmap.common_back);
        TextView title = findViewById(R.id.title);
        title.setText("样品管理");
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
