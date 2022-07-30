package com.surface.resourcecenter.ui.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by gukang on 2017/7/17.
 */

public class NoScrollViewPager extends ViewPager {

  public NoScrollViewPager(Context context) {
    super(context);
  }

  public NoScrollViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public void setCurrentItem(int item, boolean smoothScroll) {
    super.setCurrentItem(item, smoothScroll);
  }

  @Override
  public void setCurrentItem(int item) {
    super.setCurrentItem(item, false);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return false;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return false;
  }
}
