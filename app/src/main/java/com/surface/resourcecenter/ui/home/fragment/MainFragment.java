package com.surface.resourcecenter.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.home.adapter.HomePagerAdapter;
import com.surface.resourcecenter.ui.home.widget.NoScrollViewPager;
import com.surface.resourcecenter.ui.home.widget.TabView;
import com.surface.resourcecenter.ui.userInfo.MineFragment;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private NoScrollViewPager viewPager;
    private HomePagerAdapter homePagerAdapter;
    private TabView tabView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rc_fragment_main, container, false);
        TabView tabView = (TabView) view.findViewById(R.id.tabView);
        NoScrollViewPager viewPager = (NoScrollViewPager) view.findViewById(R.id.viewPager);
        this.tabView = (TabView) view.findViewById(R.id.tabView);
        this.viewPager = (NoScrollViewPager) view.findViewById(R.id.viewPager);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        tabView = view.findViewById(R.id.tabView);


        homePagerAdapter = new HomePagerAdapter(getFragmentManager());

        Fragment[] fragments = new Fragment[3];
        fragments[0] = new HomeFragment();
        fragments[1] = new TaskMainFragment();
        fragments[2] = new MineFragment();
        homePagerAdapter.setFragments(fragments);
        viewPager.setAdapter(homePagerAdapter);
        tabView.setViewPager(viewPager);
        viewPager.setCurrentItem(TabView.TAB_HOME);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
