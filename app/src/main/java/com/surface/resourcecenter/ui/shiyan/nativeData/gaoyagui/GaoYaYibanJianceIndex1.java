package com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui;

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

public class GaoYaYibanJianceIndex1 extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private String[] gridHeader = {"接线形式、相序、空气净距检查","电气联锁试验","柜体尺寸、厚度、材质检测","机械操作","隔离开关触头镀银层厚度检测","防护等级检验","密封试验（适用于气体绝缘环网柜）"};
    public GaoYaYibanJianceIndex1(){

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
        String[] header = {"项目","检测要求","测量或观察结果"};
        String[] leftheader = {"接线形式","相序","安全净距"};
        String[] leftheader1 = {"水平出线、垂直出线或品形出线","面对柜体前面板：\n" +
                "①左中右对应相序为ABC\n" +
                "②上中下对应相序为ABC\n" +
                "③远中近对应相序为ABC\n" +
                "④其他",
                "12kV：检查以空气为绝缘介质的开关柜，母线室、电缆室内的相间\n和相对地最小空气间隙：\n" +
                "相间和相对地≥125mm，带电体至门≥155mm;"};
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
                mViewList.get(0).getGridLayout().addView(editText,params);
            }
        }

    }

    private void initGridLayout1(){
        String[] header = {"检测要求","观察结果"};
        String[] leftHeader = {"断路器处于合闸位置时，断路器小车无法推进或拉出。"
                ,"断路器小车未到工作或试验位置时，\n断路器无法进行合闸操作。"
                ,"断路器手车处于试验位置时，二次插头才可以拔出或插上。"
                ,"当接地开关处在合闸位置时，\n断路器小车无法从试验位置进入工作位置."
                ,"断路器手车处于工作位置时，无法操作接地开关。"
                ,"只有当接地开关处于闭合状态时，\n才能打开电缆室门。"
                ,"电缆室门打开时，无法操作接地开关。"
                ,"只有当隔室的元件不带电并接地时，\n隔室的门或盖板才能打开。"
                ,"负荷开关合闸，隔离开关不能操作，\n接地开关不能合闸"
                ,"负荷开关分闸，隔离开关分闸，\n接地开关可以合闸"
                ,"接地开关处于合闸位置，隔离开关不能操作，\n负荷开关不能合闸"
                ,"负荷开关合闸，柜门不允许打开"
                ,"负荷开关合闸，接地开关不能合闸"
                ,"接地开关处于合闸位置，负荷开关不能合闸"
                ,"负荷开关合闸，柜门不允许打开"
                ,"模拟熔断器动作后未更换熔断器前，负荷开关不允许合闸，\n也不允许保持在合闸位置"};
        int ColumnNum = header.length;
        int RowNum = leftHeader.length+1;
        mViewList.get(1).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(1).getGridLayout().setRowCount(RowNum);
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                } else if(i == 0){
                    editText.setText(leftHeader[m-1]);
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

                mViewList.get(1).getGridLayout().addView(editText,params);
            }
        }

    }

    private void initGridLayout2(){
        String[] header = {"检测项目","检测要求","测量或观察结果"};
        String[] leftheader = {"柜体尺寸（高×宽×深）","厚度","柜体材质"};
        String[] leftheader1 = {"提供实测值","覆铝锌板≥1.84","柜体应采用敷铝锌钢板、\n镀锌板弯折后栓接而成，\n或采用优质防锈处理的冷轧钢板制成，\n或采用不锈钢制成"};
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
                }else if(i == 1){
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
                mViewList.get(2).getGridLayout().addView(editText,params);
            }


        }

    }
    private void initGridLayout3(){
        String[] header = {"检验要求","测量或观察结果"};
        String[] leftheader = {"合闸电压低于额定30%，操作5次可靠不动作"
                ,"合闸电压85%~110%范围内操作5次可靠动作"
                ,"分闸电压低于额定30%，操作5次可靠不动作"
                ,"分闸电压65%~110%范围内操作5次可靠动作"
                ,"额定操作电压下操作5次，可靠动作"
                ,"人力操作5次，可靠动作"
                ,"额定操作电压“分-0.3-合分”，操作5次可靠动作"
                ,"储能电机85%和110%操作电压，操作5次储能可靠动作"};
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
                }
                editText.setPadding(20,10,20,10);

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
        String[] header = {"测量部位","检测要求（um）","测量或观察结果（um）"};
        String[] leftheader = {"A上触头","A下触头","B上触头","B下触头","C上触头","C下触头"};
        String[] leftheader1 = {"≥8"};
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
                if(i == 1 && m != 0){
                    if(m == 1){
                        rowSpec=GridLayout.spec(m, 6, GridLayout.FILL);
                        editText.setText(leftheader1[i-1]);
                    } else {
                        continue;
                    }
                }
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
        String[] header = {"检测部位","技术标准","检测方法","检测结果"};
        String[] leftheader = {"柜体外壳","隔板","活门","盖板"};
        String[] leftheader1 = {"柜体外壳防护等级\n达到IP4X及以上，\n隔室之间\n达到IP2X及以上。"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(5).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(5).getGridLayout().setRowCount(RowNum);
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
                if(i == 1 && m != 0){
                    if(m == 1){
                        rowSpec=GridLayout.spec(m, 4, GridLayout.FILL);
                        editText.setText(leftheader1[i-1]);
                    } else {
                        continue;
                    }
                }
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
        String[] header = {"检测项目","观察结果"};
        String[] leftheader = {"充入气体性质",
                "试验前压力","试验后压力","密封罩的体积"};
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
