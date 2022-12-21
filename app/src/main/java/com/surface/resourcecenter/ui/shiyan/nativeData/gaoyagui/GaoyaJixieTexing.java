package com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui;

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

public class GaoyaJixieTexing extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private CheckBox mTestResult;
    private Button mRefresh,mSave;
    private String[] gridHeader = {"机械特性试验","机械操作"};
    public GaoyaJixieTexing(){

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
        String[] header = {"试验参数名称","技术要求","A相","B相","C相"};
        String[] leftheader = {"合闸时间（ms）","合闸不同期（ms）","分闸时间（ms）","分闸不同期（ms）","合闸弹跳（ms）"};
        String[] leftheader1 = {"30-70","≤2.0","20-50","≤2.0","≤2.0"};
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
                    editText.setText(leftheader1[m-1]);
                    editText.setTextSize(14);
                } else {
                    datas.add(editText);
                }
                editText.setPadding(20,20,20,20);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                //表示起始位置为m，占据1行
                rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);

                if((m == 2 || m == 4) && (i == 2)){
                    columnSpec=GridLayout.spec(i, 3, 1.75f);
                } else if((m == 2 || m == 4) && i > 2){
                    datas.remove(editText);
                    continue;
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
        int index = 1;
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
                mViewList.get(index).getGridLayout().addView(editText,params);
            }


        }
        mViewList.get(index).setShiYanData(datas);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_refresh:
                DoTaskActivity activity = (DoTaskActivity) getActivity();
                String sampleId = activity.dispatchBean.getSampleId();
                TestItemsBean bean =getTestItems(GaoyaShiyanItem.gyjx);
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
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        String[] leftheader = {"合闸电压低于额定30%，操作5次可靠不动作"
                ,"合闸电压85%~110%范围内操作5次可靠动作"
                ,"分闸电压低于额定30%，操作5次可靠不动作"
                ,"分闸电压65%~110%范围内操作5次可靠动作"
                ,"额定操作电压下操作5次，可靠动作"
                ,"人力操作5次，可靠动作"
                ,"额定操作电压“分-0.3-合分”，操作5次可靠动作"
                ,"储能电机85%和110%操作电压，操作5次储能可靠动作"};
        TestItemsBean bean =getTestItems(GaoyaShiyanItem.gyjx);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            json.put("experimentName1",gridHeader[0]);
            json.put("experimentName2",gridHeader[1]);
            if(!TextUtils.isEmpty(resultId)){
                json.put("id",resultId);
            }
            for(int i = 0;i<results.size();i++){
                json.put(GaoyaShiyanItem.gyjx1_data[i],results.get(i).getText().toString());
            }
            for(int i = 0;i<results1.size();i++){
                json.put(GaoyaShiyanItem.gyjx_data[i],results1.get(i).getText().toString());
                json.put("yq"+(i+1),leftheader[i]);
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
        ArrayList<EditText> results = mViewList.get(0).getShiYanData();
        ArrayList<EditText> results1 = mViewList.get(1).getShiYanData();

        try {
            JSONObject jsonObject = new JSONObject(result);
            resultId = jsonObject.getString("id");
            for(int i = 0;i<GaoyaShiyanItem.gyjx1_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.gyjx1_data[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            for(int i = 0;i<GaoyaShiyanItem.gyjx_data.length;i++){
                String data = jsonObject.getString(GaoyaShiyanItem.gyjx_data[i]);
                results1.get(i).requestFocus();
                results1.get(i).setText(data);
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
