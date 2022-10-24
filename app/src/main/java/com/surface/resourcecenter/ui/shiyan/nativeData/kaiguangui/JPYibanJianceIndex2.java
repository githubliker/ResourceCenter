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

public class JPYibanJianceIndex2 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    private TextView mGridTitle,mGridTitle1,mGridTitle2,mGridTitle3,mGridTitle4,mGridTitle5;
    GridLayout mGridLayout, mGridLayout1, mGridLayout2, mGridLayout3, mGridLayout4, mGridLayout5;
    public JPYibanJianceIndex2(){

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

        mGridTitle.setText("提升试验");
        mGridTitle1.setText("机械碰撞试验");
        mGridTitle2.setText("成套设备的防护等级验证");
        mGridTitle3.setText("");
        mGridTitle4.setText("");


        initGridLayout();
        initGridLayout1();
        initGridLayout2();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        String[] header = {"最大运输质量（Kg) "," ","1.25倍最大运输质量（Kg)"," "};
        String[] leftheader = {"提升1","提升高度（m)","水平移动距离（m)","悬吊时间（min)"};
        String[] leftheader1 = {"提升2","提升高度（m)","水平移动距离（m)","水平移动时间（S)"};
        String[] leftheader2 = {"第一次","第二次","第三次"};
        int ColumnNum = header.length;
        int RowNum = leftheader2.length*2+3;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
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
                mGridLayout.addView(editText,params);
            }


        }

    }

    private void initGridLayout1(){
        String[] header = {"检测项目及检测要求","观察结果"};
        String[] leftheader = {"壳体应达到外部机械撞击防护等级（IK）","施加撞击能量（J）","撞击元件等效质量（kg）","跌落高度（mm）",
                "对最大尺寸超过1m的正常使用的每个外露面冲击5次",
                "试验后：壳体IP代码和介电强度不变：可移式覆板可以移开和装上，门可以打开和关闭"};
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
                mGridLayout1.addView(editText,params);
            }
        }

    }

    private void initGridLayout2(){
        String[] header = {"检测项目及检验要求","观察结果"};
        String[] leftheader = {"用直径为 1.0 mm的试验棒，试验用力为（1.0±0.1）N进行试验，试具不得进入壳内。","防止溅水，向外壳各方面溅水应无有害影响。"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mGridLayout2.setColumnCount(ColumnNum);
        mGridLayout2.setRowCount(RowNum);
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
                mGridLayout2.addView(editText,params);
            }


        }

    }

    private void initGridLayout3(){
        String[] header = {"检测要求","检测结果"};
        String[] leftheader = {"元器件的工作状态未受损伤，且所要求的操作力与试验前一样。","联锁机构的工作状态未受损伤，且所要求的操作力与试验前一样。","规定的防护等级状态未受损伤，且所要求的操作力与试验前一样。"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mGridLayout3.setColumnCount(ColumnNum);
        mGridLayout3.setRowCount(RowNum);
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
