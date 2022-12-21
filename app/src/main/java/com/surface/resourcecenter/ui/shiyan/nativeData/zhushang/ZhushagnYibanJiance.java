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

public class ZhushagnYibanJiance extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private String[] gridHeader = {"设计和外观检查","密封试验（适用于箱式开关）"};
    public ZhushagnYibanJiance(){

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
        String[] header = {"项目","检查结果"};
        String[] leftheader = {"结构","外观"};

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

    private void initGridLayout1(){
        int index = 1;
        String[] header = {"检测项目","检测结果"};
        String[] leftheader = {"充入气体性质","试验前压力","试验后压力"," 密封罩的体积","试验方法描述","试验结果（包括计算过程）"};
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
            }

        } else {  // update action
            int index = buttonId - 10010;
            DoTaskActivity activity = (DoTaskActivity) getActivity();
            String sampleId = activity.dispatchBean.getSampleId();
            if(index == 0){
                TestItemsBean bean =getTestItems(ZhuShangShiyanItem.dlqsjwg);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            } else if(index == 1){
                TestItemsBean bean =getTestItems(ZhuShangShiyanItem.dlqmfsy);
                getShiyanData(index,sampleId,bean.getId(),mDataCallback);
            }
        }
    }

    private void saveShiyanData(ArrayList<EditText> results,boolean isChecked){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(ZhuShangShiyanItem.dlqsjwg);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(ZhuShangShiyanItem.dlqsjwg_data[i],results.get(i).getText().toString());
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
        TestItemsBean bean =getTestItems(ZhuShangShiyanItem.dlqmfsy);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            for(int i = 0;i<results.size();i++){
                json.put(ZhuShangShiyanItem.dlqmfsy_data[i],results.get(i).getText().toString());
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
            for(int i = 0;i<ZhuShangShiyanItem.dlqsjwg_data.length;i++){
                String data = jsonObject.getString(ZhuShangShiyanItem.dlqsjwg_data[i]);
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
            for(int i = 0;i<ZhuShangShiyanItem.dlqmfsy_data.length;i++){
                String data = jsonObject.getString(ZhuShangShiyanItem.dlqmfsy_data[i]);
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
