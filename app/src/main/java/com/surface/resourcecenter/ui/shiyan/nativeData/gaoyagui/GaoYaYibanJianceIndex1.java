package com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui;

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

import com.google.gson.JsonObject;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.utils.ToastUtils;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;
import com.surface.resourcecenter.ui.shiyan.nativeData.GridLayoutBean;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONArray;
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

public class GaoYaYibanJianceIndex1 extends BaseFragment implements View.OnClickListener {

    private String TAG = "GaoYaYibanJianceIndex1";
    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private String[] gridHeader = {"接线形式、相序、空气净距检查","电气联锁试验","柜体尺寸、厚度、材质检测","隔离开关触头镀银层厚度检测","防护等级检验","密封试验（适用于气体绝缘环网柜）"};
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
        view.findViewById(R.id.bottom_layout).setVisibility(View.GONE);
        initView();
        initGridLayout();
        initGridLayout1();
        initGridLayout2();
        initGridLayout3();
        initGridLayout4();
        initGridLayout5();
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
        String[] header = {"项目","检测要求","测量或观察结果"};
        String[] leftheader = {"接线形式","相序","安全净距","安全净距","安全净距"};
        String[] leftheader1 = {"水平出线、垂直出线或品形出线","面对柜体前面板：\n" +
                "①左中右对应相序为ABC\n" +
                "②上中下对应相序为ABC\n" +
                "③远中近对应相序为ABC\n" +
                "④其他",
                "12kV：检查以空气为绝缘介质的开关柜，母线室、电缆室内的相间\n和相对地最小空气间隙",
                "12kV：检查以空气为绝缘介质的开关柜，母线室、电缆室内\n" +
                        "相间和相对地≥125mm;",
                "12kV：检查以空气为绝缘介质的开关柜，母线室、电缆室内\n" +
                "带电体至门≥155mm;"};
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

    private void initGridLayout1(){
        int index = 1;
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
                    editText.setText(leftHeader[m-1]);
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

    private void initGridLayout2(){
        int index = 2;
        String[] header = {"检测项目","检测要求","测量或观察结果"};
        String[] leftheader = {"柜体尺寸（高×宽×深）","厚度","柜体材质"};
        String[] leftheader1 = {"提供实测值","覆铝锌板≥1.84","柜体应采用敷铝锌钢板、\n镀锌板弯折后栓接而成，\n或采用优质防锈处理的冷轧钢板制成，\n或采用不锈钢制成"};
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
                }else if(i == 1){
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
    private void initGridLayout3(){
        int index = 3;
        String[] header = {"测量部位","检测要求（um）","测量或观察结果（um）"};
        String[] leftheader = {"A上触头","A下触头","B上触头","B下触头","C上触头","C下触头"};
        String[] leftheader1 = {"≥8"};
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
                if(i == 1 && m != 0){
                    datas.remove(editText);
                    if(m == 1){
                        rowSpec=GridLayout.spec(m, 6, GridLayout.FILL);
                        editText.setText(leftheader1[i-1]);
                        editText.setKeyListener(null);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }

    private void initGridLayout4(){
        int index = 4;
        String[] header = {"检测部位","技术标准","检测方法","检测结果"};
        String[] leftheader = {"柜体外壳","隔板","活门","盖板"};
        String[] leftheader1 = {"柜体外壳防护等级\n达到IP4X及以上，\n隔室之间\n达到IP2X及以上。"};
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
                if(i == 1 && m != 0){
                    datas.remove(editText);
                    if(m == 1){
                        rowSpec=GridLayout.spec(m, 4, GridLayout.FILL);
                        editText.setText(leftheader1[i-1]);
                        editText.setKeyListener(null);
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }

    private void initGridLayout5(){
        int index = 5;
        String[] header = {"检测项目","观察结果"};
        String[] leftheader = {"充入气体性质",
                "试验前压力","试验后压力","密封罩的体积"};
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
                    editText.setKeyListener(null);
                    editText.setText(leftheader[m-1]);
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
            }

        } else {  // update action
            int index = buttonId - 10010;
            DoTaskActivity activity = (DoTaskActivity) getActivity();
            String sampleId = activity.dispatchBean.getSampleId();
            if(index == 0){
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.jxxsxxkqjjjc);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 1){
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.dqls);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 2){
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.gygthdcz);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 3){
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.ctdyc);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 4){
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.fhdj);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 5){
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.mfsyhwg);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            }

        }
    }

    private void saveShiyanData(ArrayList<EditText> results,boolean isChecked){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.jxxsxxkqjjjc);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(GaoyaShiyanItem.jxxsxxkqjjjc_data[i],results.get(i).getText().toString());
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
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.dqls);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(GaoyaShiyanItem.dqls_data[i],results.get(i).getText().toString());
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
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.gygthdcz);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(GaoyaShiyanItem.gygthdcz_data[i],results.get(i).getText().toString());
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
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.ctdyc);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(GaoyaShiyanItem.ctdyc_data[i],results.get(i).getText().toString());
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
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.fhdj);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size() -1;i++){
                json.put(GaoyaShiyanItem.fhdj_data[i],results.get(i).getText().toString());
                i++;
                json.put(GaoyaShiyanItem.fhdj_data[i],results.get(i).getText().toString());
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
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.mfsyhwg);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());

            json.put(GaoyaShiyanItem.mfsyhwg_data[0],results.get(0).getText().toString());
            json.put(GaoyaShiyanItem.mfsyhwg_data[1],results.get(1).getText().toString());
            json.put(GaoyaShiyanItem.mfsyhwg_data[2],results.get(2).getText().toString());
            json.put(GaoyaShiyanItem.mfsyhwg_data[3],results.get(3).getText().toString());
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
            for(int i = 0;i<GaoyaShiyanItem.jxxsxxkqjjjc_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.jxxsxxkqjjjc_data[i]);
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
            for(int i = 0;i<GaoyaShiyanItem.dqls_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.dqls_data[i]);
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
            for(int i = 0;i<GaoyaShiyanItem.gygthdcz_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.gygthdcz_data[i]);
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
            for(int i = 0;i<GaoyaShiyanItem.ctdyc_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.ctdyc_data[i]);
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
            for(int i = 0;i<GaoyaShiyanItem.fhdj_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.fhdj_data[i]);
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
            for(int i = 0;i<GaoyaShiyanItem.mfsyhwg_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.mfsyhwg_data[i]);
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
