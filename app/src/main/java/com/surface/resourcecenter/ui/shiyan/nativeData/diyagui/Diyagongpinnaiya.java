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
import com.surface.resourcecenter.data.utils.ToastUtils;
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

public class Diyagongpinnaiya extends BaseFragment implements View.OnClickListener {

    private List<GridLayoutBean> mViewList = new ArrayList<>();
    private LinearLayout mFatherLayout;
    private CheckBox mTestResult;
    private Button mRefresh,mSave;
    private String[] gridHeader = {"工频耐压试验","正极性","负极性"};
    public Diyagongpinnaiya(){

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
        String[] header = {"检测项目","试验电压(V) ","施压时间(s)"," 观察结果"};
        String[] leftheader = {"主回路的所有带电部件\n（包括连接到主回路上的控制电路和辅助电路）\n与裸露导电部件之间(A，B，C，N)-PE",
                "每个极和连接到裸露导电部件上的所有\n其他极之间A-(B、C、N、PE)",
                "每个极和连接到裸露导电部件上的所有\n其他极之间B-(A、C、N、PE)",
                "每个极和连接到裸露导电部件上的所有\n其他极之间C-(A、B、N、PE)",
                "每个极和连接到裸露导电部件上的所有\n其他极之间N-(A、B、C、PE)",
                "带电部件和用金属箔裹缠的手柄之间（1.5倍试验电压）"};

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
                    editText.setText(leftheader[i]);
                    datas.add(editText);
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
        String[] header = {"加压部位","接地部位","施加电压(峰值)(kV)","加压次数","击穿次数"};
        String[] leftheader = {"A、B、C、N ","A","B ","C","N"};
        String[] leftheader1 = {"PE","B、C、N 、PE","A、C、N 、PE","A、B、N 、PE","A、B、C、PE"};
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
                }  else if(i == 1){
                    editText.setText(leftheader1[m-1]);
                    datas.add(editText);
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
                mViewList.get(1).getGridLayout().addView(editText,params);
            }


        }
        mViewList.get(1).setShiYanData(datas);
    }

    private void initGridLayout2(){
        String[] header = {"加压部位","接地部位","施加电压(峰值)(kV)","加压次数","击穿次数"};
        String[] leftheader = {"A、B、C、N ","A","B ","C","N"};
        String[] leftheader1 = {"PE","B、C、N 、PE","A、C、N 、PE","A、B、N 、PE","A、B、C、PE"};
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
                    editText.setKeyListener(null);
                } else if(i == 1){
                    editText.setText(leftheader1[m-1]);
                    datas.add(editText);
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
                mViewList.get(2).getGridLayout().addView(editText,params);
            }


        }
        mViewList.get(2).setShiYanData(datas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_refresh:
                DoTaskActivity activity = (DoTaskActivity) getActivity();
                String sampleId = activity.dispatchBean.getSampleId();
                TestItemsBean bean =getTestItems(DiyaShiyanItem.dyjdxnsy);
                getShiyanData(0,sampleId,bean.getId(),mDataCallback);
                break;
            case R.id.test_save:
                if(resultId.equals("-1")){
                    ToastUtils.ShowCenterToast(getContext(),"请先点击刷新按钮，更新页面数据");
                    return;
                }
                saveShiyanData(mTestResult.isChecked());
                break;
        }
    }

    private void saveShiyanData(boolean isChecked){
        ArrayList<EditText> results = mViewList.get(0).getShiYanData();
        ArrayList<EditText> results1 = mViewList.get(1).getShiYanData();
        ArrayList<EditText> results2 = mViewList.get(2).getShiYanData();
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        TestItemsBean bean =getTestItems(DiyaShiyanItem.dyjdxnsy);
        JSONObject json = new JSONObject();
        try {
            json.put("sample",activity.dispatchBean.getSampleId());
            json.put("experiment",bean.getId());
            json.put("sign",bean.getSign());
            json.put("experiment_name",bean.getName());
            json.put("experimentName2","冲击耐受电压试验");
            json.put("experimentName1",gridHeader[0]);
            if(!TextUtils.isEmpty(resultId)){
                json.put("id",resultId);
            }
            for(int i = 0;i<results.size();i++){
                json.put(DiyaShiyanItem.getDyjdxnsy_data()[i],results.get(i).getText().toString());
            }
            for(int i = 0;i<results1.size();i++){
                json.put(DiyaShiyanItem.getDyjdxnsy_Z_data()[i],results1.get(i).getText().toString());
            }
            for(int i = 0;i<results2.size();i++){
                json.put(DiyaShiyanItem.getDyjdxnsy_F_data()[i],results2.get(i).getText().toString());
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

        try {
            JSONObject jsonObject = new JSONObject(result);
            resultId = jsonObject.getString("id");
            for(int i = 0;i<DiyaShiyanItem.getDyjdxnsy_data().length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.getDyjdxnsy_data()[i]);
                results.get(i).requestFocus();
                results.get(i).setText(data);
            }
            for(int i = 0;i<DiyaShiyanItem.getDyjdxnsy_Z_data().length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.getDyjdxnsy_Z_data()[i]);
                results1.get(i).requestFocus();
                results1.get(i).setText(data);
            }
            for(int i = 0;i<DiyaShiyanItem.getDyjdxnsy_F_data().length;i++){
                String data = jsonObject.getString(DiyaShiyanItem.getDyjdxnsy_F_data()[i]);
                results2.get(i).requestFocus();
                results2.get(i).setText(data);
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
