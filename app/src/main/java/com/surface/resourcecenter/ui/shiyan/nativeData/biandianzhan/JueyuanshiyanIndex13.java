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

public class JueyuanshiyanIndex13 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    GridLayout mGridLayout, mGridLayout1, mGridLayout2, mGridLayout3, mGridLayout4, mGridLayout5;
    public JueyuanshiyanIndex13(){

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
        initGridLayout1();
        initGridLayout2();
        initGridLayout3();
        initGridLayout4();
        initGridLayout5();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int ColumnNum = 5;
        int RowNum = 7;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"项目","施压部位","峰值电压（kV）","次数","结果"};
        String[] leftheader = {"高压连接线","高压连接线","高压连接线","低压连接线","低压连接线","低压连接线"};
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
                if(m==1 &&i == 0 ||m==4 &&i == 0 ){
                    //表示起始位置为m，占据1行
                    rowSpec=GridLayout.spec(m, 3, GridLayout.FILL);
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                } else if(m == 2&& i == 0 || m == 3&& i == 0 || m == 5&& i == 0|| m == 6&& i == 0){
                    continue;
                } else {
                    //表示起始位置为m，占据1行
                    rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.height = 60;
                mGridLayout.addView(editText,params);
            }


        }

    }

    private void initGridLayout1(){
        int ColumnNum = 16;
        int RowNum = 5;
        mGridLayout1.setColumnCount(ColumnNum);
        mGridLayout1.setRowCount(RowNum);
        String[] leftheader = {"正极性","试验结果","负极性","试验结果"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    if( i == 0){
                        editText.setText("次数");
                    } else {
                        editText.setText((i)+"");
                    }

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
        int ColumnNum = 16;
        int RowNum = 5;
        mGridLayout2.setColumnCount(ColumnNum);
        mGridLayout2.setRowCount(RowNum);
        String[] leftheader = {"正极性","试验结果","负极性","试验结果"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    if( i == 0){
                        editText.setText("次数");
                    } else {
                        editText.setText((i)+"");
                    }

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
                mGridLayout2.addView(editText,params);
            }
        }

    }
    private void initGridLayout3(){
        int ColumnNum = 16;
        int RowNum = 5;
        mGridLayout3.setColumnCount(ColumnNum);
        mGridLayout3.setRowCount(RowNum);
        String[] leftheader = {"正极性","试验结果","负极性","试验结果"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    if( i == 0){
                        editText.setText("次数");
                    } else {
                        editText.setText((i)+"");
                    }

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

    private void initGridLayout4(){
        int ColumnNum = 10;
        int RowNum = 6;
        mGridLayout4.setColumnCount(ColumnNum);
        mGridLayout4.setRowCount(RowNum);
        String[] header = {"施压端","A相—BC相、地","A相—BC相、地","A相—BC相、地","B相—AC相、地","B相—AC相、地","B相—AC相、地","C相—AB相、地","C相—AB相、地","C相—AB相、地"};
        String[] leftheader = {"次数","正极性","试验结果","负极性","试验结果"};
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
                if( m == 0 && i == 1 || m == 0 && i == 4 || m == 0 && i==7){
                    //表示起始位置为m，占据1行
                    rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 3, GridLayout.FILL);
                } else if(m == 0&&( i == 2 || i == 3 || i ==5 || i ==6 || i == 8 || i == 9) ){
                    continue;
                } else {
                    //表示起始位置为m，占据1行
                    rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.height = 60;
                mGridLayout4.addView(editText,params);
            }


        }

    }

    private void initGridLayout5(){
        int ColumnNum = 4;
        int RowNum = 4;
        mGridLayout5.setColumnCount(ColumnNum);
        mGridLayout5.setRowCount(RowNum);
        String[] header = {"验证部位","允许值（mm）","测量部位","测量值（mm）"};
        String[] leftheader = {"高压侧相对地（有机绝缘/瓷绝缘）","低压侧相间","低压侧相对地"};
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
