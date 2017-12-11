package com.android.devartexplore;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

/**
 * Created by gj on 2017/12/2.
 */

public class AdtApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        LogUtils.getConfig().setBorderSwitch(false);
        LogUtils.getConfig().setLogHeadSwitch(false);

    }
}
