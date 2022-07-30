package com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.BaseFragment;


/**
 * home activity 容器
 *
 * @author yangzhipeng
 * @date 2018/6/21
 */

public class GaoyaJuFangIndex9 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    GridLayout mGridLayout;
    public GaoyaJuFangIndex9(){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_main_gridlayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init(View view) {
        mGridLayout = view.findViewById(R.id.grid_layout);
        deviceName = view.findViewById(R.id.shebei_name);
        deviceTypeId = view.findViewById(R.id.shebei_xinghao);
        deviceId = view.findViewById(R.id.shebei_bianhao);
        remark = view.findViewById(R.id.remark);
        mPicTitle = view.findViewById(R.id.pic_title);
        view.findViewById(R.id.huanjing_layout).setVisibility(View.GONE);
        deviceName.setText("");
        deviceTypeId.setText("");
        deviceId.setText("/");
        remark.setText("");


        initGridLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initGridLayout(){
        int ColumnNum = 6;
        int RowNum = 3;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"施加电压(kV)","施加电压(kV)","持续时间(s)","A","B","C"};
        String[] leftheader = {"预加电压1.8Ur","测量电压1.3Ur"};
        String[] leftheader1 = {"30","180"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                }else if(i == 2){
                    editText.setText(leftheader1[m-1]);
                }
                editText.setPadding(20,10,20,10);
                editText.setMinWidth(150);

                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;

                if(m == 0 && i == 0){
                    //表示起始位置为m，占据1行
                    rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 2, GridLayout.FILL);
                } else if(m == 0 && i==1){
                    continue;
                } else {
                    //表示起始位置为m，占据1行
                    rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 1, GridLayout.FILL);
                }
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.height = 60;
                mGridLayout.addView(editText,params);
            }


        }

    }

    @Override
    public void onClick(View v) {
//        Router.launchRender3DActivity(getContext(),new Bundle());
//            ToastUtils.show("视频正在上传中。。。");
    }
}
