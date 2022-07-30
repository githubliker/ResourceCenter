package com.surface.resourcecenter.ui.dispatch.fragment;

import android.view.View;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;

import java.util.ArrayList;
import java.util.List;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class TestStandardFragment extends BaseFragment implements View.OnClickListener {

    private List<HomeItem> itemList = new ArrayList<>();

    public TestStandardFragment(){
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_standard_layout;
    }

    @Override
    public void init(View view) {

    }

    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
