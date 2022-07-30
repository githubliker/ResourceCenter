package com.surface.resourcecenter.data.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.surface.resourcecenter.data.sp.SpKey;
import com.surface.resourcecenter.data.sp.SpManager;


/**
 * 封装对View的操作
 * Created by dongming on 2017/2/14.
 */
public class ViewUtils {

  public static <T extends View> T inflate(ViewGroup parent, int resId) {
    View view = newInstance(parent, resId);
    if (view != null) {
      return (T) view;
    }
    return null;
  }

  public static View newInstance(ViewGroup parent, int resId) {
    return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
  }

  public static void setVisibility(View host, int id, int visibility) {
    if (host != null) {
      setVisibility(host.findViewById(id), visibility);
    }
  }

  public static void setVisibility(View view, int visibility) {
    if (view != null) {
      view.setVisibility(visibility);
    }
  }

  public static void setText(View host, int id, String text) {
    if (host != null) {
      setText(host.findViewById(id), text);
    }
  }

  public static void setText(View view, String text) {
    if (view instanceof TextView) {
      ((TextView) view).setText(text);
    }
  }

  public static void setImage(View host, int id, String url, int imageDefault) {
    if (host != null) {
      if (!TextUtils.isEmpty(url)) {
        setImage(host.findViewById(id), url, imageDefault);
      } else {
        setImage(host.findViewById(id), imageDefault);
      }
    }
  }

  public static void setImageRound(View host, int id, String url, int imageDefault, int round) {
    if (host != null) {
      if (!TextUtils.isEmpty(url)) {
        setImageRound(host.findViewById(id), url, imageDefault, round);
      } else {
        setImage(host.findViewById(id), imageDefault);
      }
    }

  }

  public static void setImage(View view, String url, int imageDefault) {
    if (view instanceof ImageView) {
      ImageBinder.bind((ImageView) view, url, imageDefault);
    }
  }

  public static void setImageRound(View view, String url, int imageDefault, int round) {
    if (view instanceof ImageView) {
      ImageBinder.bindRoundImage((ImageView) view, url, imageDefault, round);
    }
  }

  public static void setImage(View host, int id, int imageDefault) {
    if (host != null) {
      setImage(host.findViewById(id), imageDefault);
    }
  }

  public static void setImage(View view, int imageDefault) {
    if (view instanceof ImageView) {
      ImageView imageView = (ImageView) view;
      imageView.setImageResource(imageDefault);
    }
  }

  public static void setBackgroundColor(View host, int id, int color) {
    if (host != null) {
      setBackgroundColor(host.findViewById(id), color);
    }
  }

  public static void setBackgroundColor(View view, int color) {
    if (view != null) {
      view.setBackgroundColor(color);
    }
  }

  public static void setTextColor(View host, int id, int color) {
    if (host != null) {
      setTextColor(host.findViewById(id), color);
    }
  }

  public static void setTextColor(View view, int color) {
    if (view instanceof TextView) {
      ((TextView) view).setTextColor(color);
    }
  }

  public static void setTypeface(View host, int id, Typeface typeface) {
    if (host != null) {
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        setTypeface(host.findViewById(id), typeface);
      }
    }
  }

  public static void setTypeface(View view, Typeface typeface) {
    if (view instanceof TextView) {
      ((TextView) view).setTypeface(typeface);
    }
  }

  public static void bindClick(View host, int id, View.OnClickListener listener) {
    if (host != null) {
      View view = host.findViewById(id);
      if (view != null) {
        view.setOnClickListener(listener);
      }
    }
  }

  public static void bindSingleClick(View host, int id, final SingleOnClicklistener listener) {
    if (host != null) {
      View view = host.findViewById(id);
      if (view != null) {
        view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            long singleClickTs = SpManager.getInstance().get(SpKey.SINGLE_CLICK_TS, 0L);
            if (System.currentTimeMillis() - singleClickTs >= 1000) {
              SpManager.getInstance().set(SpKey.SINGLE_CLICK_TS, System.currentTimeMillis());
              listener.onClick(v);
            }
          }
        });
      }
    }
  }

  public static void requestLayout(View host, int id) {
    if (host != null) {
      View view = host;
      if (id > 0) {
        view = host.findViewById(id);
      }
      if (view != null) {
        view.requestLayout();
      }
    }
  }

  public interface SingleOnClicklistener {
    void onClick(View v);
  }

}

