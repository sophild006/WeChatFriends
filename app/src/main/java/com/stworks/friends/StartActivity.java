package com.stworks.friends;

import android.os.Bundle;
import android.view.View;

import com.stworks.common.base.ui.BaseActivity;
import com.stworks.common.manager.AManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wwq on 2018/7/5.
 */

public class StartActivity  extends BaseActivity {

    private Unbinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder= ButterKnife.bind(this);
    }

    @Override
    protected int doGetLayoutResId() {
        return R.layout.start_activity;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.tv_enter_friends})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.tv_enter_friends:
                AManager.startMainActivity(this,MainActivity.class);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }
}
