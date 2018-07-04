package com.stworks.common.base.mvp;

/**
 * Created by wwq on 2018/7/4.
 */

public interface HttpCallBack<T> {

    void onResponse(T t, String error);
}

