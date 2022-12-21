package com.surface.resourcecenter.ui.shiyan.nativeData.zhushang;

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
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhuShangShiyanItem;
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

public class ZhushangLeidianChongji extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private CheckBox mTestResult;
    private Button mRefresh,mSave;
    private String[] gridHeader = {"开关装置处于合闸状态 正极性","开关装置处于合闸状态 负极性","开关装置处于分闸状态 正极性","开关装置处于分闸状态 负极性"};
    public ZhushangLeidianChongji(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_gridlayout_normal;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init(View view) {
        mFatherLayout = view.findViewById(R.id.grid_father);
        mTestResult = view.findViewById(R.id.test_result);
        mRefresh = view.findViewById(R.id.test_refresh);
        mRefresh.setOnClickListener(this);
        mSave = view.findViewById(R.id.test_save);
        mSave.setOnClickListener(this);
        initView();
        initGridLayout();
        initGridLayout1();
        initGridLayout2();
        initGridLayout3();
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
        String[] header = {"测试部位","施加电压(峰值)(kV)","修正后施加电压（kV）","实测电压峰值（kV）","加压次数","击穿次数"};
        String[] leftheader = {"Aa-F","Bb-F","Cc-F"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(0).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(0).getGridLayout().setRowCount(RowNum);
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
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 2){
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 5){
                    editText.setText("0");
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
                    editText.setText(m+""+i);
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
        mViewList.get(0).setShiYanData(datas);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout1(){
        String[] header = {"测试部位","施加电压(峰值)(kV)","修正后施加电压（kV）","实测电压峰值（kV）","加压次数","击穿次数"};
        String[] leftheader = {"Aa-F","Bb-F","Cc-F"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(1).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(1).getGridLayout().setRowCount(RowNum);
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
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 2){
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 5){
                    editText.setText("0");
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
                    editText.setText(m+""+i);
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
        mViewList.get(1).setShiYanData(datas);
    }
    private void initGridLayout2(){
        String[] header = {"测试部位","施加电压(峰值)(kV)","修正后施加电压（kV）","实测电压峰值（kV）","加压次数","击穿次数"};
        String[] leftheader = {"A-a","B-b","C-c","a-A","b-B","c-C"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(2).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(2).getGridLayout().setRowCount(RowNum);
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
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 2){
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 5){
                    editText.setText("0");
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
                    editText.setText(m+""+i);
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
        mViewList.get(2).setShiYanData(datas);
    }
    private void initGridLayout3(){
        String[] header = {"测试部位","施加电压(峰值)(kV)","修正后施加电压（kV）","实测电压峰值（kV）","加压次数","击穿次数"};
        String[] leftheader = {"A-a","B-b","C-c","a-A","b-B","c-C"};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(3).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(3).getGridLayout().setRowCount(RowNum);
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
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 2){
                    editText.setText("1");
                    datas.add(editText);
                } else if(i == 5){
                    editText.setText("0");
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
                    editText.setText(m+""+i);
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
        mViewList.get(3).setShiYanData(datas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_refresh:
                DoTaskActivity activity = (DoTaskActivity) getActivity();
                String sampleId = activity.dispatchBean.getSampleId();
                TestItemsBean bean =getTestItems(ZhuShangShiyanItem.gyldcj);
                getShiyanData(0,sampleId,bean.getId(),mDataCallback);
                break;
            case R.id.test_save:
                saveShiyanData(mTestResult.isChecked());
                break;
        }
    }

    private void saveShiyanData(boolean isChecked){
        ArrayList<EditText> results = mViewList.get(0).getShiYanData();
        ArrayList<EditText> results1 = mViewList.get(1).getShiYanData();
        ArrayList<EditText> results2 = mViewList.get(2).getShiYanData();
        ArrayList<EditText> results3 = mViewList.get(3).getShiYanData();
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(ZhuShangShiyanItem.gyldcj);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            if(!TextUtils.isEmpty(resultId)){
                json.put("id",resultId);
            }
            String[] data = ZhuShangShiyanItem.getLdcj_h_z();
            int m = 0;
            for(int i = 0;i<results.size();i++){
                if(i % 4 >1){
                    json.put(data[m++],results.get(i).getText().toString());
                } else {
                    json.put("ys1",results.get(i).getText().toString());
                    json.put("ys2",results.get(i).getText().toString());
                }
            }
            String[] data1 = ZhuShangShiyanItem.getLdcj_h_f();
            m = 0;
            for(int i = 0;i<results1.size();i++){
                if(i % 4 >1){
                    json.put(data1[m++],results1.get(i).getText().toString());
                } else {
                    json.put("ys3",results1.get(i).getText().toString());
                    json.put("ys4",results1.get(i).getText().toString());
                }
            }
            String[] data2 = ZhuShangShiyanItem.getLdcj_f_z();
            m = 0;
            for(int i = 0;i<results2.size();i++){
                if(i % 4 >1){
                    json.put(data2[m++],results2.get(i).getText().toString());
                } else {
                    json.put("ys5",results2.get(i).getText().toString());
                    json.put("ys6",results2.get(i).getText().toString());
                }
            }
            String[] data3 = ZhuShangShiyanItem.getLdcj_f_f();
            m = 0;
            for(int i = 0;i<results3.size();i++){
                if(i % 4 >1){
                    json.put(data3[m++],results3.get(i).getText().toString());
                } else {
                    json.put("ys7",results3.get(i).getText().toString());
                    json.put("ys8",results3.get(i).getText().toString());
                }
            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
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
                updateShiyanData(result);

            }catch (Exception e){}
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }
    };
    private String resultId = "";
    private void updateShiyanData(String result){
        mSave.setEnabled(true);
        ArrayList<EditText> results = mViewList.get(0).getShiYanData();
        ArrayList<EditText> results1 = mViewList.get(1).getShiYanData();
        ArrayList<EditText> results2 = mViewList.get(2).getShiYanData();
        ArrayList<EditText> results3 = mViewList.get(3).getShiYanData();

        try {
            JSONObject jsonObject = new JSONObject(result);
            resultId = jsonObject.getString("id");
            String[] data = ZhuShangShiyanItem.getLdcj_h_z();
            int m = 0;
            String d1 = jsonObject.getString("ys1");
            String d2 = jsonObject.getString("ys2");
            String d3 = jsonObject.getString("ys3");
            String d4 = jsonObject.getString("ys4");
            String d5 = jsonObject.getString("ys5");
            String d6 = jsonObject.getString("ys6");
            String d7 = jsonObject.getString("ys7");
            String d8 = jsonObject.getString("ys8");
            for(int i = 0;i<results.size();i++){
                if(i % 4 == 0){
                    results.get(i).setText(d1);
                } else if(i%4 == 1){
                    results.get(i).setText(d2);
                } else {
                    String d = jsonObject.getString(data[m++]);
                    results.get(i).requestFocus();
                    results.get(i).setText(d);
                }
            }
            String[] data1 = ZhuShangShiyanItem.getLdcj_h_f();
            m = 0;
            for(int i = 0;i<results1.size();i++){
                if(i % 4 == 0){
                    results1.get(i).setText(d3);
                } else if(i%4 == 1){
                    results1.get(i).setText(d4);
                } else {
                    String d = jsonObject.getString(data1[m++]);
                    results1.get(i).requestFocus();
                    results1.get(i).setText(d);
                }
            }
            String[] data2 = ZhuShangShiyanItem.getLdcj_f_z();
            m = 0;
            for(int i = 0;i<results2.size();i++){
                if(i % 4 == 0){
                    results2.get(i).setText(d5);
                } else if(i%4 == 1){
                    results2.get(i).setText(d6);
                } else {
                    String d = jsonObject.getString(data2[m++]);
                    results2.get(i).requestFocus();
                    results2.get(i).setText(d);
                }
            }
            String[] data3 = ZhuShangShiyanItem.getLdcj_f_f();
            m = 0;
            for(int i = 0;i<results3.size();i++){
                if(i % 4 == 0){
                    results3.get(i).setText(d7);
                } else if(i%4 == 1){
                    results3.get(i).setText(d8);
                } else {
                    String d = jsonObject.getString(data3[m++]);
                    results3.get(i).requestFocus();
                    results3.get(i).setText(d);
                }
            }

            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                mTestResult.setChecked(true);
            } else {
                mTestResult.setChecked(false);
            }
        }catch (Exception e){}
    }
}
