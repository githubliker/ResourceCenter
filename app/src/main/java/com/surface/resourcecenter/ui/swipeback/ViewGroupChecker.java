package com.surface.resourcecenter.ui.swipeback;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 这个类主要做的事是遍历 targe view 的树，并交由注册的 checker 进行检查
 * Created by orekiesun on 2017/7/20.
 */

public class ViewGroupChecker {
  private boolean traversaled = false;

  private List<WeakReference<View>> whiteNameViews = new ArrayList<>();

  /**
   * 检查当前点击是否被特殊子 view 需要
   *
   * @param viewGroup
   * @param event
   * @return true:子 view 可能需要消费这次事件 false:没有子 view 需要消费
   */
  boolean check(ViewGroup viewGroup, MotionEvent event) {
    if (!traversaled) {
      traversalViewGroup(viewGroup, event);
      traversaled = true;
    }
    for (WeakReference<View> whiteViewRef : whiteNameViews) {
      View view = whiteViewRef.get();
      if (touchInView(view, event) && viewNeedFoucs(view, event)) {
        Log.d("ViewGroupChecker", "touch white name view");
        return true;
      }
    }
    Log.d("ViewGroupChecker", "no white name view touched");
    return false;
  }

  private boolean viewNeedFoucs(View whiteView, MotionEvent event) {
    if (whiteView instanceof RecyclerView) {
      RecyclerView recyclerView = (RecyclerView) whiteView;
      return ViewCompat.canScrollHorizontally(recyclerView, -1)
          || ViewCompat.canScrollHorizontally(recyclerView, 1);
    }
    return true;
  }

  /**
   * 第一次遍历整个 view 树
   * 记下所有可能需要事件拦截的 view
   *
   * @param viewGroup
   * @param event
   * @return
   */
  private boolean traversalViewGroup(ViewGroup viewGroup, MotionEvent event) {
    for (int i = 0; i < viewGroup.getChildCount(); i++) {
      View view = viewGroup.getChildAt(i);
      if (isWhiteNameView(view, event)) {
        whiteNameViews.add(new WeakReference<>(view));
      }
      if (view instanceof ViewGroup) {
        traversalViewGroup((ViewGroup) view, event);
      }
    }
    return false;
  }

  private boolean touchInView(View view, MotionEvent event) {
    if (view == null) {
      return false;
    }
    int[] loc = new int[2];
    view.getLocationOnScreen(loc);
    int x = loc[0], y = loc[1], width = view.getWidth(),
        height = view.getHeight();
    boolean touchInRv = false;
    if (event.getRawX() > x && event.getRawX() < x + width &&
        event.getRawY() > y && event.getRawY() < y + height) {
      touchInRv = true;
    }
    return touchInRv;
  }

  /**
   * 找到可能需要拦截事件的子 view
   *
   * @param view
   * @param event
   * @return
   */
  private boolean isWhiteNameView(View view, MotionEvent event) {
    return view instanceof RecyclerView;
  }

  public void markViewSwipable(View view) {
    for (WeakReference<View> curViewRef : whiteNameViews) {
      if (curViewRef.get() == view) {
        return;
      }
    }
    whiteNameViews.add(new WeakReference<>(view));
  }

  public void markViewNotSwipable(View view) {
    Iterator<WeakReference<View>> iterator = whiteNameViews.iterator();
    while (iterator.hasNext()) {
      WeakReference<View> curViewRef = iterator.next();
      if (curViewRef.get() == view) {
        iterator.remove();
      }
    }
  }
}
