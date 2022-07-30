package com.surface.resourcecenter.data.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.surface.resourcecenter.R;

/**
 * Toast工具类
 * Created by wcy on 2015/12/26.
 */
public class ToastUtils {

    public static void ShowCenterToast(Context context, String msg){
        Toast toast = Toast.makeText(context,msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        try{
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
        }catch (Exception e){}
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(R.drawable.icon_success);
        imageCodeProject.setPadding(20,30,20,30);
        toastView.addView(imageCodeProject, 0);
        toastView.setBackgroundResource(R.drawable.btn_blank_title_rect);
        toastView.setPadding(50,30,50,30);
        toast.show();
    }
    public static void hintKbOne(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
// 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
}
