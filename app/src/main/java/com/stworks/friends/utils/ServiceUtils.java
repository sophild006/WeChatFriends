package com.stworks.friends.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.stworks.common.span.TextClickSpan;
import com.stworks.common.span.VerticalImageSpan;
import com.stworks.friends.R;
import com.stworks.friends.bean.CommentInfo;

import java.util.List;

/**
 * Created by wwq on 2018/7/5.
 */

public class ServiceUtils {
    public static SpannableStringBuilder makePraiseSpan(Context context, List<CommentInfo> praiseBeans) {
        if (praiseBeans != null && praiseBeans.size() > 0) {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append("  ");
            int praiseSize = praiseBeans.size();
            for (int i = 0; i < praiseSize; i++) {
                CommentInfo praiseBean = praiseBeans.get(i);
                String praiseUserName = praiseBean.getSender().getNick();
                int start = builder.length();
                int end = start + praiseUserName.length();
                builder.append(praiseUserName);
                if (i != praiseSize - 1) {
                    builder.append(", ");
                }
                builder.setSpan(new TextClickSpan(context, praiseUserName), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            builder.setSpan(new VerticalImageSpan(ContextCompat.getDrawable(context, R.drawable.heart_drawable_blue)),
                    0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return builder;
        }
        return null;
    }
}
