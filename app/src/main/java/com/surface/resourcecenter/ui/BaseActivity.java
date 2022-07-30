package com.surface.resourcecenter.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentManager;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.statusbar.StatusBarCompat;
import com.surface.resourcecenter.ui.statusbar.StatusbarUtils;
import com.surface.resourcecenter.ui.swipeback.SwipeBackActivity;


public class BaseActivity extends SwipeBackActivity {
  private ImageView mStatusbarImage;
  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = this;
  }

  protected void initStatusBar(Activity activity) {
    if (Build.VERSION.SDK_INT >= 19) {
      setStatusBar(activity);
    } else {
      setStatusBarEnable(false);
    }
  }

  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    //默认是关闭页面右滑退出动效果
    getSwipeBackLayout().setEnabled(false);
    setStatusBarIcon();
    initStatusBar(this);
    hideStatusBar();
  }

  protected void setStatusBarIcon() {
    mStatusbarImage = new ImageView(this);
    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    layoutParams.height = StatusbarUtils.getStatusBarHeight(context);
    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
    mStatusbarImage.setLayoutParams(layoutParams);
    mStatusbarImage.setBackgroundColor(getResources().getColor(R.color.yc_primary_bg));
    setRootPaddingTop(StatusbarUtils.getStatusBarHeight(context));
    ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    viewGroup.addView(mStatusbarImage, 0);
  }

  protected void setStatusBar(Activity activity) {
    if (StatusbarUtils.MIUISetStatusBarLightMode(activity, false)) {
      setStatusBarEnable(true);
      setStatusbarSize();
    } else if (StatusbarUtils.FlymeSetStatusBarLightMode(activity, false)) {
      setStatusBarEnable(true);
      setStatusbarSize();
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      StatusBarCompat.translucentStatusBar(activity, true);
      setStatusBarEnable(true);
      setStatusbarSize();
      activity.getWindow().getDecorView().setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    } else {
      setStatusBarEnable(false);
    }
  }

  protected void setStatusBarEnable(boolean enable) {
    int marginTop = enable ? StatusbarUtils.getStatusBarHeight(context) : 0;
    if (mStatusbarImage != null) {
      mStatusbarImage.setVisibility(enable ? View.VISIBLE : View.GONE);
    }
    setRootPaddingTop(marginTop);
  }

  protected void setStatusbarSize() {
    if (mStatusbarImage != null) {
      RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
          RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
      layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
      layoutParams.height = StatusbarUtils.getStatusBarHeight(context);
      mStatusbarImage.setLayoutParams(layoutParams);
    }
  }

  public void hideStatusBar() {
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setStatusBarEnable(false);
  }

  public void showStatusBar() {
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setStatusBarEnable(true);
  }

  @Override
  protected void onResume() {
    super.onResume();
    getSupportFragmentManager()
        .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
          @Override
          public void onBackStackChanged() {
            setStatusBar(BaseActivity.this);
          }
        });
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setStatusBarEnable(false);
    } else {
      initStatusBar(this);
    }
  }

}
