package com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan;

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

public class ZukangFuzaiSunhaoIndex5 extends BaseFragment implements View.OnClickListener {

    ScrollablePanel scrollablePanel;
    ScrollablePanelAdapter scrollablePanelAdapter;
    List<List<DataInfo>> datasList;
    List<String> xdateInfoList;
    List<String> ydataInfoList;
    List<String> x1dateInfoList;
    private TextView deviceName,deviceTypeId,deviceId,remark;
    public ZukangFuzaiSunhaoIndex5(){

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
        deviceName.setText("变压器空负载损耗测试仪");
        deviceTypeId.setText("HCS-6000");
        deviceId.setText("SMWZJC-003");
        remark.setText("校正到分接电流[Pkt ( W) \\ Zkt (%)]\n"+
                "校正到参考温度[Pk( W) \\ Zk(%) \\ Zk(Ω/相)]");
    }

    private void generateTestData() {
        ydataInfoList = new ArrayList<>();

        ydataInfoList.add("高压对低压");
        ydataInfoList.add("高压对低压");
        ydataInfoList.add("高压对低压");

        scrollablePanelAdapter.setYDataInfoList(ydataInfoList);

        xdateInfoList = new ArrayList<>();
        xdateInfoList.add("分接位置");
        xdateInfoList.add("施加电流(A)");
        xdateInfoList.add("测量电压(V)");
        xdateInfoList.add("测量损耗(W)");
        xdateInfoList.add("Pkt ( W)");
        xdateInfoList.add("Zkt (%)");
        xdateInfoList.add("负载损耗Pk( W)");
        xdateInfoList.add("短路阻抗Zk(%)");
        xdateInfoList.add("短路阻抗Zk(Ω/相)");
        xdateInfoList.add("总损耗P总( W)");
        xdateInfoList.add("规定值Pk( W)");
        xdateInfoList.add("规定值Zk(%)");
        xdateInfoList.add("规定值P总( W)");
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
