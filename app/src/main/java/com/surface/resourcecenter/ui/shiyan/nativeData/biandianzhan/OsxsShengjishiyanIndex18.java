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

public class OsxsShengjishiyanIndex18 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    private TextView mGridTitle,mGridTitle1,mGridTitle2,mGridTitle3,mGridTitle4,mGridTitle5;
    GridLayout mGridLayout, mGridLayout1, mGridLayout2, mGridLayout3, mGridLayout4, mGridLayout5;
    public OsxsShengjishiyanIndex18(){

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
        mGridTitle1.setVisibility(View.GONE);
        mGridTitle2.setVisibility(View.GONE);
        mGridTitle3.setVisibility(View.GONE);
        mGridTitle4.setVisibility(View.GONE);
        mGridTitle5.setVisibility(View.GONE);

        initGridLayout();
        initGridLayout4();
        initGridLayout5();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int ColumnNum = 10;
        int RowNum = 4;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"测点","1","2","3","4","5","6","7","8","平均"};
        String[] leftheader = {"试验前背景","测量声压级","试验后背景"};
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

    private void initGridLayout4(){
        int ColumnNum = 8;
        int RowNum = 2;
        mGridLayout1.setColumnCount(ColumnNum);
        mGridLayout1.setRowCount(RowNum);
        String[] header = {"平均\n" +
                "吸声系数\n" +
                "α","试验室\n" +
                "表面积SV(m2)","试验室\n" +
                "吸声面积A(m2)","测量表面\n" +
                "面积\n" +
                "S(m2)","环境\n" +
                "修正值\n" +
                "K(dB)","声压级校正值\n" +
                "[dB(A)]","声功率级\n" +
                "LWA，Sr\n" +
                "[dB(A)]","声功率级\n" +
                "规定值\n" +
                "[dB(A)]"};

        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
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
                params.height = 120;
                mGridLayout1.addView(editText,params);
            }
        }

    }

    private void initGridLayout5(){
        int ColumnNum = 6;
        int RowNum = 2;
        mGridLayout2.setColumnCount(ColumnNum);
        mGridLayout2.setRowCount(RowNum);
        String[] header = {"低压励磁电压","分接位置","轮廓线周长","油箱高度","测点高度","测点间隔"};

        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
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

    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
