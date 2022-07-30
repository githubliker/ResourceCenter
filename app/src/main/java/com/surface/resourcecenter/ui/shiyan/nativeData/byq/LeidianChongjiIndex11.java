package com.surface.resourcecenter.ui.shiyan.nativeData.byq;

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

public class LeidianChongjiIndex11 extends BaseFragment implements View.OnClickListener {

    ScrollablePanel scrollablePanel;
    ScrollablePanelAdapter scrollablePanelAdapter;
    List<List<DataInfo>> datasList;
    List<String> xdateInfoList;
    List<String> ydataInfoList;
    List<String> x1dateInfoList;
    private TextView deviceName,deviceTypeId,deviceId,remark;
    public LeidianChongjiIndex11(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_yjbyq_zhiliudianzuxishoubi;
    }

    @Override
    public void init(View view) {
        deviceName = view.findViewById(R.id.shebei_name);
        deviceTypeId = view.findViewById(R.id.shebei_xinghao);
        deviceId = view.findViewById(R.id.shebei_bianhao);
        remark = view.findViewById(R.id.remark);
        view.findViewById(R.id.huanjing_layout).setVisibility(View.GONE);
        scrollablePanel = (ScrollablePanel) view.findViewById(R.id.scrollable_panel);
        scrollablePanelAdapter = new ScrollablePanelAdapter();
        generateTestData();
        scrollablePanelAdapter.setDateType1Index(9);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
        deviceName.setText("冲击电压发生器");
        deviceTypeId.setText("BST-400");
        deviceId.setText("SMWZJC-005");
        remark.setText("除截断时刻后的波形部分，在降低电压下所记录的电压和电流瞬变波形图与在全电压下所记录的相应的瞬变波形图无明显差异");
    }

    private void generateTestData() {
        ydataInfoList = new ArrayList<>();

        for(int i = 0;i<16;i++){
            ydataInfoList.add("");
        }

        scrollablePanelAdapter.setYDataInfoList(ydataInfoList);

        xdateInfoList = new ArrayList<>();
        xdateInfoList.add("全波施压端");
        xdateInfoList.add("试验电压（kV）");
        xdateInfoList.add("T1 (μs)");
        xdateInfoList.add("T2 (μs)");
        xdateInfoList.add("-");
        xdateInfoList.add("");
        scrollablePanelAdapter.setXDateInfoList(xdateInfoList);

        x1dateInfoList = new ArrayList<>();
        x1dateInfoList.add("截波施压端");
        x1dateInfoList.add("试验电压（kV）");
        x1dateInfoList.add("Tc(μs)");
        x1dateInfoList.add("Kc");
        x1dateInfoList.add("K0%");
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
