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

public class JPFanghuDengjiIndex4 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    private TextView mGridTitle,mGridTitle1,mGridTitle2,mGridTitle3,mGridTitle4,mGridTitle5;
    GridLayout mGridLayout, mGridLayout1, mGridLayout2, mGridLayout3, mGridLayout4, mGridLayout5;
    public JPFanghuDengjiIndex4(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_osxsbdz_gridlayout_jueyuan;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init(View view) {
        mGridLayout = view.findViewById(R.id.grid_layout);
        mGridLayout1 = view.findViewById(R.id.grid_layout1);
        mGridLayout2 = view.findViewById(R.id.grid_layout2);
        mGridLayout3 = view.findViewById(R.id.grid_layout3);
        mGridLayout4 = view.findViewById(R.id.grid_layout4);
        mGridLayout5= view.findViewById(R.id.grid_layout5);
        mGridTitle = view.findViewById(R.id.grid_title);
        mGridTitle1 = view.findViewById(R.id.grid_title1);
        mGridTitle2 = view.findViewById(R.id.grid_title2);
        mGridTitle3 = view.findViewById(R.id.grid_title3);
        mGridTitle4 = view.findViewById(R.id.grid_title4);
        mGridTitle5 = view.findViewById(R.id.grid_title5);
        deviceName = view.findViewById(R.id.shebei_name);
        deviceTypeId = view.findViewById(R.id.shebei_xinghao);
        deviceId = view.findViewById(R.id.shebei_bianhao);
        remark = view.findViewById(R.id.remark);
        mPicTitle = view.findViewById(R.id.pic_title);
        view.findViewById(R.id.huanjing_layout).setVisibility(View.GONE);
        deviceName.setText("");
        deviceTypeId.setText("");
        deviceId.setText("/");
        remark.setText("");

        mGridTitle.setVisibility(View.GONE);
        mGridTitle1.setText("试验后介电性能验证");
        mGridTitle2.setVisibility(View.GONE);
        mGridTitle3.setVisibility(View.GONE);
        mGridTitle4.setVisibility(View.GONE);
        mGridTitle5.setVisibility(View.GONE);

        initGridLayout();
        initGridLayout1();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        String[] header = {"检测要求 ","检测结果"};
        String[] leftheader = {"试具不应进入防护空间与带电部分保持足够的间隙。","进水应对试品无有害影响。","水不计入带电部件、绕组。"
                ,"水不积聚在可能导致沿爬电距离引起漏电起痕的绝缘部件上。","水不积聚在电缆头附近或进入电缆。","有泄水孔，进水不积聚，且能排出。"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
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
                    editText.setMinWidth(450);
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
                params.height = 60;
                mGridLayout.addView(editText,params);
            }


        }

    }

    private void initGridLayout1(){
        String[] header = {"检测部位","施加电压（kV）","持续时间（s）","检测结果"};
        String[] leftheader = {"配电箱主回路A、B、C、a、b、c相与地之间","配电箱主回路A相与B相之间","配电箱主回路B相与C相之间","配电箱主回路A相与C相之间","配电箱主回路ABC相与abc相之间","辅助回路与地之间"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mGridLayout1.setColumnCount(ColumnNum);
        mGridLayout1.setRowCount(RowNum);
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                } else if(i == 1){
                    if(m == 6){
                        editText.setText("2");
                    } else {
                        editText.setText("2.5");
                    }
                } else if(i == 2){
                    editText.setText("5");
                }
                editText.setPadding(20,10,20,10);
                editText.setMinWidth(100);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;
                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                //表示起始位置为i，占据1列
                columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.height = 60;
                mGridLayout1.addView(editText,params);
            }
        }

    }

    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
