package com.surface.resourcecenter.ui.swipeback;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import androidx.fragment.app.FragmentActivity;


/**
 * Created by orekiesun on 2017/7/10.
 * 这个 activity 做了两件事
 * 1.给最外层 view 套上一层 swipebacklayout
 * 2.去掉退出动画
 */

public class SwipeBackActivity extends FragmentActivity implements SwipeBackContainer {


  private SwipeBackLayout swipeBackLayout;
  private ImageView ivShadow;
  private View view;

  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(getContainer());
    view = LayoutInflater.from(this).inflate(layoutResID, null);
    // view.setBackgroundColor(Color.WHITE);
    swipeBackLayout.addView(view);
  }

  private View getContainer() {
    RelativeLayout container = new RelativeLayout(this);
    swipeBackLayout = new SwipeBackLayout(this);
    ivShadow = new ImageView(this);
    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    container.addView(ivShadow, params);
    container.addView(swipeBackLayout, params);
    return container;
  }

  public void setRootPaddingTop(int statusBarHeight) {
    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    params.topMargin = statusBarHeight;
    swipeBackLayout.setLayoutParams(params);
    ivShadow.setLayoutParams(params);
  }

  @Override
  public void setRequestedOrientation(int requestedOrientation) {
    if (swipeBackLayout != null && swipeBackLayout.isSwiping()) {
      return;
    }
    super.setRequestedOrientation(requestedOrientation);
  }

  public void finishNoAnim() {
    finish();
    overridePendingTransition(0, 0);
  }

  public void disableBackgroundWhite() {
    view.setBackgroundColor(Color.TRANSPARENT);
  }

  public SwipeBackLayout getSwipeBackLayout() {
    return swipeBackLayout;
  }

  public void markViewSwipable(View view) {
    swipeBackLayout.markViewSwipable(view);
  }

  public void markViewNotSwipable(View view) {
    swipeBackLayout.markViewNotSwipable(view);
  }


}
