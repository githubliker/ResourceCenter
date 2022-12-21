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

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;
import com.surface.resourcecenter.ui.shiyan.nativeData.GridLayoutBean;
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

public class GaoyaWensheng extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private CheckBox mTestResult;
    private Button mRefresh,mSave;
    private String[] gridHeader = {"环境温升","多触头测量点","单触头测量点","主回路电阻平均值"};
    public GaoyaWensheng(){

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
        String[] header = {"探头编号","测试部位","实测温度（℃）"};
        String[] leftheader = {"1","2","3"};
        String[] leftheader1 = {"正面1米处环境温度","侧面1米处环境温度","背面1米处环境温度"};
        int ColumnNum = header.length;
        int RowNum = leftheader1.length+1;
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
                mViewList.get(0).getGridLayout().addView(editText,params);
            }


        }
        mViewList.get(0).setShiYanData(datas);
    }

    private void initGridLayout1(){
        String[] header = {"探头编号","允许温升值（K）","实测温升（K）","实测温升（K）","实测温升（K）"};
        String[] leftheader = {"5、6、7","8、9、10","11、12、13","14、15、16",
                "17、18、19","20、21、22","23、24、25","26、27、28","29、30、31","32、33、34","35、36、37","38、39、40","41、42、43","44、45、46",
                "47、48、49","50、51、52","53、54、55","56、57、58","59、60、61"};
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
                    datas.add(editText);
                } else if(i == 1){
                    editText.setText("≤70");
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(m == 0 &&i == 2){
                    columnSpec=GridLayout.spec(i, 3, 0.75f);
                } else if(m == 0 && (i == 3 || i == 4)){
                    continue;
                }else {
                    columnSpec=GridLayout.spec(i, 1, 0.25f);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(1).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(1).setShiYanData(datas);
    }

    private void initGridLayout2(){
        String[] header = {"探头编号","允许温升值（K）","实测温度（℃）"};
        String[] leftheader = {"1","2","3","4","5"};
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
                    datas.add(editText);
                } else if(i == 1){
                    editText.setText("30");
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == 0){
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
        String[] header = {"  ","A","B","C"};
        String[] leftheader = {"试验前主回路电阻平均值（μΩ）","试验后主回路电阻平均值（μΩ）"};
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
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i  == 0){
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, 1.75f);
                } else {
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1,0.25f);
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
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.gywssy);
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
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.gywssy);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            if(!TextUtils.isEmpty(resultId)){
                json.put("id",resultId);
            }
            String[] data = GaoyaShiyanItem.gywssy_data;
            for(int i = 0;i<data.length;i++){
                json.put(data[i],results.get(i).getText().toString());
            }
            String[] data1 = GaoyaShiyanItem.getGyWssyData_2();
            for(int i = 0;i<data1.length;i++){
                json.put(data1[i],results1.get(i).getText().toString());
            }
            String[] data2 = GaoyaShiyanItem.getGyWssyData_3();
            for(int i = 0;i<data2.length;i++){
                json.put(data2[i],results2.get(i).getText().toString());
            }
//            String[] data3 = GaoyaShiyanItem.getLdcj_f_f();
//            for(int i = 0;i<data3.length;i++){
//                json.put(data3[i],results3.get(i).getText().toString());
//            }
            if(isChecked){
                json.put("result","合格");
            } else {
                json.put("result","不合格");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.d(TAG,"data "+json.toString());
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
            String[] data = GaoyaShiyanItem.gywssy_data;
            for(int i = 0;i<data.length;i++){
                String d = jsonObject.getString(data[i]);
                results.get(i).requestFocus();
                results.get(i).setText(d);
            }
            String[] data1 = GaoyaShiyanItem.getGyWssyData_2();
            for(int i = 0;i<data1.length;i++){
                String d = jsonObject.getString(data1[i]);
                results1.get(i).requestFocus();
                results1.get(i).setText(d);
            }
            String[] data2 = GaoyaShiyanItem.getGyWssyData_3();
            for(int i = 0;i<data2.length;i++){
                String d = jsonObject.getString(data2[i]);
                results2.get(i).requestFocus();
                results2.get(i).setText(d);
            }
//            String[] data3 = GaoyaShiyanItem.getLdcj_f_f();
//            for(int i = 0;i<data3.length;i++){
//                String d = jsonObject.getString(data3[i]);
//                results3.get(i).requestFocus();
//                results3.get(i).setText(d);
//            }

            String r = jsonObject.getString("result");
            if(!TextUtils.isEmpty(r) && "合格".equals(r)){
                mTestResult.setChecked(true);
            } else {
                mTestResult.setChecked(false);
            }
        }catch (Exception e){}
    }
}
