package com.surface.resourcecenter.ui.home.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.chart.ChartManagerActivity;
import com.surface.resourcecenter.ui.device.DeviceManagerActivity;
import com.surface.resourcecenter.ui.device.GongQuManagerActivity;
import com.surface.resourcecenter.ui.dispatch.DispatchTaskActivity;
import com.surface.resourcecenter.ui.home.adapter.HomePageTaskAdapter;
import com.surface.resourcecenter.ui.home.adapter.bean.DataBean;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;
import com.surface.resourcecenter.ui.sample.SampleRecvActivity;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;
import com.surface.resourcecenter.ui.usrManager.UsrManagerActivity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private Banner banner;
    private List<HomeItem> itemList = new ArrayList<>();


    View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag();
            if(tag.equals("收样")){
                SampleRecvActivity.launch(getContext());
            } else if(tag.equals("样品管理")){
                DispatchTaskActivity.launch(getContext());
            } else if(tag.equals("试验管理")){
                DoTaskActivity.launch(getContext());
            } else if(tag.equals("报告管理")){

            } else if(tag.equals("数据分析")){
                ChartManagerActivity.launch(getContext());
            } else if(tag.equals("仪器管理")){
                DeviceManagerActivity.launch(getContext());
            } else if(tag.equals("工区管理")){
                GongQuManagerActivity.launch(getContext());
            } else if(tag.equals("用户管理")){
                UsrManagerActivity.launch(getContext());
            }

        }
    };

    void initHomeData(){
        itemList.add(new HomeItem("收样",R.mipmap.icon_shouyang));
        itemList.add(new HomeItem("样品管理",R.mipmap.icon_sample_manager));
        itemList.add(new HomeItem("试验管理",R.mipmap.icon_test_manager));
        itemList.add(new HomeItem("报告管理",R.mipmap.icon_report_manager));
        itemList.add(new HomeItem("数据分析",R.mipmap.icon_data_manager));
        itemList.add(new HomeItem("仪器管理",R.mipmap.icon_money_manager));
        itemList.add(new HomeItem("工区管理",R.mipmap.icon_device_manager));
        itemList.add(new HomeItem("用户管理",R.mipmap.icon_usr_manager));
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_home_layout;
    }

    @Override
    public void init(View view) {
        banner = view.findViewById(R.id.banner);
        banner.setVisibility(View.VISIBLE);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        initHomeData();

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(layoutManager);
        HomePageTaskAdapter adapter = new HomePageTaskAdapter(itemList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
        initBanner();
    }

    private void initBanner(){
        banner.setLoopTime(5000);
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new ImageAdapter(DataBean.getTestData()))
                .setIndicator(new CircleIndicator(getContext()));
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
     */
    public class ImageAdapter extends BannerAdapter<DataBean, ImageAdapter.BannerViewHolder> {

        public ImageAdapter(List<DataBean> mDatas) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(mDatas);
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new BannerViewHolder(imageView);
        }

        @Override
        public void onBindView(BannerViewHolder holder, DataBean data, int position, int size) {
            holder.imageView.setImageResource(data.imageRes);
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public BannerViewHolder(@NonNull ImageView view) {
                super(view);
                this.imageView = view;
            }
        }
    }

}
