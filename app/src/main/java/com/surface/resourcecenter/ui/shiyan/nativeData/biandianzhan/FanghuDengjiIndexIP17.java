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

public class FanghuDengjiIndexIP17 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    GridLayout mGridLayout;
    public FanghuDengjiIndexIP17(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_main_gridlayout;
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
        remark.setText("绝缘试验（主回路高压连接线工频）、绝缘试验（主回路低压连接线工频）、辅助和控制回路的绝缘试验");


        initGridLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int ColumnNum = 2;
        int RowNum = 5;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"检验要求","测量或观察结果"};
        String[] leftheader = {"用边缘无毛刺的2.5mm的平直钢性钢棒检查，试验用力3N±0.3N，试品外壳能完全防止直径不小于2.5mm的试具或金属线进入，\n满足IP3X防护等级要求"
                ,"1.0mm，长100mm钢丝与壳内带电部分或运动部件保持了足够的间隙，\n对接近危险部件的防护达到IPXXD的要求"
                ,"按右图的淋水试验装置在试品各被试表面进行均匀的淋水，水量10L/min，持续时间1min/m2，至少5min。试验完成后如果满足以下结果：\n" +
                "a）电器主回路和辅助回路的绝缘部位应无进水痕迹。\n" +
                "b）设备内部的任一电气元件和机构部位应无进水痕迹。\n" +
                "c）结构件和其它非绝缘部件应无明显积水。\n" +
                "即可认为符合相应的标准要求。\n" +
                "注）淋水角度±60°试验即可认为符合IPX3的要求。\n" +
                "淋水角度±180°试验即可认为符合IPX4的要求","要求满足IP33D。"};
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
                if(i == 1){
                    editText.setMinWidth(500);
                } else {
                    editText.setMinWidth(150);

                }
                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                //表示起始位置为i，占据1列
                columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                if(m == 3){
                    params.height = 300;
                } else {
                    params.height = 120;
                }
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
