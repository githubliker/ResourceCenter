package com.surface.resourcecenter.ui.home.fragment;

import android.view.View;
import android.widget.TextView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.home.adapter.HomePagerAdapter;
import com.surface.resourcecenter.ui.home.widget.NoScrollViewPager;
import com.surface.resourcecenter.ui.home.widget.TabView;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class EmptyFragment extends BaseFragment implements View.OnClickListener {
    private NoScrollViewPager viewPager;
    private HomePagerAdapter homePagerAdapter;
    private TabView tabView;
    private TextView mTextView;
    private String text;

    public EmptyFragment(String text){
        this.text = text;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_empty_layout;
    }

    @Override
    public void init(View view) {
        ((TextView)view.findViewById(R.id.text)).setText(text);
    }


    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
