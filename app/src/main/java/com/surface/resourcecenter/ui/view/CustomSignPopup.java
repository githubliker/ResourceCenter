package com.surface.resourcecenter.ui.view;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.utils.FileHelper;

import java.io.IOException;

public class CustomSignPopup extends CenterPopupView {
    public CustomSignPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_custom_sign;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        SignatureView signatureView = findViewById(R.id.signature);
        findViewById(R.id.sign_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
                dismiss();
            }
        });
        findViewById(R.id.save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String path = FileHelper.getPicDir(getContext())+System.currentTimeMillis()+".png";
                    if (signatureView.save(path)) {
                        Log.e("TAG","save path "+path);
                        Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "保存失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        findViewById(R.id.clear).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
            }
        });
    }
    protected void onShow() {
        super.onShow();
    }
}
