package com.surface.resourcecenter.ui.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.utils.ViewUtils;

import java.util.HashMap;


/**
 * 首页底部Tab
 */

public class TabView extends FrameLayout {

  private View rootView;
  private ViewPager viewPager;
  private ImageView tabImgHome,tabImgMine,tabImgMiddle;
  private TextView tabTxtHome,tabTxtMine,tabTxtMiddle;

  private static int currentClickTab;
  private HashMap<Integer, Integer> tabIndex = new HashMap<>();
  private HashMap<Integer, String> tabText = new HashMap<>();

  public static int TAB_HOME = 0;
  public static int TAB_MIDDLE = 1;
  public static int TAB_ME = 2;

  public TabView(@NonNull Context context) {
    this(context, null);
  }

  public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    rootView = LayoutInflater.from(context).inflate(R.layout.rc_tab_view_layout, this, true);
    initView(rootView);
    initListener(rootView);
    initTabResource();
  }

  private void initTabResource() {
    tabText.put(TabView.TAB_HOME, "首页");
    tabText.put(TabView.TAB_ME, "任务");
    tabText.put(TabView.TAB_ME, "我的");
    tabIndex.put(R.id.tabHomeLayout, TAB_HOME);
    tabIndex.put(R.id.tabSecondLayout, TAB_MIDDLE);
    tabIndex.put(R.id.tabMineLayout, TAB_ME);
  }

  private void initView(View rootView) {

    tabImgHome = rootView.findViewById(R.id.tabImgHome);
    tabImgMiddle = rootView.findViewById(R.id.tabImgSecond);
    tabImgMine = rootView.findViewById(R.id.tabImgMine);
    tabTxtHome = rootView.findViewById(R.id.tabTvHome);
    tabTxtMiddle = rootView.findViewById(R.id.tabTvSecond);
    tabTxtMine = rootView.findViewById(R.id.tabTvMine);
    tabImgHome.setSelected(true);
    tabTxtHome.setSelected(true);
  }

  private void initListener(View rootView) {
    ViewUtils.bindClick(rootView, R.id.tabHomeLayout, clickListener);
    ViewUtils.bindClick(rootView, R.id.tabSecondLayout, clickListener);
    ViewUtils.bindClick(rootView, R.id.tabMineLayout, clickListener);
  }

  public void setViewPager(ViewPager viewPager) {
    this.viewPager = viewPager;
  }

  private void tabSelect(int index) {
    unSelectedAll();
    if (index == TAB_HOME) {
      tabImgHome.setSelected(true);
      tabTxtHome.setSelected(true);
    } else if (index == TAB_ME) {
      tabImgMine.setSelected(true);
      tabTxtMine.setSelected(true);
    } else if (index == TAB_MIDDLE) {
      tabImgMiddle.setSelected(true);
      tabTxtMiddle.setSelected(true);
    }
  }

  private void unSelectedAll(){
    tabImgHome.setSelected(false);
    tabImgMine.setSelected(false);
    tabTxtHome.setSelected(false);
    tabTxtMine.setSelected(false);
    tabImgMiddle.setSelected(false);
    tabTxtMiddle.setSelected(false);
  }

  private OnClickListener clickListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      if (viewPager != null) {
        currentClickTab = tabIndex.get(v.getId());
        viewPager.setCurrentItem(currentClickTab);
        tabSelect(currentClickTab);
      }
    }
  };

}

