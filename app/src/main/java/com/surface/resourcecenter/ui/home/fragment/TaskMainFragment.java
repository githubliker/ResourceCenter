package com.surface.resourcecenter.ui.home.fragment;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lxj.xpopup.XPopup;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.home.adapter.bean.PageInfo;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class TaskMainFragment extends BaseFragment implements View.OnClickListener {
    TabLayout tabLayout;
    public ViewPager viewPager;

    PageInfo[] pageInfos = new PageInfo[]{
            new PageInfo("待处理", new TodoTaskFragment()),
            new PageInfo("已完成", new FinishTaskFragment())
    };
    @Override
    protected int getLayoutId() {
        return R.layout.rc_task_fragment_layout;
    }

    @Override
    public void init(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        XPopup.setPrimaryColor(getResources().getColor(R.color.usercenterbg));
        viewPager.setAdapter(new MainAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }


    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return pageInfos[i].fragment;
        }

        @Override
        public int getCount() {
            return pageInfos.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageInfos[position].title;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(viewPager != null){
            viewPager.removeAllViews();
        }
        viewPager = null;
        pageInfos = null;
    }
    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
