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

public class GaoyaJixieCaozuoIndex6 extends BaseFragment implements View.OnClickListener {


    private TextView deviceName,deviceTypeId,deviceId,remark,mPicTitle;
    GridLayout mGridLayout;
    public GaoyaJixieCaozuoIndex6(){

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
        int ColumnNum = 5;
        int RowNum = 11;
        mGridLayout.setColumnCount(ColumnNum);
        mGridLayout.setRowCount(RowNum);
        String[] header = {"试验参数名称","技术要求","A相","B相","C相"};
        String[] leftheader = {"行程，mm","合分时间，ms","合闸时间，ms","合闸不同期，ms","合闸弹跳，ms","合闸速度，m/s",
                "分闸时间，ms","分闸不同期，ms","分闸反弹，mm","分闸速度，m/s"};
        for(int m = 0 ;m<RowNum;m++){
            for(int i = 0;i<ColumnNum;i++){
                TextView editText = new TextView(getContext());
                editText.setBackgroundResource(R.drawable.chart_item_shape);
                editText.setGravity(Gravity.CENTER);
                if(m == 0){
                    editText.setText(header[i]);
                } else if(i == 0){
                    editText.setText(leftheader[m-1]);
                }
                editText.setPadding(20,10,20,10);

                editText.setMinWidth(150);
                GridLayout.Spec rowSpec;
                GridLayout.Spec columnSpec;
                if((m == 1 || m == 2)&& i ==1){
                    //表示起始位置为m，占据1行
                    rowSpec=GridLayout.spec(m, 1, GridLayout.FILL);
                    //表示起始位置为i，占据1列
                    columnSpec=GridLayout.spec(i, 4, GridLayout.FILL);
                } else if((m == 1 || m == 2) && i !=0){
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
