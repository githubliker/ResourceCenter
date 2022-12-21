package com.surface.resourcecenter.ui.shiyan.nativeData.diyagui;

import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;
import com.surface.resourcecenter.ui.shiyan.nativeData.GridLayoutBean;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoyaShiyanItem;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class DiYaYibanJianceIndex1 extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private String[] gridHeader = {"1\t布线、操作性能和功能","2\t提升试验","3\t机械碰撞试验","4\t电气间隙和爬电距离","5\t标志试验","9\t机械操作试验","10\t柜体材质、厚度及尺寸检测"};
    public DiYaYibanJianceIndex1(){

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
            params.setMargins(10,20,10,0);
            gridLayout.setLayoutParams(params);

            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params1.setMargins(20,0,0,0);
            textView.setTextColor(Color.parseColor("#111000"));
            textView.setTextSize(20);
            textView.setLayoutParams(params1);
            textView.setText(gridHeader[gridHeader.length - 1 - i]);


            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params2.setMargins(10,0,0,0);
            params2.weight = 1;
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText("合格");
            checkBox.setChecked(true);
            checkBox.setLayoutParams(params2);
            Button save = new Button(getContext());
            save.setText("保存");
            save.setId(10000 + gridHeader.length - 1- i);
            save.setOnClickListener(this);
            Button update = new Button(getContext());
            update.setText("刷新");
            update.setId(10010 + gridHeader.length - 1- i);
            update.setOnClickListener(this);
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(checkBox);
            layout.addView(update);
            layout.addView(save);
            layout.setPadding(0,0,0,20);

            GridLayoutBean bean = new GridLayoutBean();
            bean.setGridLayout(gridLayout);
            bean.setTextView(textView);
            bean.setSave(save);
            bean.setUpdate(update);
            bean.setResult(checkBox);
            mViewList.add(0,bean);
            mFatherLayout.addView(layout,0);
            mFatherLayout.addView(gridLayout,0);
            mFatherLayout.addView(textView,0);

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int index = 0;
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
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(14);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }

    private void initGridLayout1(){
        int index = 1;
        String[] header = {"最大运输质量（Kg) "," ","1.25倍最大运输质量（Kg)"," "};
        String[] leftheader = {"提升1","提升高度（m)","水平移动距离（m)","悬吊时间（min)"};
        String[] leftheader1 = {"提升2","提升高度（m)","水平移动距离（m)","水平移动时间（S)"};
        String[] leftheader2 = {"第一次","第二次","第三次"};
        int ColumnNum = header.length;
        int RowNum = leftheader2.length*2+3;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(14);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    if(i % 2 == 1){
                        datas.add(editText);
                    } else {
                    editText.setKeyListener(null);
                    }
                } else if(m == 1){
                    editText.setText(leftheader[i]);
                    editText.setKeyListener(null);
                } else if(m == 5){
                    editText.setText(leftheader1[i]);
                    editText.setKeyListener(null);
                } else if(i == 0){
                    if(m < 5){
                        editText.setText(leftheader2[(m -2) %3]);
                        editText.setKeyListener(null);
                    } else {
                        editText.setText(leftheader2[(m -6) %3]);
                        editText.setKeyListener(null);
                    }
                } else {
                    if(i == 2 && m <5){
                        editText.setText("/");
                    } else {
                        datas.add(editText);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }


        }
        mViewList.get(index).setShiYanData(datas);
    }

    private void initGridLayout2(){
        int index = 2;
        String[] header = {"检测项目及检测要求","观察结果"};
        String[] leftheader = {"壳体应达到外部机械撞击防护等级（IK）","施加撞击能量（J）",
                "撞击元件等效质量（kg）","跌落高度（mm）","对最大尺寸超过1m的正常使用的每个外露面冲击5次"
        ,"试验后：壳体IP代码和介电强度不变：可移式覆板可以移开和装上，门可以打开和关闭"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(14);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }


        }
        mViewList.get(index).setShiYanData(datas);
    }

    private void initGridLayout3(){
        int index = 3;
        String[] header = {"项目","检测项目","技术要求值(mm)","检测结果"};
        String[] leftHeader = {"电气间隙","爬电距离"};
        String[] leftHeader1 = {"相与相之间","不同电压的电路导体之间","带电部件与裸露导电部件之间","相与相之间","不同电压的电路导体之间","带电部件与裸露导电部件之间"};
        String[] leftHeader2 = {"≥8","/","≥8","≥10","/","≥10"};
        int ColumnNum = header.length;
        int RowNum = leftHeader1.length+1;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(14);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else if(i == 1){
                    editText.setText(leftHeader1[m-1]);
                    editText.setKeyListener(null);
                } else if(i == 2){
                    editText.setText(leftHeader2[m-1]);
                    datas.add(editText);
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;
                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i == 0 && m !=0){
                    datas.remove(editText);
                    if(m%3 == 1){
                        editText.setText(leftHeader[(m-1)%3]);
                        editText.setKeyListener(null);
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

                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }
    private void initGridLayout4(){
        int index = 4;
        String[] header = {"检测要求","检测结果"};
        String[] leftheader = {"试验时先手持一块在水中浸泡过的布，摩擦标志15s，\n" +
                "再用在石油溶剂油中浸泡过的布摩擦标志15s。试验后，标志仍容易辨认"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(14);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }


        }
        mViewList.get(index).setShiYanData(datas);
    }
    private void initGridLayout5(){
        int index = 5;
        String[] header = {"检测项目及检测要求","观察结果"};
        String[] leftheader = {"循环操作200次，元器件的工作状态未受损伤，\n且所要求的操作力与试验前一样",
                "循环操作200次，联锁机构的工作状态未受损伤，\n且所要求的操作力与试验前一样"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(14);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }
    private void initGridLayout6(){
        int index = 6;
        String[] header = {"检验项目","检测要求","测量结果"};
        String[] leftheader = {"柜体材质",
                "柜体厚度","柜体尺寸：高×宽×深"};
        String[] leftheader1 = {"柜体应采用覆铝锌钢板、镀锌板弯折后栓接而成，\n或采用优质防锈处理的冷轧钢板制成，\n或采用304不锈钢制成，或采用SMC材料制成"," ","提供实测值"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(14);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                    editText.setKeyListener(null);
                } else if(i == 1){
                    editText.setText(leftheader1[m-1]);
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }
    @Override
    public void onClick(View v) {
        int buttonId = v.getId();
        if(buttonId < 10010){  //save action
            int index = buttonId - 10000;
            ArrayList<EditText> results = mViewList.get(index).getShiYanData();
            boolean isCheck = mViewList.get(index).getResult().isChecked();
            if(index == 0){
                saveShiyanData(results,isCheck);
            } else if(index == 1){
                saveShiyanData1(results,isCheck);
            } else if(index == 2){
                saveShiyanData2(results,isCheck);
            } else if(index == 3){
                saveShiyanData3(results,isCheck);
            } else if(index == 4){
                saveShiyanData4(results,isCheck);
            } else if(index == 5){
                saveShiyanData5(results,isCheck);
            } else if(index == 6){
                saveShiyanData6(results,isCheck);
            }

        } else {  // update action
            int index = buttonId - 10010;
            DoTaskActivity activity = (DoTaskActivity) getActivity();
            String sampleId = activity.dispatchBean.getSampleId();
            if(index == 0){
                TestItemsBean bean =getTestItems(DiyaShiyanItem.bxczxngn);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 1){
                TestItemsBean bean =getTestItems(DiyaShiyanItem.tssy);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 2){
                TestItemsBean bean =getTestItems(DiyaShiyanItem.jxpzsy);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 3){
                TestItemsBean bean =getTestItems(DiyaShiyanItem.dqjxpdjl);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 4){
                TestItemsBean bean =getTestItems(DiyaShiyanItem.bzjy);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 5){
                TestItemsBean bean =getTestItems(DiyaShiyanItem.kggjxcz);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 6){
                TestItemsBean bean =getTestItems(DiyaShiyanItem.gtczhdcc);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            }

        }
    }

    private void saveShiyanData(ArrayList<EditText> results,boolean isChecked){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.bxczxngn);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(DiyaShiyanItem.getBxczxngn_data()[i],results.get(i).getText().toString());
            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
            Log.e(TAG,"result "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveShiyanData(json.toString());
    }

    private void saveShiyanData1(ArrayList<EditText> results,boolean isChecked ){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.tssy);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(DiyaShiyanItem.getTssy_data()[i],results.get(i).getText().toString());
            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
            Log.e(TAG,"result "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveShiyanData(json.toString());
    }

    private void saveShiyanData2(ArrayList<EditText> results,boolean isChecked ){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.jxpzsy);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(DiyaShiyanItem.jxpzsy_data[i],results.get(i).getText().toString());
            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
            Log.e(TAG,"result "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveShiyanData(json.toString());
    }

    private void saveShiyanData3(ArrayList<EditText> results,boolean isChecked ){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.dqjxpdjl);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(DiyaShiyanItem.getDqjxpdjl_data()[i],results.get(i).getText().toString());
            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
            Log.e(TAG,"result "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveShiyanData(json.toString());
    }

    private void saveShiyanData4(ArrayList<EditText> results,boolean isChecked ){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.bzjy);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(DiyaShiyanItem.bzjy_data[i],results.get(i).getText().toString());
            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
            Log.e(TAG,"result "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveShiyanData(json.toString());
    }

    private void saveShiyanData5(ArrayList<EditText> results,boolean isChecked ){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.kggjxcz);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(DiyaShiyanItem.kggjxcz_data[i],results.get(i).getText().toString());
            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
            Log.e(TAG,"result "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveShiyanData(json.toString());
    }

    private void saveShiyanData6(ArrayList<EditText> results,boolean isChecked ){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.gtczhdcc);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());

            json.put(DiyaShiyanItem.gtczhdcc_data[0],results.get(0).getText().toString());
            json.put(DiyaShiyanItem.gtczhdcc_data[1],results.get(1).getText().toString());
            json.put(DiyaShiyanItem.gtczhdcc_data[2],results.get(2).getText().toString());
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
            Log.e(TAG,"result "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveShiyanData(json.toString());
    }

    HttpListener<String> mDataCallback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {
            try {
                JSONObject json = null;
                json = new JSONObject(response.get());
                String msg = json.getString("msg");
                String result = json.getString("data");
                Log.e(TAG,"mDataCallback result "+response.get().toString());
                if(what == 0){
                    updateShiyanData(result);
                } else if(what == 1){
                    updateShiyanData1(result);
                } else if(what == 2){
                    updateShiyanData2(result);
                } else if(what == 3){
                    updateShiyanData3(result);
                } else if(what == 4){
                    updateShiyanData4(result);
                } else if(what == 5){
                    updateShiyanData5(result);
                } else if(what == 6){
                    updateShiyanData6(result);
                }
            }catch (Exception e){}
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }
    };
    private void updateShiyanData(String result){
        int index = 0;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();
        CheckBox checkBox = mViewList.get(index).getResult();
        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<DiyaShiyanItem.getBxczxngn_data().length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.getBxczxngn_data()[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }catch (Exception e){}
    }
    private void updateShiyanData1(String result){
        int index = 1;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<DiyaShiyanItem.getTssy_data().length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.getTssy_data()[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            CheckBox checkBox = mViewList.get(index).getResult();
            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }catch (Exception e){}
    }
    private void updateShiyanData2(String result){
        int index = 2;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<DiyaShiyanItem.jxpzsy_data.length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.jxpzsy_data[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            CheckBox checkBox = mViewList.get(index).getResult();
            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }catch (Exception e){}
    }
    private void updateShiyanData3(String result){
        int index = 3;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<DiyaShiyanItem.getDqjxpdjl_data().length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.getDqjxpdjl_data()[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            CheckBox checkBox = mViewList.get(index).getResult();
            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }catch (Exception e){}
    }
    private void updateShiyanData4(String result){
        int index = 4;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<DiyaShiyanItem.bzjy_data.length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.bzjy_data[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            CheckBox checkBox = mViewList.get(index).getResult();
            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }catch (Exception e){}
    }
    private void updateShiyanData5(String result){
        int index = 5;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<DiyaShiyanItem.kggjxcz_data.length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.kggjxcz_data[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            CheckBox checkBox = mViewList.get(index).getResult();
            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }catch (Exception e){}
    }
    private void updateShiyanData6(String result){
        int index = 6;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<DiyaShiyanItem.gtczhdcc_data.length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.gtczhdcc_data[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            CheckBox checkBox = mViewList.get(index).getResult();
            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }catch (Exception e){}
    }

}
