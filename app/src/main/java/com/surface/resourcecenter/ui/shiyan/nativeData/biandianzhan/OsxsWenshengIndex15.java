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

public class OsxsWenshengIndex15 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    private TextView mGridTitle,mGridTitle1,mGridTitle2,mGridTitle3,mGridTitle4,mGridTitle5;
    GridLayout mGridLayout, mGridLayout1, mGridLayout2, mGridLayout3, mGridLayout4, mGridLayout5;
    public OsxsWenshengIndex15(){

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

        mGridTitle.setText("箱体外--变压器顶层油温升测量");
        mGridTitle1.setText("箱体外--热态电阻测量");
        mGridTitle2.setText("箱体内--变压器顶层油温升测量");
        mGridTitle3.setText("箱体内--热态电阻测量");
        mGridTitle4.setText("高压连接线及其接线端子、低压连接线和开关设备、样品外壳的温升试验");
        mGridTitle5.setVisibility(View.GONE);

        initGridLayout();
        initGridLayout1();
        initGridLayout2();
        initGridLayout3();
        initGridLayout4();
        initGridLayout5();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int ColumnNum = 4;
        int RowNum = 4;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"--","顶层油温度","底部油温度","环境温度"};
        String[] leftheader = {"总损耗下各测点的温度（℃）","额定电流下各测点的温度（℃）电源断开时","额定电流下各测点的温度（℃）电阻测量结束时"};
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
        int ColumnNum = 11;
        int RowNum = 6;
        mGridLayout1.setColumnCount(ColumnNum);
        mGridLayout1.setRowCount(RowNum);
        String[] header = {"时间","0'30''","1'","1'30''","2'","2'30''","3'","3'30''","4'","4'30''","5'"};
        String[] header1 = {"时间","5'30''","6'","6'30''","7'","7'30''","8'","8'30''","9'","9'30''","10'"};
        String[] leftheader = {"RAB(Ω)","Rab(mΩ)","时间","RAB(Ω)","Rab(mΩ)"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                }  else if(m == 3){
                    editText.setText(header1[i]);
                }else if(i == 0){
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
        int ColumnNum = 4;
        int RowNum = 4;
        mGridLayout2.setColumnCount(ColumnNum);
        mGridLayout2.setRowCount(RowNum);
        String[] header = {"--","顶层油温度","底部油温度","环境温度"};
        String[] leftheader = {"总损耗下各测点的温度（℃）","额定电流下各测点的温度（℃）电源断开时","额定电流下各测点的温度（℃）电阻测量结束时"};
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
        int ColumnNum = 11;
        int RowNum = 6;
        mGridLayout3.setColumnCount(ColumnNum);
        mGridLayout3.setRowCount(RowNum);
        String[] header = {"时间","0'30''","1'","1'30''","2'","2'30''","3'","3'30''","4'","4'30''","5'"};
        String[] header1 = {"时间","5'30''","6'","6'30''","7'","7'30''","8'","8'30''","9'","9'30''","10'"};
        String[] leftheader = {"RAB(Ω)","Rab(mΩ)","时间","RAB(Ω)","Rab(mΩ)"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                }  else if(m == 3){
                    editText.setText(header1[i]);
                }else if(i == 0){
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


    private void initGridLayout4(){
        int ColumnNum = 11;
        int RowNum = 7;
        mGridLayout4.setColumnCount(ColumnNum);
        mGridLayout4.setRowCount(RowNum);
        String[] header = {"测试部位","允许温升（K）","A","B","C","A","B","C","A","B","C"};
        String[] leftheader = {"高压连接线接线端子","低压母线连接处","低压隔离开关上端","低压隔离开关下端","低压主断路器上端","低压主断路器下端","低压母线连接处"};
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
                mGridLayout4.addView(editText,params);
            }


        }

    }

    private void initGridLayout5(){
        int ColumnNum = 5;
        int RowNum = 4;
        mGridLayout5.setColumnCount(ColumnNum);
        mGridLayout5.setRowCount(RowNum);
        String[] header = {"测试部位","允许温升（K）"," "," "," "};
        String[] leftheader = {"低压主开关操作手柄（K）","外壳覆板（K）","电子元件周围空气温度（℃）"};
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
                mGridLayout5.addView(editText,params);
            }


        }

    }

    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
