package com.surface.resourcecenter.ui.shiyan.nativeData.zhushang;

import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
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

public class ZhushangHuiludianzu extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private CheckBox mTestResult;
    private Button mRefresh,mSave;
    private String[] gridHeader = {"主回路电阻测量"};
    public ZhushangHuiludianzu(){

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
        String[] header = {"测量部位","试验电流（A）","技术要求值（μΩ）","测量或观察结果（μΩ）"};
        String[] leftheader = {"Aa","Bb","Cc"};
        String[] leftheader1 = {"≤150"};
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
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                if(i == 2 && m != 0){
                    if(m == 1){
                        rowSpec=GridLayout.spec(m, 3, GridLayout.FILL);
                        editText.setText(leftheader1[0]);
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
                mViewList.get(0).getGridLayout().addView(editText,params);
            }
        }
        mViewList.get(0).setShiYanData(datas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_refresh:
                DoTaskActivity activity = (DoTaskActivity) getActivity();
                String sampleId = activity.dispatchBean.getSampleId();
                TestItemsBean bean =getTestItems(ZhuShangShiyanItem.gyzhldz);
                getShiyanData(0,sampleId,bean.getId(),mDataCallback);
                break;
            case R.id.test_save:
                saveShiyanData(mViewList.get(0).getShiYanData(),mTestResult.isChecked());
                break;
        }
    }

    private void saveShiyanData(ArrayList<EditText> results,boolean isChecked){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(ZhuShangShiyanItem.gyzhldz);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            if(!TextUtils.isEmpty(resultId)){
                json.put("id",resultId);
            }
            for(int i = 0;i<results.size();i++){
                json.put(ZhuShangShiyanItem.gyzhldz_data[i],results.get(i).getText().toString());
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
//                Log.e(TAG,"mDataCallback result "+response.get().toString());
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
        int index = 0;
        ArrayList<EditText> results = mViewList.get(index).getShiYanData();

        try {
            JSONObject jsonObject = new JSONObject(result);
            for(int i = 0;i<ZhuShangShiyanItem.gyzhldz_data.length;i++){
                String data = jsonObject.getString(ZhuShangShiyanItem.gyzhldz_data[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
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
