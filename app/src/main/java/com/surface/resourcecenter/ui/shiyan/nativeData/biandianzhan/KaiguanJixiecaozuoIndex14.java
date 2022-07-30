package com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan;

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

public class KaiguanJixiecaozuoIndex14 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    GridLayout mGridLayout;
    public KaiguanJixiecaozuoIndex14(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_osxsbdz_gridlayout_jixiecaozuo;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init(View view) {
        mGridLayout = view.findViewById(R.id.grid_layout);
        deviceName = view.findViewById(R.id.shebei_name);
        deviceTypeId = view.findViewById(R.id.shebei_xinghao);
        deviceId = view.findViewById(R.id.shebei_bianhao);
        remark = view.findViewById(R.id.remark);
        mPicTitle = view.findViewById(R.id.pic_title);
        view.findViewById(R.id.huanjing_layout).setVisibility(View.GONE);
        deviceName.setText("");
        deviceTypeId.setText("");
        deviceId.setText("/");


        initGridLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int ColumnNum = 5;
        int RowNum = 7;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"高压、低压开关设备","数量","标准要求(次/台)","操作方式","结果"};
        String[] leftheader = {"高压负荷开关","高压接地开关","低压主断路器","低压隔离开关","低压出线开关","电容柜主开关"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                }
                editText.setPadding(20,10,20,10);

                editText.setMinWidth(150);
                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                //表示起始位置为i，占据1列
                columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.height = 60;
                mGridLayout.addView(editText,params);
            }


        }

    }

    private void initGridHeader(){
        TextView editText = new TextView(getContext());
        editText.setBackgroundResource(R.drawable.chart_item_shape);
        editText.setGravity(Gravity.CENTER);
        editText.setText("操作方式及要求");
        //表示起始位置为0，占据2行
        GridLayout.Spec rowSpec=GridLayout.spec(0, 1, GridLayout.FILL);
        //表示起始位置为1，占据1列
        GridLayout.Spec columnSpec=GridLayout.spec(0, 1, GridLayout.FILL);
        GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
        params.height = 45;
        params.width = 200;
        mGridLayout.addView(editText,params);


        mGridLayout.addView(editText,params);
    }
    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
