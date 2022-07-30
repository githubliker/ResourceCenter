package com.surface.resourcecenter.ui.swipeback;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;


/**
 * Created by orekiesun on 2017/7/10.
 */

public class SwipeBackLayout extends FrameLayout {

  public static final int STATE_NONE = 2022;
  public static final int STATE_SLIDING = 2023;// 正在滑动
  public static final int STATE_PENDDING = 2024;// 正在检测是否触发条件
  public static final String TAG = "SwipebackLayout2";
  private int finishThreshold;
  private View target;


  private int startX;
  private int startY;
  private int state = STATE_NONE;

  private boolean enabled = true;

  private ViewGroupChecker swipeChecker = new ViewGroupChecker();

  public SwipeBackLayout(Context context) {
    super(context);
    init();
  }

  public SwipeBackLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public SwipeBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    finishThreshold = (int) (getResources().getDisplayMetrics().widthPixels / 5f);
  }

  /**
   * 判断是否正在滑动
   *
   * @return
   */
  public boolean isSwiping() {
    return state == STATE_SLIDING;
  }

  /**
   * 设置滑动触发返回的范围
   *
   * @param finishThreshold 当 dx 大于此值时会触发返回上一级的操作，默认值为 1/5 的屏幕宽度
   */
  public void setFinishThreshold(int finishThreshold) {
    this.finishThreshold = finishThreshold;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    boolean dispatchResult = super.dispatchTouchEvent(ev);
    Log.d("RvChecker", "dispatchTouchEvent-> result:" + dispatchResult);
    return dispatchResult;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    if (!enabled) {
      return false;
    }
    boolean intercepted = false;
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (event.getPointerCount() > 1) {
          return false;
        }
        onFingerPrepareDown(event);
        break;
      case MotionEvent.ACTION_MOVE:
        if (event.getPointerCount() > 1) {
          return false;
        }
        intercepted = onFingerPrepareMove(event);
        break;
    }
    return intercepted;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        /**
         * 若子 view 都不处理 down 事件，则会回调此处
         */
        if (state == STATE_PENDDING) {
          state = STATE_SLIDING;
          Log.d("RvChecker", "onTouchEvent-> correct state from pending to sliding");
        }
        break;
      case MotionEvent.ACTION_MOVE:
        onFingerMove(event);
        break;
      case MotionEvent.ACTION_CANCEL:
        onFingerUp(event, true);
        break;
      case MotionEvent.ACTION_UP:
        onFingerUp(event, false);
        break;
    }
    return true;
  }

  private void onFingerPrepareDown(MotionEvent event) {
    Log.d("RvChecker", "prepare down");
    state = STATE_NONE;
    startX = (int) event.getRawX();
    startY = (int) event.getRawY();
    // 横屏的时候禁止滑动返回
    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
      state = STATE_NONE;
      return;
    }
    target = getChildAt(0);
    if (!checkCheckers(event)) {
      Log.d("RvChecker", "down state switch to pendding");
      state = STATE_PENDDING;
    }
    Log.d("RvChecker", "down state: " + state);
  }

  private boolean onFingerPrepareMove(MotionEvent event) {
    Log.d("RvChecker", "prepare move");
    if (state == STATE_PENDDING) {
      int dx = (int) (event.getRawX() - startX);
      int dy = (int) (event.getRawY() - startY);
      if (dx > dp2px(getContext(),10) && Math.abs(dx) > 2 * Math.abs(dy)) {
        Log.d("RvChecker", "down state switch to slideing");
        state = STATE_SLIDING;
        return true;
      }
    }
    return false;
  }

  private void onFingerMove(MotionEvent event) {
    Log.d("RvChecker", "finger move->" + state);
    if (state == STATE_SLIDING && target != null) {
      int dx = Math.max((int) (event.getRawX() - startX), 0);
      target.setTranslationX(dx);
    }
  }

  private void onFingerUp(MotionEvent event, boolean isCanceled) {
    Log.d("RvChecker", "finger up->");
    int dx = Math.max((int) (event.getRawX() - startX), 0);
    if (dx > finishThreshold && !isCanceled) {
      Log.d("RvChecker", "finger up-> finish");
      finish(dx);
    } else {
      state = STATE_NONE;
      Log.d("RvChecker", "finger up-> reset");
      resetViewPosition(dx);
    }
  }

  private boolean checkCheckers(MotionEvent event) {
    return target != null && swipeChecker.check((ViewGroup) target, event);
  }

  private void resetViewPosition(int fromX) {
    if (target != null) {
      ObjectAnimator animator = ObjectAnimator.ofFloat(target, "translationX", fromX, 0);
      animator.setInterpolator(new FastOutSlowInInterpolator());
      animator.setDuration(300);
      animator.start();
    }
  }

  private void finish(int fromX) {
    if (target != null) {
      ObjectAnimator animator = ObjectAnimator.ofFloat(target, "translationX", fromX,
          getResources().getDisplayMetrics().widthPixels);
      animator.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          if (target != null) {
            target.setVisibility(INVISIBLE);
            if (target instanceof ViewGroup) {
              ((ViewGroup) target).removeAllViews();
            }

            Activity activity = ((Activity) getContext());
            if (activity instanceof SwipeBackActivity) {
              ((SwipeBackActivity) activity).finishNoAnim();
            } else {
              activity.finish();
            }
          }
        }
      });
      animator.setDuration(300);
      animator.start();
    }
  }

  public void markViewSwipable(View view) {
    swipeChecker.markViewSwipable(view);
  }

  public void markViewNotSwipable(View view) {
    swipeChecker.markViewNotSwipable(view);
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  public int dp2px(Context context, float dp) {
    if (context == null) {
      return -1;
    }
    return (int) (dp * context.getResources().getDisplayMetrics().density);
  }
}
