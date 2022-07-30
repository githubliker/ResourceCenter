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

public class OsxsNaishouDianliuIndex16 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    private TextView mGridTitle,mGridTitle1,mGridTitle2,mGridTitle3,mGridTitle4,mGridTitle5;
    GridLayout mGridLayout, mGridLayout1, mGridLayout2, mGridLayout3, mGridLayout4, mGridLayout5;
    public OsxsNaishouDianliuIndex16(){

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

        mGridTitle.setText("低压主回路的短时和峰值耐受电流试验");
        mGridTitle1.setText("");
        mGridTitle1.setVisibility(View.GONE);
        mGridTitle2.setText("接地回路的短时和峰值耐受电流试验");
        mGridTitle3.setText("试验后，接地回路连续性检查");
        mGridTitle4.setVisibility(View.GONE);
        mGridTitle5.setVisibility(View.GONE);

        initGridLayout();
        initGridLayout1();
        initGridLayout2();
        initGridLayout3();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int ColumnNum = 4;
        int RowNum = 4;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"检验要求","A","B","C"};
        String[] leftheader = {"试验电流（峰值）（kA）：63  ","试验电流（有效值）（kA）：30 ","热稳定值I2t（×106  A2s）：900  "};
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

    private void initGridLayout1(){
        int ColumnNum = 2;
        int RowNum = 7;
        mGridLayout1.setColumnCount(ColumnNum);
        mGridLayout1.setRowCount(RowNum);
        String[] header = {"检验要求","测量或观察结果"};
        String[] leftheader = {"通电时间：1（s）","示波图编号","试验后，柜架结构无任何变形；","母线绝缘支持件无破裂现象"
                ,"试验后，低压主母线的机械部件和绝缘件应无明显的损伤痕迹及可察觉的变形。","试验后，低压主母线的连接部件不应松动，而且导线不应从输出端子上脱落。"};
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

    private void initGridLayout2(){
        int ColumnNum = 2;
        int RowNum = 8;
        mGridLayout2.setColumnCount(ColumnNum);
        mGridLayout2.setRowCount(RowNum);
        String[] header = {"检验要求","测量或观察结果"};
        String[] leftheader = {"试验电流（峰值）：50kA","通电时间（峰值）：0.3s","试验电流（有效值）：20kA","通电时间（有效值）：2s",
                "热稳定值I2t（×106   A2s）：800 ","示波图编号（动/热）","试验后，接地导体和到元件的接地连接部件不应松动，而且导线不应从输出端子上脱落"};
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
                mGridLayout2.addView(editText,params);
            }


        }

    }
    private void initGridLayout3(){
        int ColumnNum = 3;
        int RowNum = 4;
        mGridLayout3.setColumnCount(ColumnNum);
        mGridLayout3.setRowCount(RowNum);
        String[] header = {"测试部位","允许值（mΩ）","测量值（mΩ）"};
        String[] leftheader = {"低压室门至主接地点","高压室门至主接地点","变压器室门至主接地点"};
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
                editText.setMinWidth(100);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;
                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                //表示起始位置为i，占据1列
                columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.height = 60;
                mGridLayout3.addView(editText,params);
            }
        }

    }
    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
