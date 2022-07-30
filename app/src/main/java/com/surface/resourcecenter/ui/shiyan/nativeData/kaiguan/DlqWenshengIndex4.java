package com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan;

import android.view.View;
import android.widget.TextView;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
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

public class DlqWenshengIndex4 extends BaseFragment implements View.OnClickListener {

    ScrollablePanel scrollablePanel;
    ScrollablePanelAdapter scrollablePanelAdapter;
    List<List<DataInfo>> datasList;
    List<String> xdateInfoList;
    List<String> ydataInfoList;
    List<String> x1dateInfoList;
    private TextView deviceName,deviceTypeId,deviceId,remark;
    public DlqWenshengIndex4(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_duanluqi_wensheng;
    }

    @Override
    public void init(View view) {
        deviceName = view.findViewById(R.id.shebei_name);
        deviceTypeId = view.findViewById(R.id.shebei_xinghao);
        deviceId = view.findViewById(R.id.shebei_bianhao);
        remark = view.findViewById(R.id.remark);
        scrollablePanel = (ScrollablePanel) view.findViewById(R.id.scrollable_panel);
        scrollablePanelAdapter = new ScrollablePanelAdapter();
        scrollablePanelAdapter.setDateType1Index(6);
        generateTestData();
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
        deviceName.setText("");
        deviceTypeId.setText("");
        deviceId.setText("");
        remark.setText("");
    }

    private void generateTestData() {
        ydataInfoList = new ArrayList<>();

        ydataInfoList.add("1");
        ydataInfoList.add("2");
        ydataInfoList.add("3");
        ydataInfoList.add("4");
        ydataInfoList.add("5");

        scrollablePanelAdapter.setYDataInfoList(ydataInfoList);

        xdateInfoList = new ArrayList<>();
        xdateInfoList.add("测试部位");
        xdateInfoList.add("允许温升值");
        xdateInfoList.add("A");
        xdateInfoList.add("B");
        xdateInfoList.add("C");
        xdateInfoList.add("");
        scrollablePanelAdapter.setXDateInfoList(xdateInfoList);

        x1dateInfoList = new ArrayList<>();
        x1dateInfoList.add("");
        x1dateInfoList.add("■Rab□Rao");
        x1dateInfoList.add("■Rbc□Rbo");
        x1dateInfoList.add("■Rca□Rco");
        x1dateInfoList.add("");
        x1dateInfoList.add("");
        x1dateInfoList.add("");
        scrollablePanelAdapter.setX1DateInfoList(x1dateInfoList);

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
