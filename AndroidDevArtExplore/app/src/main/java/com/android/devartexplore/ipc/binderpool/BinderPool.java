package com.android.devartexplore.ipc.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.CountDownLatch;

/**
 * Created by gj on 2017/12/5.
 */

public class BinderPool {

    private static BinderPool instance = null;
    private Context context = null;
    private IBinderPool binderPool = null;
    private CountDownLatch countDownLatch = null;

    public final static int BINDER_CODE_SECURITY_CENTER = 1;
    public final static int BINDER_CODE_COMPUTE = 2;

    private BinderPool(Context context) {
        this.context = context;

        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context) {
        if (instance == null) {
            synchronized (BinderPool.class) {
                if (instance == null) {
                    instance = new BinderPool(context);
                }
            }
        }

        return instance;
    }

    private void connectBinderPoolService() {

        countDownLatch = new CountDownLatch(1);
        Intent intent = new Intent(context, BinderPoolService.class);

        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderPool = IBinderPool.Stub.asInterface(service);

            try {
                binderPool.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            countDownLatch.countDown();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder = null;

        if (binderPool != null) {
            binder = binderPool.queryBinder(binderCode);
        }

        return binder;
    }


    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            binderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            binderPool = null;

            connectBinderPoolService();
        }
    };

    public static class BinderPoolImpl extends IBinderPool.Stub {
        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            switch (binderCode) {
                case BINDER_CODE_SECURITY_CENTER:
                    return new SecurityCenterImpl();
                case BINDER_CODE_COMPUTE:
                    return new ComputeImpl();
            }

            return null;
        }
    }

}
