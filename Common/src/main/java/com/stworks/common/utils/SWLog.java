package com.stworks.common.utils;

import android.util.Log;

import com.stworks.common.BuildConfig;

/**
 * Created by wwq on 2018/7/4.
 */

public class SWLog {
    public static boolean isDebug = BuildConfig.LOG_DEBUG;
    public static String TAG = "SWLog";

    public static void d(String message) {
        if (!isDebug)
            return;
        Log.d(TAG, message);
    }

    public static void e(String message) {
        if (!isDebug)
            return;
        Log.e(TAG, message);
    }
}
