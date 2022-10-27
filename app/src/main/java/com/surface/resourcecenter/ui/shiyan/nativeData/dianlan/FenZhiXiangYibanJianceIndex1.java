package com.surface.resourcecenter.ui.shiyan.nativeData.dianlan;

import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.shiyan.nativeData.GridLayoutBean;

import java.util.ArrayList;
import java.util.List;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class FenZhiXiangYibanJianceIndex1 extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private String[] gridHeader = {"1\t布线、操作性能和功能","2\t电气间隙和爬电距离","3\t标志检验","6\t柜体材质、厚度及尺寸检测","7\t机械操作试验","提升试验","10\t机械碰撞试验"};
    public FenZhiXiangYibanJianceIndex1(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_gridlayout_normal;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init(View view) {
        mFatherLayout = view.findViewById(R.id.grid_father);

        initView();
        initGridLayout();
        initGridLayout1();
        initGridLayout2();
        initGridLayout3();
        initGridLayout4();
        initGridLayout5();
        initGridLayout6();
    }

    private void initView(){
        mViewList.clear();
        for(int i = 0;i< gridHeader.length;i++){
            GridLayout gridLayout = new GridLayout(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20,20,10,20);
            gridLayout.setPadding(0,0,0,20);
            gridLayout.setLayoutParams(params);

            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params1.setMargins(20,0,0,0);
            textView.setTextColor(Color.parseColor("#111000"));
            textView.setTextSize(20);
            textView.setLayoutParams(params1);
            textView.setText(gridHeader[gridHeader.length - 1 - i]);

            GridLayoutBean bean = new GridLayoutBean();
            bean.setGridLayout(gridLayout);
            bean.setTextView(textView);
            mViewList.add(0,bean);
            mFatherLayout.addView(gridLayout,0);
            mFatherLayout.addView(textView,0);

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        String[] header = {"检测项目及检验要求","测量或观察结果"};
        String[] leftheader = {"对机械操作元件、联锁、锁扣等部件的有效性进行检查","检查导线和电缆的布置是否正确","检查电器安装是否正确\n" +
                "由操作人员观察的指示仪表应安装在成套设备基础面上方0.2m～2.2m之间。\n" +
                "操作器件，如手柄、按钮或类似器件，应安装在易于操作的高度上，\n其中心线一般应在成套设备基础面上0.2m～2m之间。\n不经常操作的器件，可以装在高度达2.2m处。",
                "端子，不包括保护导体端子，应位于成套设备的基础面上方至少0.2m，\n并且端子的位置应使电缆易于与其连接","相导体截面积测量值","中性导体端子截面积：≥  相导体截面积"
                ,"保护导体端子截面积：≥  相导体截面积"
                ,"中性导体端子的数量：≥","保护导体端子的数量：≥"
                ,"中性导体端子标志：蓝色，黑色或“N”","保护导体端子标志：黄绿双色"
                ,"检查接线图和技术数据是否相符"
                ,"通电操作试验，按设备的电气原理图要求进行模拟动作试验，\n试验结果应符合设计要求"};

        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(0).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(0).getGridLayout().setRowCount(RowNum);
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
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == ColumnNum -1){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(0).getGridLayout().addView(editText,params);
            }
        }

    }

    private void initGridLayout1(){
        String[] header = {"项目","检测项目","技术要求值(mm)","检测结果"};
        String[] leftHeader = {"电气间隙","爬电距离"};
        String[] leftHeader1 = {"相与相之间","不同电压的电路导体之间","带电部件与裸露导电部件之间","相与相之间","不同电压的电路导体之间","带电部件与裸露导电部件之间"};
        String[] leftHeader2 = {"≥8","/","≥8","≥10","/","≥10"};
        int ColumnNum = header.length;
        int RowNum = leftHeader1.length+1;
        mViewList.get(1).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(1).getGridLayout().setRowCount(RowNum);
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                } else if(i == 1){
                    editText.setText(leftHeader1[m-1]);
                } else if(i == 2){
                    editText.setText(leftHeader2[m-1]);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;
                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i == 0 && m !=0){
                    if(m%3 == 1){
                        editText.setText(leftHeader[(m-1)%3]);
                        //表示起始位置为m，占据1行
                        rowSpec=GridLayout.spec(m, 3, GridLayout.FILL);
                    } else {
                        continue;
                    }
                }
                if(i  == ColumnNum -3){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
                }

                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);

                mViewList.get(1).getGridLayout().addView(editText,params);
            }
        }

    }
    private void initGridLayout2(){
        String[] header = {"检测要求","检测结果"};
        String[] leftheader = {"试验时先手持一块在水中浸泡过的布，摩擦标志15s，\n" +
                "再用在石油溶剂油中浸泡过的布摩擦标志15s。试验后，标志仍容易辨认"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(2).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(2).getGridLayout().setRowCount(RowNum);
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
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == ColumnNum -1){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(2).getGridLayout().addView(editText,params);
            }


        }


    }
    private void initGridLayout3(){
        String[] header = {"检验项目","检测要求","测量结果"};
        String[] leftheader = {"柜体材质",
                "柜体厚度","柜体尺寸：高×宽×深"};
        String[] leftheader1 = {"柜体应采用覆铝锌钢板、镀锌板弯折后栓接而成，\n或采用优质防锈处理的冷轧钢板制成，\n或采用304不锈钢制成，或采用SMC材料制成","实测厚度应≥1.08","提供实测值"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(3).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(3).getGridLayout().setRowCount(RowNum);
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
                    editText.setText(leftheader1[m-1]);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == ColumnNum -1){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(3).getGridLayout().addView(editText,params);
            }
        }
    }
    private void initGridLayout4(){
        String[] header = {"检测项目及检测要求","观察结果"};
        String[] leftheader = {"循环操作200次，元器件的工作状态未受损伤，\n且所要求的操作力与试验前一样",
                "循环操作200次，联锁机构的工作状态未受损伤，\n且所要求的操作力与试验前一样"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(4).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(4).getGridLayout().setRowCount(RowNum);
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
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == ColumnNum -1){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(4).getGridLayout().addView(editText,params);
            }
        }
    }
    private void initGridLayout5(){
        String[] header = {"最大运输质量（Kg) "," ","1.25倍最大运输质量（Kg)"," "};
        String[] leftheader = {"提升1","提升高度（m)","水平移动距离（m)","悬吊时间（min)"};
        String[] leftheader1 = {"提升2","提升高度（m)","水平移动距离（m)","水平移动时间（S)"};
        String[] leftheader2 = {"第一次","第二次","第三次"};
        int ColumnNum = header.length;
        int RowNum = leftheader2.length*2+3;
        mViewList.get(5).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(5).getGridLayout().setRowCount(RowNum);
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                } else if(m == 1){
                    editText.setText(leftheader[i]);
                } else if(m == 5){
                    editText.setText(leftheader1[i]);
                } else if(i == 0){
                    if(m < 5){
                        editText.setText(leftheader2[(m -2) %3]);
                    } else {
                        editText.setText(leftheader2[(m -6) %3]);
                    }

                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == ColumnNum -1){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(5).getGridLayout().addView(editText,params);
            }


        }

    }
    private void initGridLayout6(){
        String[] header = {"检测项目及检测要求","观察结果"};
        String[] leftheader = {"壳体应达到外部机械撞击防护等级（IK）","施加撞击能量（J）",
                "撞击元件等效质量（kg）","跌落高度（mm）","对最大尺寸超过1m的正常使用的每个外露面冲击5次"
        ,"试验后：壳体IP代码和介电强度不变：可移式覆板可以移开和装上，门可以打开和关闭"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(6).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(6).getGridLayout().setRowCount(RowNum);
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
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == ColumnNum -1){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(6).getGridLayout().addView(editText,params);
            }


        }

    }





    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
