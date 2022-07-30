package com.surface.resourcecenter.ui.shiyan.nativeData.byq;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class YjWenshengIndex10 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark;
    TableLayout mGuoChengLayout;
    public YjWenshengIndex10(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_yjbyq_wenshengshiyan;
    }

    @Override
    public void init(View view) {
        deviceName = view.findViewById(R.id.shebei_name);
        deviceTypeId = view.findViewById(R.id.shebei_xinghao);
        deviceId = view.findViewById(R.id.shebei_bianhao);
        remark = view.findViewById(R.id.remark);
        mGuoChengLayout = view.findViewById(R.id.guocheng_data);
        deviceName.setText("自动绝缘油耐压试验机");
        deviceTypeId.setText("KD9701");
        deviceId.setText("064");
        remark.setText("");
        initGuochengData();
    }

    private void  initGuochengData(){
        for(int i = 0;i<10;i++){
            TableRow row = new TableRow(getContext());
            for(int j = 0;j <11;j++){
                TextView textView = new TextView(getContext());
                textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1));
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.chart_item_shape);
                textView.setText(i+"/"+j);
                row.addView(textView);
            }
            mGuoChengLayout.addView(row);
        }
        mGuoChengLayout.getChildCount();
        Log.d("TAG","mGuochengLayout count"+mGuoChengLayout.getChildCount());
    }

    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
