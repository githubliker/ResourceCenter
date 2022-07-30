package com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan;

import android.view.View;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.shiyan.nativeData.adapter.ClickablePanelAdapter;
import com.surface.resourcecenter.ui.shiyan.nativeData.adapter.DataInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class JixieCaozuoiIndex10 extends BaseFragment implements View.OnClickListener {

    ScrollablePanel scrollablePanel;
    ClickablePanelAdapter scrollablePanelAdapter;
    List<List<DataInfo>> datasList;
    List<String> xdateInfoList;
    List<String> ydataInfoList;
    public JixieCaozuoiIndex10(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_yjbyq_zhiliudianzuxishoubi;
    }

    @Override
    public void init(View view) {
        scrollablePanel = (ScrollablePanel) view.findViewById(R.id.scrollable_panel);
        scrollablePanelAdapter = new ClickablePanelAdapter();
        generateTestData();
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
    }

    private void generateTestData() {
        ydataInfoList = new ArrayList<>();

        ydataInfoList.add("1");
        ydataInfoList.add("2");
        ydataInfoList.add("3");
        ydataInfoList.add("4");
        ydataInfoList.add("5");
        ydataInfoList.add("6");

        scrollablePanelAdapter.setYDataInfoList(ydataInfoList);

        xdateInfoList = new ArrayList<>();
        xdateInfoList.add("操作项目");
        xdateInfoList.add("操作次数");
        xdateInfoList.add("试品状态");
        xdateInfoList.add("");
        scrollablePanelAdapter.setXDateInfoList(xdateInfoList);

        datasList = new ArrayList<>();
        for (int i = 0; i < ydataInfoList.size(); i++) {
            List<DataInfo> orderInfoList = new ArrayList<>();
            for (int j = 0; j < xdateInfoList.size(); j++) {
                DataInfo orderInfo = new DataInfo();
                orderInfo.setDate("--");
                orderInfo.setStatus("empty");
                orderInfoList.add(orderInfo);
            }
            datasList.add(orderInfoList);
        }
        scrollablePanelAdapter.setDatasList(datasList);
    }
    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
