package com.surface.resourcecenter.data.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;


/**
 * 封装图片加载
 * Created by dongming on 2017/3/27.
 */
public class ImageBinder {

    private ImageBinder() {
    }

    /**
     * 图片加载
     *
     * @param imageView
     * @param url
     * @param defaultImage
     */
    public static void bind(ImageView imageView, String url, int defaultImage) {
        RequestOptions options = new RequestOptions();
        options.placeholder(defaultImage)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(imageView.getContext()).load(url)
                .apply(options)
                .into(imageView);
    }

    public static void bindRoundImage(ImageView view, String url, int imageDefault, int round) {
        RequestOptions myOptions = new RequestOptions()
                .placeholder(imageDefault)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new GlideRoundTransform(view.getContext(), round));

        Glide.with(view.getContext())
                .load(url)
                .apply(myOptions)
                .into(view);
    }

    public static void bindCircleImage(ImageView mUserPic, String doCommentHead, int ic_default_color) {
    }
}
