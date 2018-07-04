package com.stworks.common.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wwq on 2018/7/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(doGetLayoutResId());
    }

    protected abstract int doGetLayoutResId();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化监听器
     */
    protected abstract void initListener();

    /**
     * @param flag 1 是显示errorView 隐藏其他
     *             0 是显示emptyView  隐藏其他
     *             -1 是正常显示view  隐藏其他
     */
    public void setViewStatus(int flag) {

    }
}
