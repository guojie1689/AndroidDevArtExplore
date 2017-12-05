package com.android.devartexplore.ipc.binderpool;

import android.os.RemoteException;

/**
 * Created by gj on 2017/12/5.
 */

public class SecurityCenterImpl extends ISecurityCenter.Stub {
    @Override
    public String encrypt(String content) throws RemoteException {
        char data[] = content.toCharArray();

        for (int i = 0; i < data.length; i++) {
            data[i] = (char) (data[i] + 2);
        }

        return new String(data);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        char data[] = password.toCharArray();

        for (int i = 0; i < data.length; i++) {
            data[i] = (char) (data[i] - 2);
        }

        return new String(data);
    }
}
