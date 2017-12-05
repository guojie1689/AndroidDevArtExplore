package com.android.devartexplore.ipc.binderpool;

import android.os.RemoteException;

/**
 * Created by gj on 2017/12/5.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
