package com.surface.resourcecenter;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.surface.resourcecenter.data.sp.SpManager;
import com.tencent.smtt.sdk.QbSdk;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

public class HcApplication extends Application {

    private static final String APP_NAME = "Controller";

    @Override
    public void onCreate() {
        super.onCreate();

        SpManager.init(this);

        NoHttp.initialize(this);
        Logger.setTag("NoHttp");
        Logger.setDebug(true);// 开始NoHttp的调试模式, 这样就能看到请求过程和日志

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }


}
