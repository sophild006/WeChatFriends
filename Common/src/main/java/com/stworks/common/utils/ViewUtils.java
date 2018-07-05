package com.stworks.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stworks.common.R;

/**
 * Created by wwq on 2018/7/5.
 */

public class ViewUtils {
    /**
     * glide imageView的加载统一管理，如果更换框架也方便
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void intoImageView(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).asBitmap().placeholder(R.drawable.default_image).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /**
     * 判空后返回正确的字符串，用于给textView设置文本
     *
     * @param content
     * @return
     */
    public static String getContent(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        return content;
    }
}
