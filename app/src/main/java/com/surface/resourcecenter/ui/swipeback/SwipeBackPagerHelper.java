package com.surface.resourcecenter.ui.swipeback;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by orekiesun on 2017/8/14.
 */

public class SwipeBackPagerHelper {

  private SwipeBackLayout swipeBackLayout;
  private ViewPager viewPager;

  private ViewPager.OnPageChangeListener onPageChange = new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
      if (position == 0) {
        // 最左侧被选中
        swipeBackLayout.markViewNotSwipable(viewPager);
      } else {
        swipeBackLayout.markViewSwipable(viewPager);
      }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
  };

  public SwipeBackPagerHelper(SwipeBackLayout swipeBackLayout, ViewPager viewPager) {
    this.swipeBackLayout = swipeBackLayout;
    this.viewPager = viewPager;
  }

  public void setUp() {
    swipeBackLayout.markViewNotSwipable(viewPager);
    viewPager.addOnPageChangeListener(onPageChange);
  }
}
