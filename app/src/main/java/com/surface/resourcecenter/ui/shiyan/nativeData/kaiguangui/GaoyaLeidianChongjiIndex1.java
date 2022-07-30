package com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class GaoyaLeidianChongjiIndex1 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark;
    GridLayout mGridLayout;
    public GaoyaLeidianChongjiIndex1(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_duanluqi_leidianchongji;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init(View view) {
        mGridLayout = view.findViewById(R.id.grid_layout);
//        deviceName = view.findViewById(R.id.shebei_name);
//        deviceTypeId = view.findViewById(R.id.shebei_xinghao);
//        deviceId = view.findViewById(R.id.shebei_bianhao);
//        remark = view.findViewById(R.id.remark);
//        view.findViewById(R.id.huanjing_layout).setVisibility(View.GONE);
//        deviceName.setText("");
//        deviceTypeId.setText("");
//        deviceId.setText("/");

        initGridLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        for(int m = 2;m<20;m++){
            for(int i = 0;i<10;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                editText.setText("test"+i);
                //表示起始位置为0，占据2行
                GridLayout.Spec rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                //表示起始位置为1，占据1列
                GridLayout.Spec columnSpec=GridLayout.spec(i+3, 1, GridLayout.FILL);
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.height = 45;
                mGridLayout.addView(editText,params);
            }
        }

    }
    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
