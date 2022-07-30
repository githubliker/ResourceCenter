package com.surface.resourcecenter;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.surface.resourcecenter.data.sp.SpManager;
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

    }


}
