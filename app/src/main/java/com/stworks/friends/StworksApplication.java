package com.stworks.friends;

import android.support.multidex.MultiDexApplication;

import com.stworks.common.utils.GlobalContext;

/**
 * Created by wwq on 2018/7/4.
 */

public class StworksApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        GlobalContext.setAppContext(this);
    }
}
