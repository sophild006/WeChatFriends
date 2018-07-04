package com.stworks.common.base.mvp;

import android.content.Context;

/**
 * Created by wwq on 2018/7/4.
 */

public interface MainPresenter<V extends MainView> {
    /**
     * 初始化presenter
     */
    void init(Context context);

    /**
     * 绑定view
     *
     * @param view
     */
    void attatchView(V view);

    /**
     * 解绑view
     */
    void detatchView();

    /**
     * 释放一些资源等等
     */
    void onDestroy();
}
