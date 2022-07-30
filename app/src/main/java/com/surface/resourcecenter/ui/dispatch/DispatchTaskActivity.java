package com.surface.resourcecenter.ui.dispatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.dispatch.adapter.StandardAdapter;
import com.surface.resourcecenter.ui.dispatch.adapter.bean.standardItem;
import com.surface.resourcecenter.ui.dispatch.fragment.TestStandardFragment;
import com.surface.resourcecenter.ui.home.adapter.bean.PageInfo;

import java.util.ArrayList;
import java.util.List;


public class DispatchTaskActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private final static String TAG = DispatchTaskActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private List<standardItem> mStandardList = new ArrayList<>();
    private RadioGroup mStandardGrop;
    StandardAdapter adapter;
    private List<PageInfo> pageInfos = new ArrayList<>();

    public ViewPager viewPager;
    private MainAdapter pagerAdapter;
    private TextView mIndicate,mTestItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_dispatch_layout);
        initTitle();
        initStandardList();
        mIndicate = findViewById(R.id.indicate);
        mTestItem = findViewById(R.id.testItem);
        viewPager = findViewById(R.id.viewPager);
        mStandardGrop = findViewById(R.id.radioGroup_gender);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new StandardAdapter(this);
        adapter.initData(mStandardList);
        mRecyclerView.setAdapter(adapter);
        mStandardGrop.setOnCheckedChangeListener(this);
        pagerAdapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTestItem.setText(pageInfos.get(position).title);
                mIndicate.setText((position+1)+" / "+pageInfos.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initFragment();
    }


    private void initStandardList(){
        mStandardList.clear();
        mStandardList.add(new standardItem("外观尺寸检查",false));
        mStandardList.add(new standardItem("密度测量",false));
        mStandardList.add(new standardItem("环刚度",false));
        mStandardList.add(new standardItem("压扁试验",false));
        mStandardList.add(new standardItem("落锤冲击",false));
        mStandardList.add(new standardItem("堆卡软化温度",false));
        mStandardList.add(new standardItem("图层厚度试验",false));
    }
    private void initStandardList1(){
        mStandardList.clear();
        mStandardList.add(new standardItem("外观尺寸检查",true));
        mStandardList.add(new standardItem("密度测量",true));
        mStandardList.add(new standardItem("环刚度",true));
        mStandardList.add(new standardItem("压扁试验",true));
        mStandardList.add(new standardItem("落锤冲击",true));
        mStandardList.add(new standardItem("堆卡软化温度",true));
        mStandardList.add(new standardItem("图层厚度试验",false));
    }

    private void initStandardList2(){
        mStandardList.clear();
        mStandardList.add(new standardItem("外观尺寸检查",false));
        mStandardList.add(new standardItem("密度测量",true));
        mStandardList.add(new standardItem("环刚度",false));
        mStandardList.add(new standardItem("压扁试验",true));
        mStandardList.add(new standardItem("落锤冲击",true));
        mStandardList.add(new standardItem("堆卡软化温度",false));
        mStandardList.add(new standardItem("图层厚度试验",true));
    }
    private void initTitle() {
        ImageView leftButton = findViewById(R.id.leftButton);
        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageResource(R.mipmap.common_back);
        TextView title = findViewById(R.id.title);
        title.setText("调度信息");
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb_temp = findViewById(group.getCheckedRadioButtonId());
        String position = (String)rb_temp.getTag();
        Log.d("tag","------------"+position);
        if(position.equals("1")){
            initStandardList();
        } else if(position.equals("2")){
            initStandardList1();
        } else if(position.equals("3")){
            initStandardList2();
        }
        adapter.initData(mStandardList);
        adapter.notifyDataSetChanged();
        initFragment();
    }

    private void initFragment(){
        pageInfos.clear();
        for(int i = 0;i<mStandardList.size();i++){
            if(mStandardList.get(i).isItemStatus()){
                pageInfos.add(new PageInfo(mStandardList.get(i).getItemName(),new TestStandardFragment()));
            }
        }
        pagerAdapter.notifyDataSetChanged();
        if(pageInfos.size()> 0){
            mTestItem.setText(pageInfos.get(0).title);
            mIndicate.setText(1+" / "+pageInfos.size());
        }
    }
    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return pageInfos.get(i).fragment;
        }

        @Override
        public int getCount() {
            return pageInfos.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageInfos.get(position).title;
        }
    }
    public static void launch(Context context) {
        Intent intent = new Intent(context, DispatchTaskActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
