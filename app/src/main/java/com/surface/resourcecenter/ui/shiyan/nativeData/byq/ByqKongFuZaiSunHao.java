package com.surface.resourcecenter.ui.shiyan.nativeData.byq;

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

public class ByqKongFuZaiSunHao extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private String[] gridHeader = {"空载损耗和空载电流测量","在90%、110%电压下空载损耗和空载电流测量","短路阻抗和负载损耗测量"};
    private String[] gridRemark = {"技术要求值: 空载损耗不得有正偏差，空载电流应不大于技术要求值+30%\n"," \n","技术要求值: 短路阻抗应为规定值的±10%以内，负载损耗不得有正偏差。\n"};
    public ByqKongFuZaiSunHao(){

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

            TextView remark = new TextView(getContext());
            remark.setTextColor(Color.parseColor("#111000"));
            remark.setTextSize(16);
            remark.setLayoutParams(params1);
            remark.setText(gridRemark[gridRemark.length -1-i]);

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
            bean.setReMark(remark);
            bean.setSave(save);
            bean.setUpdate(update);
            bean.setResult(checkBox);
            mViewList.add(0,bean);
            mFatherLayout.addView(layout,0);
            mFatherLayout.addView(remark,0);
            mFatherLayout.addView(gridLayout,0);
            mFatherLayout.addView(textView,0);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    private void initGridLayout(){
        int index = 0;
        String[] header = {"施加电压(V)\n平均值","施加电压(V)\n方均根值","测量电流\n(A)","测量损耗\n(W)",
                "空载损耗\nP0(W)","空载电流\nI0(A)","空载电流\nI0(%)","规定值\nP0(W)","规定值\nI0(%)"};
        String[] leftheader = {""};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(12);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }

    private void initGridLayout1(){
        int index = 1;
        String[] header = {"U/Ur","平均值电压\n(V)","方均根值\n电压(V)","空载电流I0\n(A)",
                "空载电流I0\n(%)","空载损耗\nP0(W)"};
        String[] leftheader = {"90%","110%"};
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
                columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                mViewList.get(index).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(index).setShiYanData(datas);
    }

    private void initGridLayout2(){
        int index = 2;
        String[] header = {"绕组","分接位置","施加\n电流(A)","测量\n电压(V)",
                "测量损耗\n(W)","负载损耗\nPk75℃(W)","短路阻抗\nZk75℃","总损耗\nP总(W)","规定值\nI0(%)","Pk75℃\n(W)","Zk75℃(%)","P总(W)"};
        String[] leftheader = {""};
        int ColumnNum = header.length;
        int RowNum = leftheader.length+1;
        mViewList.get(index).getGridLayout().setColumnCount(ColumnNum);
        mViewList.get(index).getGridLayout().setRowCount(RowNum);
        ArrayList<EditText> datas = new ArrayList<>();
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                EditText editText = new EditText(getContext());
                editText.setTextSize(10);
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                    editText.setKeyListener(null);
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
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

}
