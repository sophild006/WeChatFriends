package com.stworks.common.utils;

/**
 * Created by wwq on 2018/7/5.
 */

public class UiUtils {

    public static int dip2px(float dipValue) {
        if (dipValue == 0) {
            return 0;
        }
        final float scale = GlobalContext.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
