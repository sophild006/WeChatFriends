package com.stworks.common.base.mvp;

/**
 * Created by wwq on 2018/7/4.
 */

public interface MainView {
    /**
     * 通过状态码弹出提示内容
     *
     * @param statusCode
     * @param message
     */
    void showToast(int statusCode, String message);

    /**
     * 单纯的弹出提示
     *
     * @param message
     */
    void showToast(String message);

    /**
     * 设置view显示状态
     *
     * @param status
     */
    void setViewStatus(int status);

}
