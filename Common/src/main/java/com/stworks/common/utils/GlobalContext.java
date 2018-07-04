package com.stworks.common.utils;

import android.content.Context;

/**
 * Created by wwq on 2018/7/4.
 */

public class GlobalContext {

    private GlobalContext() {
    }

    private static Context mContext;

    public static final void setAppContext(Context appContext) {
        if (appContext != null) {
            mContext = appContext.getApplicationContext();
        }
    }

    public static final Context getAppContext() {
        assert mContext != null;
        return mContext;
    }
}
