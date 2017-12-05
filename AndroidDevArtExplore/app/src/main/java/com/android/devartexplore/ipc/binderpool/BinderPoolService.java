package com.android.devartexplore.ipc.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by gj on 2017/12/5.
 */

public class BinderPoolService extends Service {

    private IBinder binder = new BinderPool.BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
