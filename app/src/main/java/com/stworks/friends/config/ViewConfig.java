package com.stworks.friends.config;

/**
 * Created by wwq on 2018/7/4.
 */

public class ViewConfig {
    /**
     * 1 是显示errorView 隐藏其他
     * 0 是显示emptyView  隐藏其他
     * -1 是正常显示view  隐藏其他
     */
    public static final int NORMAL_STATUS = -1;
    public static final int EMPTY_STATUS = 0;
    public static final int ERROR_STATUS = 1;


    /**
     * view类型
     */

    public static final int ONLY_WORDS = 0;
    public static final int WORD_AND_PICTURE = 1;
}
