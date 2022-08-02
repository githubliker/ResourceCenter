package com.surface.resourcecenter.ui.chart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.chart.fragment.BarChartFragment;
import com.surface.resourcecenter.ui.chart.fragment.BarChartFragment1;
import com.surface.resourcecenter.ui.chart.fragment.ChartFragment;
import com.surface.resourcecenter.ui.device.fragment.DeviceFragment;


public class ChartManagerActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, ChartManagerActivity.class);
        context.startActivity(intent);
    }
    BaseFragment mCurrent;
    RadioGroup mGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_activity_chart_manager);
        mCurrent = ChartFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.channelContent,
                mCurrent).commitAllowingStateLoss();
        initTitle();
        mGroup = findViewById(R.id.radio_group);
//        mGroup.check(0);

        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                getSupportFragmentManager().beginTransaction().remove(mCurrent);

                if(i == R.id.radio_button1){
                    mCurrent = ChartFragment.newInstance();
                } else if(i == R.id.radio_button2){
                    mCurrent = BarChartFragment.newInstance();
                } else {
                    mCurrent = BarChartFragment1.newInstance();
                }
                getSupportFragmentManager().beginTransaction().add(R.id.channelContent,
                        mCurrent).commitAllowingStateLoss();
            }
        });
    }

    private void initTitle() {
        ImageView leftButton = findViewById(R.id.leftButton);
        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageResource(R.mipmap.common_back);
        TextView title = findViewById(R.id.title);

        title.setText("数据分析");
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
