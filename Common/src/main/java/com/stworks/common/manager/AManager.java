package com.stworks.common.manager;

import android.content.Context;
import android.content.Intent;

/**
 * Created by wwq on 2018/7/4.
 * activity跳转路由
 */
public class AManager {
    /**
     * 启动主界面
     *
     * @param context
     * @param mainActivityClass
     */
    public static void startMainActivity(Context context, Class mainActivityClass) {
        context.startActivity(new Intent(context, mainActivityClass));
    }
}
