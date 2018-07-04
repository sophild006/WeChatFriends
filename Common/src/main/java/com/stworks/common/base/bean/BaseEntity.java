package com.stworks.common.base.bean;

/**
 * Created by wwq on 2018/7/4.
 */

public class BaseEntity<T> extends BaseBean {
    private T rt;


    public T getRt() {
        return rt;
    }

    public void setRt(T rt) {
        this.rt = rt;
    }

}
