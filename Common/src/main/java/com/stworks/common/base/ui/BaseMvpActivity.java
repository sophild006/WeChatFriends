package com.stworks.common.base.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.stworks.common.base.mvp.MainPresenter;
import com.stworks.common.base.mvp.MainView;

/**
 * Created by wwq on 2018/7/4.
 */

public abstract class BaseMvpActivity<P extends MainPresenter> extends BaseActivity implements MainView {

    private P mPresenter;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showToast(String message) {

        toast.setText(message);
        toast.show();
    }

    @Override
    public void showToast(int statusCode, String message) {
        toast.setText(message);
        toast.show();
    }

    @Override
    public void setViewStatus(int flag) {
        super.setViewStatus(flag);

    }

    public abstract P createPresenter();

    protected P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attatchView(this);
        }
        if (toast == null) {
            toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        }
        return mPresenter;
    }
}
