package com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan;

import android.view.View;
import android.widget.TextView;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
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

public class DianyabiTestIndex3 extends BaseFragment implements View.OnClickListener {

    ScrollablePanel scrollablePanel;
    ScrollablePanelAdapter scrollablePanelAdapter;
    List<List<DataInfo>> datasList;
    List<String> xdateInfoList;
    List<String> ydataInfoList;
    List<String> x1dateInfoList;
    private TextView deviceName,deviceTypeId,deviceId,remark;
    private String pageType = Consts.GSBYQ;
    public DianyabiTestIndex3(){

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
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
        deviceName.setText("全自动变比测试仪");
        deviceTypeId.setText("HCBB-Ⅲ");
        deviceId.setText("SMWZJC-001");
        remark.setText("主分接电压比偏差：\n" +
                "规定电压比的±0.5%与实际阻抗电压百分数的±1/10中较低者\n" +
                "应检定单相变压器的极性和三相变压器的联结组标号");
        view.findViewById(R.id.biaohao_layout).setVisibility(View.VISIBLE);
        if(pageType.equals(Consts.GSBYQ)){
        } else if(pageType.equals(Consts.YJSBYQ)){
            remark.setText("主分接电压比偏差：\n" +
                    "规定电压比的±0.5%与实际阻抗电压百分数的±1/10中较低者\n" +
                    "应检定单相变压器的极性和三相变压器的联结组标号\n"+
                    "联结组标号检定 Dyn");
        }

    }

    private void generateTestData() {
        ydataInfoList = new ArrayList<>();

        if(pageType.equals(Consts.YJSBYQ)){
            ydataInfoList.add("HV对LV");
            ydataInfoList.add("HV对LV");
            ydataInfoList.add("HV对LV");
            ydataInfoList.add("HV对LV");
            ydataInfoList.add("HV对LV");
        } else {
            ydataInfoList.add("高压对低压");
            ydataInfoList.add("高压对低压");
            ydataInfoList.add("高压对低压");
            ydataInfoList.add("高压对低压");
            ydataInfoList.add("高压对低压");
        }

        scrollablePanelAdapter.setYDataInfoList(ydataInfoList);

        xdateInfoList = new ArrayList<>();
        xdateInfoList.add("计算变比");
        xdateInfoList.add("分接位置");
        xdateInfoList.add("AB/ab");
        xdateInfoList.add("BC/bc");
        xdateInfoList.add("CA/ca");
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
