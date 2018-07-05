package com.stworks.common.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;

import com.stworks.common.span.TextClickSpan;

import java.util.List;

/**
 * Created by wwq on 2018/7/5.
 */

public class StringUtils {


    public static SpannableStringBuilder createCommentSpan(Context context, String childUserName, String commentContent) {
        String richText = String.format("%s: %s", childUserName, commentContent);
        SpannableStringBuilder builder = new SpannableStringBuilder(richText);
        if (!TextUtils.isEmpty(childUserName)) {
            builder.setSpan(new TextClickSpan(context, childUserName), 0, childUserName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

}
