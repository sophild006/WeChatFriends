package com.stworks.common.base.mvp;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by wwq on 2018/7/4.
 */

public class MainBasePresenter<V extends MainView> implements MainPresenter<V> {

    private WeakReference<V> referenceView;

    public V getView() {
        return referenceView == null ? null : referenceView.get();
    }

    public Context mContext;

    @Override
    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public void attatchView(V view) {
        referenceView = new WeakReference<>(view);
    }

    @Override
    public void detatchView() {
        if (referenceView != null) {
            referenceView.clear();
            referenceView = null;
        }
    }

    @Override
    public void onDestroy() {
        if (referenceView != null && referenceView.get() != null) {
            detatchView();
        }
    }
}
