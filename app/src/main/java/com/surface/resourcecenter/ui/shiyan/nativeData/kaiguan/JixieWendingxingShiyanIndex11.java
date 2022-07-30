package com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan;

import android.view.View;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.shiyan.nativeData.adapter.ClickablePanelAdapter;
import com.surface.resourcecenter.ui.shiyan.nativeData.adapter.DataInfo;
import com.surface.resourcecenter.ui.shiyan.nativeData.adapter.ScrollablePanelAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class JixieWendingxingShiyanIndex11 extends BaseFragment implements View.OnClickListener {

    ScrollablePanel scrollablePanel;
    ScrollablePanelAdapter scrollablePanelAdapter;
    List<List<DataInfo>> datasList;
    List<String> xdateInfoList;
    List<String> ydataInfoList;
    List<String> x1dateInfoList;
    public JixieWendingxingShiyanIndex11(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_yjbyq_zhiliudianzuxishoubi;
    }

    @Override
    public void init(View view) {
        scrollablePanel = (ScrollablePanel) view.findViewById(R.id.scrollable_panel);
        scrollablePanelAdapter = new ScrollablePanelAdapter();
        generateTestData();
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
    }

    private void generateTestData() {
        ydataInfoList = new ArrayList<>();

        ydataInfoList.add("隔离断口净距");
        ydataInfoList.add("合闸时间");
        ydataInfoList.add("合闸不同期");
        ydataInfoList.add("分闸时间");
        ydataInfoList.add("分闸不同期");
        ydataInfoList.add("最大能量");
        ydataInfoList.add("最大操作力");
        ydataInfoList.add("/");
        ydataInfoList.add("1");
        ydataInfoList.add("2");
        ydataInfoList.add("3");

        scrollablePanelAdapter.setYDataInfoList(ydataInfoList);

        xdateInfoList = new ArrayList<>();
        xdateInfoList.add("操作步骤及要求");
        xdateInfoList.add("测试结果");
        xdateInfoList.add("");
        xdateInfoList.add("");
        scrollablePanelAdapter.setXDateInfoList(xdateInfoList);

        x1dateInfoList = new ArrayList<>();
        x1dateInfoList.add("操作项目");
        x1dateInfoList.add("操作次数");
        x1dateInfoList.add("试品状态");
        x1dateInfoList.add("");
        scrollablePanelAdapter.setX1DateInfoList(x1dateInfoList);
        scrollablePanelAdapter.setDateType1Index(8);

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
